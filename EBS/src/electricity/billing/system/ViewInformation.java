//The ViewInformation class is part of an electricity billing system and serves as a graphical user interface (GUI) to display customer information based on their meter number.

package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener{

    JButton cancel;
    ViewInformation(String meter) { //Takes a String meter that represents the meter number of the customer whose information will be displayed.
        setBounds(350, 150, 850, 650); //850x650 pixels
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION"); //A label for the title of the window ("VIEW CUSTOMER INFORMATION") is created and positioned at the top of the frame.
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);
        // Several JLabel components are created to display customer details
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(70, 140, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(250, 140, 100, 20);
        add(meternumber);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);
        
        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);
        
        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        add(lblstate);
        
        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500, 140, 100, 20);
        add(lblemail);
        
        JLabel email = new JLabel("");
        email.setBounds(650, 140, 100, 20);
        add(email);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 200, 100, 20);
        add(lblphone);
        
        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        add(phone);
        
        try { //A try-catch block is used to fetch customer data from the database using the provided meter number.
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'"); //SQL query retrieves customer details from the customer table where the meter number matches the provided input.
            while(rs.next()) {
                name.setText(rs.getString("name")); //The fetched details are then set to the respective labels using setText().
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cancel = new JButton("Cancel"); //A JButton labeled "Cancel" is created and added to the frame.
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 340, 100, 25); //The button's background color is set to black, and the text color is set to white. Its position is determined using setBounds().
        add(cancel);
        cancel.addActionListener(this); //An ActionListener is attached to the button, which will close the window when the button is clicked.
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg")); //An image (viewcustomer.jpg) is loaded from the classpath and displayed in the window. It is scaled to fit the designated area.
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {//When the Cancel button is clicked, the window is closed by setting its visibility to false
        setVisible(false);
    }
    
    public static void main(String[] args) {
        new ViewInformation("");//The entry point of the application, which creates a new instance of the ViewInformation class with an empty meter number (likely for testing or demonstration purposes).
    }
}
