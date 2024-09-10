import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, exit, back;
    String p_number;

    Deposit(String p_number) {
        this.p_number = p_number;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel info = new JLabel("Enter your amount to deposit");
        info.setForeground(Color.WHITE);
        info.setFont(new Font("System", Font.BOLD, 16));
        info.setBounds(170, 300, 400, 20);
        image.add(info);

        amount = new JTextField();
        amount.setFont(new Font("System", Font.BOLD, 16));
        amount.setBounds(170, 350, 335, 25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBackground(Color.GREEN);
        deposit.setBounds(410, 420, 100, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(410, 455, 100, 30);
        back.addActionListener(this);
        image.add(back);

        exit = new JButton("Exit");
        exit.setBackground(Color.RED);
        exit.setBounds(410, 490, 100, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String strAmount = amount.getText();
            if (strAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount");
            } else {
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values(?, ?, ?, ?)";
                    PreparedStatement ps = conn.getConnection().prepareStatement(query);
                    ps.setString(1, p_number);
                    ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                    ps.setString(3, "Deposit");
                    ps.setString(4, strAmount);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Rs" + strAmount + " Deposit Successfully");
                    setVisible(false);
                    new atm(p_number).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new atm(p_number).setVisible(true);
        } else if (ae.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }

}
