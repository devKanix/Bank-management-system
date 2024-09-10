
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class Signup extends JFrame implements ActionListener {
    long random;
    JTextField fullnameTextField, emailTextField, pnoTextField, addressTextField, pinTextField;
    JButton next;
    JRadioButton male, female, other;
    JDateChooser dateChooser;

    Signup() {

        Random ran = new Random();
        random = Math.abs(ran.nextLong() % 9000L) + 1000L;

        JLabel formno = new JLabel("Application Form No. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 30));
        formno.setBounds(200, 20, 600, 40);
        add(formno);

        JLabel personal = new JLabel("Page 1: Personal Details");
        personal.setFont(new Font("Raleway", Font.BOLD, 20));
        personal.setBounds(280, 70, 600, 40);
        add(personal);

        JLabel fullname = new JLabel("Full Name:");
        fullname.setFont(new Font("Raleway", Font.BOLD, 15));
        fullname.setBounds(100, 130, 600, 40);
        add(fullname);

        fullnameTextField = new JTextField();
        fullnameTextField.setBounds(300, 130, 300, 40);
        add(fullnameTextField);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 15));
        dob.setBounds(100, 190, 600, 40);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 190, 300, 40);
        add(dateChooser);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 15));
        gender.setBounds(100, 250, 600, 40);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 250, 80, 40);
        male.setBackground(Color.lightGray);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(400, 250, 80, 40);
        female.setBackground(Color.lightGray);
        add(female);

        other = new JRadioButton("Other");
        other.setBounds(500, 250, 80, 40);
        other.setBackground(Color.lightGray);
        add(other);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
        gendergroup.add(other);

        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.BOLD, 15));
        email.setBounds(100, 310, 600, 40);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setBounds(300, 310, 300, 40);
        add(emailTextField);

        JLabel pno = new JLabel("Phone Number:");
        pno.setFont(new Font("Raleway", Font.BOLD, 15));
        pno.setBounds(100, 370, 600, 40);
        add(pno);

        pnoTextField = new JTextField();
        pnoTextField.setBounds(300, 370, 300, 40);
        add(pnoTextField);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 15));
        address.setBounds(100, 430, 600, 40);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setBounds(300, 430, 300, 40);
        add(addressTextField);

        JLabel pcode = new JLabel("Pin Code:");
        pcode.setFont(new Font("Raleway", Font.BOLD, 15));
        pcode.setBounds(100, 490, 600, 40);
        add(pcode);

        pinTextField = new JTextField();
        pinTextField.setBounds(300, 490, 300, 40);
        add(pinTextField);

        next = new JButton("Next");
        next.setBounds(520, 550, 80, 40);
        next.addActionListener(this);
        add(next);

        setTitle("KMB Signup Page");
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setSize(800, 800);
        setVisible(true);
        setLocation(350, 10);
    }

    public void actionPerformed(ActionEvent ae) {
        Random random = new Random();
        String formno = "" + random.nextLong();
        String fullname = fullnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        } else if (other.isSelected()) {
            gender = "Other";
        }
        String email = emailTextField.getText();
        String phonno = pnoTextField.getText();
        String address = addressTextField.getText();
        String pincode = pinTextField.getText();

        try {
            if (fullname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else if (gender == null) {
                JOptionPane.showMessageDialog(null, "Gender is Required");
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email is Required");
            } else if (phonno.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phone Number is Required");
            } else if (address.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Address is Required");
            } else if (pincode.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pin  Code is Required");
            } else {
                Conn c = new Conn();
                String query = "insert into signup values(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = c.getConnection().prepareStatement(query);
                ps.setString(1, formno);
                ps.setString(2, fullname);
                ps.setString(3, dob);
                ps.setString(4, gender);
                ps.setString(5, email);
                ps.setString(6, phonno);
                ps.setString(7, address);
                ps.setString(8, pincode);
                ps.executeUpdate();

                setVisible(false);
                new Signup2(formno).setVisible(true);
                c.closeConnection(); // Close the connection after use
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        new Signup();
    }
}
