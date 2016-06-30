/*
 * Created by JFormDesigner on Tue Jun 14 19:53:39 CDT 2016
 */

package com.cs521.team7;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import com.cs521.team7.model.Users.User;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import java.sql.PreparedStatement;

/**
 * @author Saurabh Jadhav
 */
public class LoginPage extends JFrame {
    public LoginPage() {
        initComponents();
        this.setTitle("Login");
    }

    private void signUP_btn_ActionPerformed(ActionEvent e) {
        // TODO add your code here
        SignUpPage signUpPage=new SignUpPage();
        signUpPage.setVisible(true);
        this.setVisible(false);
    }

    private void btn_loginActionPerformed(ActionEvent e) {
        // TODO add your code here
        if(textField_uname.getText().length()>0 && textField_password.getText().length()>0)
        {

            Connection db=DatabaseConnection.getConnection();
            try {
                PreparedStatement stmt = db.prepareStatement("SELECT username,password,type FROM cs521team7.user WHERE username=? AND password=?");
                stmt.setString(1, textField_uname.getText());
                stmt.setString(2, textField_password.getText());
                ResultSet rs = stmt.executeQuery();
                //if username found

                if(rs.next()) {
                    System.out.println(rs.getString("username"));
                    if(rs.getString("type").equals("CUSTOMER")) {
                        HomePage homePage = new HomePage(new User(rs.getString("username"),rs.getString("password")));
                        System.out.println(rs.getString("type"));
                        //homePage.setSize(homePage.getMaximumSize());
                        homePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        // homePage.setUndecorated(true);
                        homePage.setVisible(true);
                        this.setVisible(false);
                    }
                    else
                    {
                        AdminHomePage adminHomePage = new AdminHomePage();
                        System.out.println(rs.getString("type"));
                        //homePage.setSize(homePage.getMaximumSize());
                        adminHomePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        // homePage.setUndecorated(true);
                        adminHomePage.setVisible(true);
                        this.setVisible(false);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Incorrect Username/Password","Error",JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null,"Database Error","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Incorrect Username/Password","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Saurabh Jadhav
        label_uname = new JLabel();
        textField_uname = new JTextField();
        label_password = new JLabel();
        textField_password = new JPasswordField();
        btn_signUp = new JButton();
        btn_login = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/logo/sonos_logo.jpg")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "3*(default, $lcgap), default:grow, $lcgap, min, $lcgap, default",
            "2*(default, $lgap), top:default"));

        //---- label_uname ----
        label_uname.setText("Username: ");
        contentPane.add(label_uname, CC.xy(1, 1));
        contentPane.add(textField_uname, CC.xywh(3, 1, 9, 1, CC.FILL, CC.DEFAULT));

        //---- label_password ----
        label_password.setText("Password: ");
        contentPane.add(label_password, CC.xy(1, 3));
        contentPane.add(textField_password, CC.xywh(3, 3, 9, 1, CC.FILL, CC.DEFAULT));

        //---- btn_signUp ----
        btn_signUp.setText("Sign Up");
        btn_signUp.addActionListener(e -> signUP_btn_ActionPerformed(e));
        contentPane.add(btn_signUp, CC.xy(9, 5, CC.DEFAULT, CC.TOP));

        //---- btn_login ----
        btn_login.setText("Login");
        btn_login.addActionListener(e -> btn_loginActionPerformed(e));
        contentPane.add(btn_login, CC.xy(11, 5, CC.LEFT, CC.TOP));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Saurabh Jadhav
    private JLabel label_uname;
    private JTextField textField_uname;
    private JLabel label_password;
    private JPasswordField textField_password;
    private JButton btn_signUp;
    private JButton btn_login;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
