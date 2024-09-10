import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class atm extends JFrame implements ActionListener {

    JButton deposit, withdrawl, p_change, ministatement, balanceenquiry, fastcash, exit;
    String p_number;

    atm(String p_number) {
        this.p_number = p_number;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("assets/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(150, 388, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Cash Withdrawl");
        withdrawl.setBounds(150, 422, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        p_change = new JButton("Pin Change");
        p_change.setBounds(150, 456, 150, 30);
        p_change.addActionListener(this);
        image.add(p_change);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(357, 388, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(357, 422, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(357, 456, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        exit = new JButton("Exit");
        exit.setBounds(357, 490, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        // setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(p_number).setVisible(true);
        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new Withdrawl(p_number).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new atm("");
    }

}
