/*
 * Created by JFormDesigner on Tue Jun 14 20:31:10 CDT 2016
 */

package com.cs521.team7;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.event.*;

import com.cs521.team7.model.Users.User;
import com.cs521.team7.model.Users.shopping.Cart;
import com.cs521.team7.model.Users.shopping.CartItem;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.sun.rowset.CachedRowSetImpl;


/**
 * @author Saurabh Jadhav
 */
public class HomePage extends JFrame implements RowSetListener  {
    private User user;
    HomePageProductTableModel homePageProductTableModel;
    Connection db;
    Cart cart;
    public HomePage(User user) throws SQLException {
        this.user=user;
        initComponents();
        cart=new Cart();
        db=DatabaseConnection.getConnection();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Window is closed..");
                try {
                    db.close();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        this.setTitle("Home Page");
        CachedRowSet myCachedRowSet = getContentsOfProductTable();
        homePageProductTableModel = new HomePageProductTableModel(myCachedRowSet);
        homePageProductTableModel.addEventHandlersToRowSet(this);

         // Displays the table
        table_ProductList.setModel(homePageProductTableModel);
        table_ProductList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table_ProductList.getColumnModel().getColumn(0).setMinWidth(0);
        table_ProductList.getColumnModel().getColumn(0).setMaxWidth(0);
        table_ProductList.getColumnModel().getColumn(0).setWidth(0);
        table_ProductList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (table_ProductList.getSelectedRow() > -1) {
                    // print first column value from selected row
                    System.out.println("ff "+table_ProductList.getValueAt(table_ProductList.getSelectedRow(), 1));
                }
            }
        });
        table_ProductList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    System.out.println("Hi");
                    System.out.println("double "+table_ProductList.getValueAt(table_ProductList.getSelectedRow(), 0));
                    try {
                        PreparedStatement stmt=db.prepareStatement("select idproduct,name,description,artistname,albumname,release_year,price from cs521team7.product where idproduct=?");

                        String str=table_ProductList.getValueAt(table_ProductList.getSelectedRow(), 0).toString();
                        int productID=Integer.parseInt(str);
                        stmt.setInt(1,productID);
                        ResultSet rs = stmt.executeQuery();
                        //if username found

                        if(rs.next()) {
                            String details="Product Name: "+rs.getString("name")
                                    +"\nDescription: "+rs.getString("description")
                                    +"\nArtist Name: "+rs.getString("artistname")
                                    +"\nAlbum Name: "+rs.getString("albumname")
                                    +"\nRelease Year: "+rs.getString("release_year")
                                    +"\nPrice: $"+rs.getFloat("price");
                            JOptionPane.showMessageDialog(null,details,"Product Details",JOptionPane.PLAIN_MESSAGE);
                            }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private void btn_addToCartActionPerformed(ActionEvent e) {
        // TODO add your code here
        String product=table_ProductList.getValueAt(table_ProductList.getSelectedRow(), 0).toString();
        String price=table_ProductList.getValueAt(table_ProductList.getSelectedRow(), 6).toString();
        System.out.println(cart.getTotalCartPrice());
        cart.addProductToCart(new CartItem(Integer.parseInt(product),1,Float.parseFloat(price)));
        label_totalCartItems.setText(String.valueOf(cart.getTotalItems()));
        label_totalCartPrice.setText("$"+String.valueOf(cart.getTotalCartPrice()));
        //System.out.println(cart);

    }
    private CachedRowSet getContentsOfProductTable() {

        // write the codd to fetch the records form reservation table
        // setting for scroll option

        CachedRowSet crs = null;
        try {
            db = DatabaseConnection.getConnection();
            crs = new CachedRowSetImpl();
            crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
            crs.setUsername("team7");
            crs.setPassword("team1234");

            // In MySQL, to disable auto-commit, set the property
            // relaxAutoCommit to
            // true in the connection URL.

            crs.setUrl("jdbc:mysql://cs521.c4lyzaywi83r.us-east-1.rds.amazonaws.com:3306/cs521team7?relaxAutoCommit=true");

            // Regardless of the query, fetch the contents of COFFEES

            crs.setCommand("select idproduct,name,description,artistname,albumname,release_year,price from cs521team7.product");
            crs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return crs;

    }

    private void createNewTableModel() throws SQLException {
        HomePageProductTableModel homePageProductTableModel= new HomePageProductTableModel(getContentsOfProductTable());
        homePageProductTableModel.addEventHandlersToRowSet(this);
        table_ProductList.setModel(homePageProductTableModel);
    }
    private void menu_helpActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void menu_aboutActionPerformed(ActionEvent e) {
        // TODO add your code here
      //JOptionPane.showMessageDialog(null,"Sonos,\n\t-developed by Saurabh Jadhav");
        JEditorPane aboutPane=new JEditorPane();
        aboutPane.setContentType("text/html");
//add
            String about="<html>\n" +
                    "\n" +
                    "<body>\n" +
                    "<table>\n" +

                    "\n" +
                    "    <tr><td style=\"font-family:Papyrus;\"><b><i><h4>SONOS</h4></i></b></td></tr>\n" +
                    "    <tr><td style=\"font-family:Papyrus;\"><b>Developed By</b></td></tr>\n" +
                    "    <tr><td style=\"font-family:Papyrus;\">-Saurabh Jadhav\t    (A20359388)</td></tr>\n" +
                    "    <tr><td style=\"font-family:Papyrus;\">-Abhijeet Kedari \t(A20353562)</td></tr>\n" +
                    "    <tr><td style=\"font-family:Papyrus;\">-Sampada Pingle \t    (A20361180)</td></tr>\n" +
                    "    <tr><td style=\"font-family:Papyrus;\">-Ketan Aswani     \t(A20356793)</td></tr>\n" +
                    "    <tr><td style=\"font-family:Papyrus;\">-Vijaykumar Kannan\t(A20341296)</td></tr>\n" +
                    "\n" +
                    "\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>\n";
            aboutPane.setText(about);
         
        JOptionPane.showMessageDialog(null,aboutPane,"About US",JOptionPane.NO_OPTION);



    }

    private void menu_aboutMenuKeyPressed(MenuKeyEvent e) {
        // TODO add your code here
    }
    @Override
    public void rowSetChanged(RowSetEvent event) {

    }

    @Override
    public void rowChanged(RowSetEvent event) {

        CachedRowSet currentRowSet = this.homePageProductTableModel.productRowSet;

        try {
            currentRowSet.moveToCurrentRow();
            homePageProductTableModel = new HomePageProductTableModel(homePageProductTableModel.getCoffeesRowSet());
            table_ProductList.setModel(homePageProductTableModel);

        } catch (SQLException ex) {

            ex.printStackTrace();

            // Display the error in a dialog box.

            JOptionPane.showMessageDialog(HomePage.this, new String[] { // Display
                    // a
                    // 2-line
                    // message
                    ex.getClass().getName() + ": ", ex.getMessage() });
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {

    }

    private void menuItem_updateProfileActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        ProfilePage profilePage=new ProfilePage(user);
        profilePage.setVisible(true);
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Saurabh Jadhav
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        menuBar1 = new JMenuBar();
        menu_file = new JMenu();
        menuItem1 = new JMenuItem();
        menu_help = new JMenu();
        menu_faq = new JMenuItem();
        menu_about = new JMenuItem();
        menu1 = new JMenu();
        menuItem_updateProfile = new JMenuItem();
        panel1 = new JPanel();
        label_welcome = new JLabel();
        btn_account = new JButton();
        btn_cart = new JButton();
        btn_logout = new JButton();
        separator2 = new JSeparator();
        splitPane2 = new JSplitPane();
        panel2 = new JPanel();
        label2 = compFactory.createLabel("Total Cost of Cart:  ");
        label_totalCartPrice = compFactory.createLabel("            $0");
        label1 = new JLabel();
        label_totalCartItems = new JLabel();
        separator4 = new JSeparator();
        label8 = new JLabel();
        panel3 = new JPanel();
        btn_search = new JButton();
        label5 = new JLabel();
        textField_searchBox = new JTextField();
        label6 = new JLabel();
        comboBox_filter = new JComboBox();
        label7 = new JLabel();
        comboBox_sort = new JComboBox();
        separator3 = new JSeparator();
        scrollPane1 = new JScrollPane();
        table_ProductList = new JTable();
        btn_addToCart = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/logo/sonos_logo.jpg")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(new FormLayout(
            "default:grow",
            "2*(default, $lgap), default"));

        //======== menuBar1 ========
        {

            //======== menu_file ========
            {
                menu_file.setText("Files");

                //---- menuItem1 ----
                menuItem1.setText("Exit");
                menu_file.add(menuItem1);
            }
            menuBar1.add(menu_file);

            //======== menu_help ========
            {
                menu_help.setText("Help");
                menu_help.addActionListener(e -> menu_helpActionPerformed(e));

                //---- menu_faq ----
                menu_faq.setText("FAQ");
                menu_help.add(menu_faq);

                //---- menu_about ----
                menu_about.setText("About");
                menu_about.addActionListener(e -> {
			menu_aboutActionPerformed(e);
			menu_aboutActionPerformed(e);
		});
                menu_about.addMenuKeyListener(new MenuKeyListener() {
                    @Override
                    public void menuKeyPressed(MenuKeyEvent e) {
                        menu_aboutMenuKeyPressed(e);
                    }
                    @Override
                    public void menuKeyReleased(MenuKeyEvent e) {}
                    @Override
                    public void menuKeyTyped(MenuKeyEvent e) {}
                });
                menu_help.add(menu_about);
            }
            menuBar1.add(menu_help);

            //======== menu1 ========
            {
                menu1.setText("Profile");

                //---- menuItem_updateProfile ----
                menuItem_updateProfile.setText("Update Profile");
                menuItem_updateProfile.addActionListener(e -> menuItem_updateProfileActionPerformed(e));
                menu1.add(menuItem_updateProfile);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new FormLayout(
                "default, $lcgap, default:grow, $lcgap, default, $lcgap, default:grow, 10*($lcgap, default)",
                "default"));

            //---- label_welcome ----
            label_welcome.setText("Welcome ");
            panel1.add(label_welcome, CC.xy(1, 1, CC.CENTER, CC.DEFAULT));

            //---- btn_account ----
            btn_account.setText("My Account");
            panel1.add(btn_account, CC.xy(23, 1, CC.RIGHT, CC.TOP));

            //---- btn_cart ----
            btn_cart.setText("Cart");
            panel1.add(btn_cart, CC.xy(25, 1, CC.FILL, CC.DEFAULT));

            //---- btn_logout ----
            btn_logout.setText("Logout");
            panel1.add(btn_logout, CC.xy(27, 1));
        }
        contentPane.add(panel1, CC.xy(1, 1, CC.FILL, CC.DEFAULT));
        contentPane.add(separator2, CC.xy(1, 3, CC.FILL, CC.CENTER));

        //======== splitPane2 ========
        {

            //======== panel2 ========
            {
                panel2.setLayout(new FormLayout(
                    "min, default:grow",
                    "4*(default, $lgap), default"));
                panel2.add(label2, CC.xy(1, 1, CC.LEFT, CC.DEFAULT));
                panel2.add(label_totalCartPrice, CC.xywh(2, 1, 1, 2, CC.LEFT, CC.TOP));

                //---- label1 ----
                label1.setText("Total Items in Cart: ");
                panel2.add(label1, CC.xy(1, 3, CC.LEFT, CC.DEFAULT));

                //---- label_totalCartItems ----
                label_totalCartItems.setText("            0");
                panel2.add(label_totalCartItems, CC.xy(2, 3, CC.LEFT, CC.DEFAULT));
                panel2.add(separator4, CC.xywh(1, 5, 2, 1, CC.FILL, CC.DEFAULT));

                //---- label8 ----
                label8.setText("Browse");
                panel2.add(label8, CC.xy(1, 7, CC.LEFT, CC.DEFAULT));
            }
            splitPane2.setLeftComponent(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new FormLayout(
                    "left:default, 2*($lcgap, default), $lcgap, default:grow, 5*($lcgap, default), $lcgap, default:grow, 6*($lcgap, default)",
                    "4*(default, $lgap), 2*(default:grow, $lgap), default, $lgap, default:grow, 2*($lgap, default), $lgap, default:grow"));

                //---- btn_search ----
                btn_search.setText("Search");
                panel3.add(btn_search, CC.xywh(31, 1, 1, 5, CC.FILL, CC.DEFAULT));

                //---- label5 ----
                label5.setText("Search Text");
                panel3.add(label5, CC.xy(1, 1));
                panel3.add(textField_searchBox, CC.xywh(3, 1, 27, 1, CC.FILL, CC.DEFAULT));

                //---- label6 ----
                label6.setText("Filter");
                panel3.add(label6, CC.xy(1, 3));
                panel3.add(comboBox_filter, CC.xywh(3, 3, 27, 1, CC.FILL, CC.DEFAULT));

                //---- label7 ----
                label7.setText("Sort");
                panel3.add(label7, CC.xy(1, 5));
                panel3.add(comboBox_sort, CC.xywh(3, 5, 27, 1));
                panel3.add(separator3, CC.xywh(1, 7, 31, 1, CC.FILL, CC.DEFAULT));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table_ProductList);
                }
                panel3.add(scrollPane1, CC.xywh(1, 9, 29, 13, CC.FILL, CC.FILL));

                //---- btn_addToCart ----
                btn_addToCart.setText("Add to Cart");
                btn_addToCart.addActionListener(e -> btn_addToCartActionPerformed(e));
                panel3.add(btn_addToCart, CC.xy(31, 9));
            }
            splitPane2.setRightComponent(panel3);
        }
        contentPane.add(splitPane2, CC.xy(1, 5, CC.RIGHT, CC.DEFAULT));
        setSize(680, 360);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Saurabh Jadhav
    private JMenuBar menuBar1;
    private JMenu menu_file;
    private JMenuItem menuItem1;
    private JMenu menu_help;
    private JMenuItem menu_faq;
    private JMenuItem menu_about;
    private JMenu menu1;
    private JMenuItem menuItem_updateProfile;
    private JPanel panel1;
    private JLabel label_welcome;
    private JButton btn_account;
    private JButton btn_cart;
    private JButton btn_logout;
    private JSeparator separator2;
    private JSplitPane splitPane2;
    private JPanel panel2;
    private JLabel label2;
    private JLabel label_totalCartPrice;
    private JLabel label1;
    private JLabel label_totalCartItems;
    private JSeparator separator4;
    private JLabel label8;
    private JPanel panel3;
    private JButton btn_search;
    private JLabel label5;
    private JTextField textField_searchBox;
    private JLabel label6;
    private JComboBox comboBox_filter;
    private JLabel label7;
    private JComboBox comboBox_sort;
    private JSeparator separator3;
    private JScrollPane scrollPane1;
    private JTable table_ProductList;
    private JButton btn_addToCart;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
