/*
 * Created by JFormDesigner on Tue Jun 14 20:05:44 CDT 2016
 */

package com.cs521.team7;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

import com.cs521.team7.model.Users.User;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * @author Saurabh Jadhav
 */
public class SignUpPage extends JFrame {
    public SignUpPage() {
        initComponents();
    }

    private void btn_cancelActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }

    private void btn_signUpActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (textField_firstName.getText().length() > 0 &&
                textField_lastName.getText().length() > 0 &&
                textField_emailID.getText().length() > 0 &&
                textField_password.getText().length() > 0 &&
                textField_password.getText().equals(textField_confirmPassword.getText())) {
            Connection db = DatabaseConnection.getConnection();
            PreparedStatement stmt = null;
            try {

				stmt = db.prepareStatement("INSERT INTO cs521team7.user " +
						"(username,password)" +
						" VALUES (?,?)");
				stmt.setString(1, textField_userName.getText());
				stmt.setString(2, textField_password.getText());
				stmt.executeUpdate();
                stmt = db.prepareStatement("INSERT INTO cs521team7.customer " +
                        "(fname,lname,emailid,username)" +
                        " VALUES (?,?,?,?)");

                stmt.setString(1, textField_firstName.getText());
                stmt.setString(2, textField_lastName.getText());
                stmt.setString(3, textField_emailID.getText());
                stmt.setString(4, textField_userName.getText());
                stmt.executeUpdate();
                this.setVisible(false);
                new HomePage(new User(textField_userName.getText(),textField_password.getText())).setVisible(true);
            }
            catch (MySQLIntegrityConstraintViolationException e2)
            {
                JOptionPane.showMessageDialog(null, "User name already exists", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else
            JOptionPane.showMessageDialog(null, "Enter valid info", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Abhijeet Kedari
		label_firstName = new JLabel();
		textField_firstName = new JTextField();
		label_lastName = new JLabel();
		textField_lastName = new JTextField();
		label_emailID = new JLabel();
		textField_emailID = new JTextField();
		label_username = new JLabel();
		textField_userName = new JTextField();
		label_password = new JLabel();
		textField_password = new JPasswordField();
		label_confirmPassword = new JLabel();
		textField_confirmPassword = new JPasswordField();
		btn_cancel = new JButton();
		btn_signUp = new JButton();

		//======== this ========
		setIconImage(new ImageIcon(getClass().getResource("/logo/sonos_logo.jpg")).getImage());
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default, $lcgap, pref, 2*($lcgap, pref:grow), $lcgap, pref",
			"3*(default, $lgap), default:grow, 3*($lgap, default), $lgap, default:grow, $lgap, default"));

		//---- label_firstName ----
		label_firstName.setText("First Name");
		contentPane.add(label_firstName, CC.xy(1, 1));
		contentPane.add(textField_firstName, CC.xywh(3, 1, 7, 1, CC.FILL, CC.DEFAULT));

		//---- label_lastName ----
		label_lastName.setText("Last Name");
		contentPane.add(label_lastName, CC.xy(1, 3));
		contentPane.add(textField_lastName, CC.xywh(3, 3, 7, 1, CC.FILL, CC.DEFAULT));

		//---- label_emailID ----
		label_emailID.setText("Enter Email ID");
		contentPane.add(label_emailID, CC.xy(1, 5));
		contentPane.add(textField_emailID, CC.xywh(3, 5, 7, 1, CC.FILL, CC.DEFAULT));

		//---- label_username ----
		label_username.setText("Enter Username");
		contentPane.add(label_username, CC.xy(1, 9));
		contentPane.add(textField_userName, CC.xywh(3, 9, 7, 1, CC.FILL, CC.DEFAULT));

		//---- label_password ----
		label_password.setText("Enter Password");
		contentPane.add(label_password, CC.xy(1, 11));
		contentPane.add(textField_password, CC.xywh(3, 11, 7, 1, CC.FILL, CC.DEFAULT));

		//---- label_confirmPassword ----
		label_confirmPassword.setText("Confirm Password");
		contentPane.add(label_confirmPassword, CC.xy(1, 13));
		contentPane.add(textField_confirmPassword, CC.xywh(3, 13, 7, 1, CC.FILL, CC.DEFAULT));

		//---- btn_cancel ----
		btn_cancel.setText("Cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_cancelActionPerformed(e);
			}
		});
		contentPane.add(btn_cancel, CC.xy(3, 17));

		//---- btn_signUp ----
		btn_signUp.setText("Sign Up");
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_signUpActionPerformed(e);
			}
		});
		contentPane.add(btn_signUp, CC.xy(9, 17));
		setSize(305, 290);
		setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Abhijeet Kedari
	private JLabel label_firstName;
	private JTextField textField_firstName;
	private JLabel label_lastName;
	private JTextField textField_lastName;
	private JLabel label_emailID;
	private JTextField textField_emailID;
	private JLabel label_username;
	private JTextField textField_userName;
	private JLabel label_password;
	private JPasswordField textField_password;
	private JLabel label_confirmPassword;
	private JPasswordField textField_confirmPassword;
	private JButton btn_cancel;
	private JButton btn_signUp;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
