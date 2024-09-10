import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener {

    JButton R100, R200, R500, R1000, R2000, R5000, back;
    String p_number;

    Fastcash(String p_number) {
        this.p_number = p_number;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select withdrawl amount");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        R100 = new JButton("Rs 100");
        R100.setBounds(150, 388, 150, 30);
        R100.addActionListener(this);
        image.add(R100);

        R200 = new JButton("Rs 200");
        R200.setBounds(150, 422, 150, 30);
        R200.addActionListener(this);
        image.add(R200);

        R500 = new JButton("Rs 500");
        R500.setBounds(150, 456, 150, 30);
        R500.addActionListener(this);
        image.add(R500);

        R1000 = new JButton("Rs 1000");
        R1000.setBounds(357, 388, 150, 30);
        R1000.addActionListener(this);
        image.add(R1000);

        R2000 = new JButton("Rs 2000");
        R2000.setBounds(357, 422, 150, 30);
        R2000.addActionListener(this);
        image.add(R2000);

        R5000 = new JButton("Rs 5000");
        R5000.setBounds(357, 456, 150, 30);
        R5000.addActionListener(this);
        image.add(R5000);

        back = new JButton("Back");
        back.setBounds(357, 490, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        // setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new atm(p_number).setVisible(true);
        } else {
            String amountText = ((JButton) ae.getSource()).getText().substring(3);
            int withdrawalAmount = Integer.parseInt(amountText);
            Conn c = new Conn();
            try {
                // Retrieve the current balance
                Connection connection = c.getConnection(); // Get the database connection
                String query = "SELECT * FROM bank WHERE pin = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, p_number);
                ResultSet rs = ps.executeQuery(); // Execute the query
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += rs.getInt("amount");
                    } else {
                        balance -= rs.getInt("amount");
                    }
                }

                // Check if the withdrawal amount exceeds the balance
                if (balance < withdrawalAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Perform the withdrawal
                Date date = new Date();
                query = "INSERT INTO bank VALUES (?, ?, ?, ?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, p_number);
                ps.setDate(2, new java.sql.Date(date.getTime()));
                ps.setString(3, "Withdrawal");
                ps.setInt(4, withdrawalAmount);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Transaction Successful", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                c.closeConnection(); // Close the database connection
            }
        }
    }

    public static void main(String args[]) {
        new Fastcash("");
    }

}
