package com.cs521.team7;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import com.cs521.team7.model.Users.User;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Wed Jun 29 15:21:06 CDT 2016
 */



/**
 * @author abhijeet kedari
 */
public class ProfilePage extends JFrame {
    private final User user;
    Connection db = DatabaseConnection.getConnection();

    public ProfilePage(User user) {
        this.user = user;
        initComponents();
        this.setSize(700,300);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Window is closed..");
                try {
                    db.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getErrorCode());
                }
                System.exit(0);
            }

        });


        addpaymentmethods();
        setProfileAttributes(user);

    }

    private void setProfileAttributes(User user) {

        if(user != null){
            PreparedStatement stmt = null;
            try
            {
                stmt= db.prepareStatement("SELECT fname,lname,emailid,phoneno,billingaddress," +
                        "shippingaddress,paymentmethod FROM cs521team7.customer where username=?");
                stmt.setString(1,user.getUsername());
                ResultSet resultSet = stmt.executeQuery();

                if(resultSet.next())
                {
                    String fname = resultSet.getString(1);
                    String lname = resultSet.getString(2);
                    String emailid = resultSet.getString(3);
                    String phoneno = resultSet.getString(4);
                    String billingaddress = resultSet.getString(5);
                    String shippingaddress = resultSet.getString(6);
                    String paymentmethod = resultSet.getString(7);

                    txt_firstName.setText(fname);
                    txt_lastName.setText(lname);
                    txt_emailAddress.setText(emailid);
                    txt_phoneNumber.setText(phoneno);
                    txt_billingAddress.setText(billingaddress);
                    txt_shppingAddress.setText(shippingaddress);
                    combo_paymentMethods.setSelectedItem(paymentmethod);

                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


        }
        else
        {
            //Show Error message
        }

    }

    private void addpaymentmethods() {
        combo_paymentMethods.addItem("Paypal");
        combo_paymentMethods.addItem("Credit Card");
        combo_paymentMethods.addItem("Check");
    }

    private void btn_cancelActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        this.setVisible(false);
        HomePage homePage = new HomePage(user);
        homePage.setVisible(true);

    }

    private void btn_okActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        updateProfile();
        JOptionPane.showMessageDialog(null,"Profile Updated Successfully !!!","Info",JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
        HomePage homePage = new HomePage(user);
        homePage.setVisible(true);

    }

    private void updateProfile() {

        PreparedStatement stmt = null;

        try
        {
            stmt= db.prepareStatement("update  cs521team7.customer " +
                    "SET fname=?,lname=?,emailid=?,phoneno=?,billingaddress=?," +
                    "shippingaddress=?,paymentmethod=?" +
                    "where username=?");
            stmt.setString(1,txt_firstName.getText());
            stmt.setString(2,txt_lastName.getText());
            stmt.setString(3,txt_emailAddress.getText());
            stmt.setString(4,txt_phoneNumber.getText());
            stmt.setString(5,txt_billingAddress.getText());
            stmt.setString(6,txt_shppingAddress.getText());
            stmt.setString(7,combo_paymentMethods.getSelectedItem().toString());
            stmt.setString(8,user.getUsername());

            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - abhijeet kedari
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        lbl_firstName = new JLabel();
        txt_firstName = new JTextField();
        lbl_lastName = new JLabel();
        txt_lastName = new JTextField();
        lbl_billingAddress = new JLabel();
        txt_billingAddress = new JTextField();
        lbl_shippingAddress = new JLabel();
        txt_shppingAddress = new JTextField();
        lbl_emailAddress = new JLabel();
        txt_emailAddress = new JTextField();
        lbl_phoneNumber = new JLabel();
        txt_phoneNumber = new JTextField();
        lbl_paymentMethods = new JLabel();
        combo_paymentMethods = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/logo/sonos_logo.jpg")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(Borders.createEmptyBorder("7dlu, 7dlu, 7dlu, 7dlu"));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new FormLayout(
                    "default, $lcgap, default:grow",
                    "9*(default, $lgap), default"));

                //---- lbl_firstName ----
                lbl_firstName.setText("First Name");
                contentPanel.add(lbl_firstName, CC.xy(1, 1));
                contentPanel.add(txt_firstName, CC.xy(3, 1, CC.FILL, CC.DEFAULT));

                //---- lbl_lastName ----
                lbl_lastName.setText("Last Name");
                contentPanel.add(lbl_lastName, CC.xy(1, 3));
                contentPanel.add(txt_lastName, CC.xy(3, 3, CC.FILL, CC.DEFAULT));

                //---- lbl_billingAddress ----
                lbl_billingAddress.setText("Billing Address");
                contentPanel.add(lbl_billingAddress, CC.xy(1, 5));
                contentPanel.add(txt_billingAddress, CC.xy(3, 5, CC.FILL, CC.DEFAULT));

                //---- lbl_shippingAddress ----
                lbl_shippingAddress.setText("Shipping Address");
                contentPanel.add(lbl_shippingAddress, CC.xy(1, 7));
                contentPanel.add(txt_shppingAddress, CC.xy(3, 7));

                //---- lbl_emailAddress ----
                lbl_emailAddress.setText("Email Address");
                contentPanel.add(lbl_emailAddress, CC.xy(1, 9));
                contentPanel.add(txt_emailAddress, CC.xy(3, 9));

                //---- lbl_phoneNumber ----
                lbl_phoneNumber.setText("Phone Number");
                contentPanel.add(lbl_phoneNumber, CC.xy(1, 11));
                contentPanel.add(txt_phoneNumber, CC.xy(3, 11));

                //---- lbl_paymentMethods ----
                lbl_paymentMethods.setText("Payment Method");
                contentPanel.add(lbl_paymentMethods, CC.xy(1, 13));
                contentPanel.add(combo_paymentMethods, CC.xy(3, 13));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(Borders.createEmptyBorder("5dlu, 0dlu, 0dlu, 0dlu"));
                buttonBar.setLayout(new FormLayout(
                    "$glue, $button, $rgap, $button",
                    "pref"));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> {
                    try {
                        btn_okActionPerformed(e);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                });
                buttonBar.add(okButton, CC.xy(2, 1));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> {
                    try {
                        btn_cancelActionPerformed(e);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                });
                buttonBar.add(cancelButton, CC.xy(4, 1));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - abhijeet kedari
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel lbl_firstName;
    private JTextField txt_firstName;
    private JLabel lbl_lastName;
    private JTextField txt_lastName;
    private JLabel lbl_billingAddress;
    private JTextField txt_billingAddress;
    private JLabel lbl_shippingAddress;
    private JTextField txt_shppingAddress;
    private JLabel lbl_emailAddress;
    private JTextField txt_emailAddress;
    private JLabel lbl_phoneNumber;
    private JTextField txt_phoneNumber;
    private JLabel lbl_paymentMethods;
    private JComboBox combo_paymentMethods;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}