import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {

    JButton submit, cancel;
    JRadioButton s_account, c_account, f_account, r_account;
    JCheckBox s1, s2, s3, s4, s5, s6, s7;
    String formno;

    Signup3(String formno) {
        this.formno = formno;

        JLabel additional = new JLabel("Page 3: Account Details");
        additional.setFont(new Font("Raleway", Font.BOLD, 30));
        additional.setBounds(280, 70, 600, 40);
        add(additional);

        JLabel a_type = new JLabel("Account Type:");
        a_type.setFont(new Font("Raleway", Font.BOLD, 15));
        a_type.setBounds(100, 130, 600, 40);
        add(a_type);

        ButtonGroup a_type_group = new ButtonGroup();
        s_account = new JRadioButton("Saving Account");
        s_account.setBounds(100, 170, 200, 40);
        s_account.setBackground(Color.lightGray);
        add(s_account);
        a_type_group.add(s_account);

        c_account = new JRadioButton("Current Account");
        c_account.setBounds(100, 200, 200, 40);
        c_account.setBackground(Color.lightGray);
        add(c_account);
        a_type_group.add(c_account);

        f_account = new JRadioButton("Fixed Deposit Account");
        f_account.setBounds(400, 170, 200, 40);
        f_account.setBackground(Color.lightGray);
        add(f_account);
        a_type_group.add(f_account);

        r_account = new JRadioButton("Recurring Deposit Account");
        r_account.setBounds(400, 200, 200, 40);
        r_account.setBackground(Color.lightGray);
        add(r_account);
        a_type_group.add(r_account);

        JLabel c_number = new JLabel("Card Number:");
        c_number.setFont(new Font("Raleway", Font.BOLD, 15));
        c_number.setBounds(100, 250, 600, 40);
        add(c_number);

        JLabel c_info = new JLabel("Your 16 digit card number");
        c_info.setFont(new Font("Raleway", Font.BOLD, 10));
        c_info.setBounds(100, 270, 600, 40);
        add(c_info);

        JLabel x_card = new JLabel("XXXX-XXXX-XXXX-XXXX");
        x_card.setFont(new Font("Raleway", Font.BOLD, 15));
        x_card.setBounds(300, 250, 300, 40);
        add(x_card);

        JLabel p_number = new JLabel("PIN Number:");
        p_number.setFont(new Font("Raleway", Font.BOLD, 15));
        p_number.setBounds(100, 300, 600, 40);
        add(p_number);

        JLabel p_info = new JLabel("Your 4 digit pin number");
        p_info.setFont(new Font("Raleway", Font.BOLD, 10));
        p_info.setBounds(100, 320, 600, 40);
        add(p_info);

        JLabel xx_card = new JLabel("XXXX");
        xx_card.setFont(new Font("Raleway", Font.BOLD, 15));
        xx_card.setBounds(300, 300, 300, 40);
        add(xx_card);

        JLabel Services = new JLabel("Services:");
        Services.setFont(new Font("Raleway", Font.BOLD, 15));
        Services.setBounds(100, 350, 600, 40);
        add(Services);

        s1 = new JCheckBox("ATM Card");
        s1.setBackground(Color.LIGHT_GRAY);
        s1.setFont(new Font("Raleway", Font.BOLD, 15));
        s1.setBounds(100, 390, 200, 40);
        add(s1);

        s2 = new JCheckBox("E-Statement");
        s2.setBackground(Color.LIGHT_GRAY);
        s2.setFont(new Font("Raleway", Font.BOLD, 15));
        s2.setBounds(100, 420, 200, 40);
        add(s2);

        s3 = new JCheckBox("Mobile Banking");
        s3.setBackground(Color.LIGHT_GRAY);
        s3.setFont(new Font("Raleway", Font.BOLD, 15));
        s3.setBounds(100, 450, 200, 40);
        add(s3);

        s4 = new JCheckBox("Cheque Book");
        s4.setBackground(Color.LIGHT_GRAY);
        s4.setFont(new Font("Raleway", Font.BOLD, 15));
        s4.setBounds(400, 390, 200, 40);
        add(s4);

        s5 = new JCheckBox("Internet Banking");
        s5.setBackground(Color.LIGHT_GRAY);
        s5.setFont(new Font("Raleway", Font.BOLD, 15));
        s5.setBounds(400, 420, 200, 40);
        add(s5);

        s6 = new JCheckBox("Email & SMS Alerts");
        s6.setBackground(Color.LIGHT_GRAY);
        s6.setFont(new Font("Raleway", Font.BOLD, 15));
        s6.setBounds(400, 450, 200, 40);
        add(s6);

        s7 = new JCheckBox("I hereby declares that the above information is correct.");
        s7.setBackground(Color.LIGHT_GRAY);
        s7.setFont(new Font("Raleway", Font.BOLD, 15));
        s7.setBounds(100, 550, 600, 40);
        add(s7);

        cancel = new JButton("Cancel");
        cancel.setBounds(400, 650, 80, 40);
        cancel.addActionListener(this);
        add(cancel);

        submit = new JButton("Submit");
        submit.setBounds(520, 650, 80, 40);
        submit.addActionListener(this);
        add(submit);

        setTitle("KMB Account Details");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setSize(800, 800);
        setVisible(true);
        setLocation(350, 10);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String a_type_group = null;
            if (s_account.isSelected()) {
                a_type_group = "Saving Account";
            } else if (c_account.isSelected()) {
                a_type_group = "Current Account";
            } else if (f_account.isSelected()) {
                a_type_group = "Fixed Deposit account";
            } else if (r_account.isSelected()) {
                a_type_group = "Recurring Deposit account";
            }
            Random random = new Random();
            String c_number = "" + Math.abs((random.nextLong() % 90000000)) + 90393047L;
            String p_number = "" + Math.abs((random.nextLong() % 9000L) + 1000L);
            StringBuilder servicesBuilder = new StringBuilder();
            if (s1.isSelected()) {
                servicesBuilder.append("ATM Card, ");
            }
            if (s2.isSelected()) {
                servicesBuilder.append("E-Statement, ");
            }
            if (s3.isSelected()) {
                servicesBuilder.append("Mobile Banking, ");
            }
            if (s4.isSelected()) {
                servicesBuilder.append("Cheque Book, ");
            }
            if (s5.isSelected()) {
                servicesBuilder.append("Internet Banking, ");
            }
            if (s6.isSelected()) {
                servicesBuilder.append("Email & SMS Alerts, ");
            }
            if (s7.isSelected()) {
                servicesBuilder.append("I hereby declare that the above information is correct. ");
            }
            String services = servicesBuilder.toString().trim();

            try {
                if (a_type_group == null || a_type_group.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Account Type is Required");
                } else {
                    Conn conn = new Conn();
                    String query1 = "insert into signup3 values(?, ?, ?, ?, ?)";
                    PreparedStatement ps1 = conn.getConnection().prepareStatement(query1);
                    ps1.setString(1, formno);
                    ps1.setString(2, a_type_group);
                    ps1.setString(3, c_number);
                    ps1.setString(4, p_number);
                    ps1.setString(5, services);
                    ps1.executeUpdate();

                    String query2 = "insert into login values(?, ?, ?)";
                    PreparedStatement ps2 = conn.getConnection().prepareStatement(query2);
                    ps2.setString(1, formno);
                    ps2.setString(2, c_number);
                    ps2.setString(3, p_number);
                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Card Number: " + c_number + "\n Pin Number: " + p_number);
                    setVisible(false);
                    new Deposit(p_number).setVisible(true);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Signup3("");
    }
}
