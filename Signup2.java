import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class Signup2 extends JFrame implements ActionListener {

    JTextField religionTextField, panTextField, aadharTextField;
    JButton next;
    JRadioButton tenth, twelfth, graduate, yesAccount, noAccount, yesCitizen, noCitizen;
    JComboBox<String> vincome;
    String formno;

    Signup2(String formno) {
        this.formno = formno;

        JLabel additional = new JLabel("Page 2: Additional Details");
        additional.setFont(new Font("Raleway", Font.BOLD, 30));
        additional.setBounds(280, 70, 600, 40);
        add(additional);

        JLabel religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 15));
        religion.setBounds(100, 130, 600, 40);
        add(religion);

        religionTextField = new JTextField();
        religionTextField.setBounds(300, 130, 300, 40);
        add(religionTextField);

        JLabel income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 15));
        income.setBounds(100, 190, 600, 40);
        add(income);

        String[] valincome = { "Below 100000", "Between 100000-300000", "Above 300000" };
        vincome = new JComboBox<>(valincome);
        vincome.setBounds(300, 190, 300, 40);
        add(vincome);

        JLabel education = new JLabel("Education:");
        education.setFont(new Font("Raleway", Font.BOLD, 15));
        education.setBounds(100, 250, 600, 40);
        add(education);

        ButtonGroup educationgroup = new ButtonGroup();
        tenth = new JRadioButton("10th Pass");
        tenth.setBounds(300, 250, 100, 40);
        tenth.setBackground(Color.lightGray);
        add(tenth);
        educationgroup.add(tenth);

        twelfth = new JRadioButton("12th Pass");
        twelfth.setBounds(400, 250, 100, 40);
        twelfth.setBackground(Color.lightGray);
        add(twelfth);
        educationgroup.add(twelfth);

        graduate = new JRadioButton("Graduate");
        graduate.setBounds(500, 250, 100, 40);
        graduate.setBackground(Color.lightGray);
        add(graduate);
        educationgroup.add(graduate);

        JLabel pan = new JLabel("PAN Number:");
        pan.setFont(new Font("Raleway", Font.BOLD, 15));
        pan.setBounds(100, 310, 600, 40);
        add(pan);

        panTextField = new JTextField();
        panTextField.setBounds(300, 310, 300, 40);
        add(panTextField);

        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setFont(new Font("Raleway", Font.BOLD, 15));
        aadhar.setBounds(100, 370, 600, 40);
        add(aadhar);

        aadharTextField = new JTextField();
        aadharTextField.setBounds(300, 370, 300, 40);
        add(aadharTextField);

        JLabel account = new JLabel("Existing Account:");
        account.setFont(new Font("Raleway", Font.BOLD, 15));
        account.setBounds(100, 430, 600, 40);
        add(account);

        yesAccount = new JRadioButton("Yes");
        yesAccount.setBounds(300, 430, 60, 40);
        yesAccount.setBackground(Color.lightGray);
        add(yesAccount);

        noAccount = new JRadioButton("No");
        noAccount.setBounds(400, 430, 60, 40);
        noAccount.setBackground(Color.lightGray);
        add(noAccount);

        ButtonGroup accountgroup = new ButtonGroup();
        accountgroup.add(yesAccount);
        accountgroup.add(noAccount);

        JLabel scitizen = new JLabel("Senior Citizen:");
        scitizen.setFont(new Font("Raleway", Font.BOLD, 15));
        scitizen.setBounds(100, 490, 600, 40);
        add(scitizen);

        yesCitizen = new JRadioButton("Yes");
        yesCitizen.setBounds(300, 490, 60, 40);
        yesCitizen.setBackground(Color.lightGray);
        add(yesCitizen);

        noCitizen = new JRadioButton("No");
        noCitizen.setBounds(400, 490, 60, 40);
        noCitizen.setBackground(Color.lightGray);
        add(noCitizen);

        ButtonGroup citizengroup = new ButtonGroup();
        citizengroup.add(yesCitizen);
        citizengroup.add(noCitizen);

        next = new JButton("Next");
        next.setBounds(520, 550, 80, 40);
        next.addActionListener(this);
        add(next);

        setTitle("KMB Additional Details for Signup Page");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setSize(800, 800);
        setVisible(true);
        setLocation(350, 10);
    }

    public void actionPerformed(ActionEvent ae) {
        String formno = ""; // Initialize formno variable
        // Assuming formno is initialized somewhere in the class

        String religion = religionTextField.getText();
        String income = (String) vincome.getSelectedItem();
        String education = null;
        if (tenth.isSelected()) {
            education = "10th Pass";
        } else if (twelfth.isSelected()) {
            education = "12th Pass";
        } else if (graduate.isSelected()) {
            education = "Graduate";
        }
        String pan = panTextField.getText();
        String aadhar = aadharTextField.getText();
        String account = null;
        if (yesAccount.isSelected()) {
            account = "Yes";
        } else if (noAccount.isSelected()) {
            account = "No";
        }
        String citizen = null;
        if (yesCitizen.isSelected()) {
            citizen = "Yes";
        } else if (noCitizen.isSelected()) {
            citizen = "No";
        }
        try {
            Conn c = new Conn();
            String query = "insert into signup2 values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.getConnection().prepareStatement(query);
            ps.setString(1, formno);
            ps.setString(2, religion);
            ps.setString(3, income);
            ps.setString(4, education);
            ps.setString(5, pan);
            ps.setString(6, aadhar);
            ps.setString(7, account);
            ps.setString(8, citizen);
            ps.executeUpdate();

            setVisible(false);
            new Signup3(formno).setVisible(true);
            c.closeConnection(); // Close the connection after use
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new Signup2("");
    }
}
