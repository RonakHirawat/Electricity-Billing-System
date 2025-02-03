//The UpdateInformation class serves as a user interface for updating customer details in the electricity billing system. It allows users to enter new values for their address, city, state, email, and phone number based on their meter number, and it updates the information in the database accordingly. 

package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{

    JTextField tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton update, cancel;
    String meter;
    JLabel name;
    UpdateInformation(String meter) { //Takes a String meter representing the meter number of the customer whose information will be updated.
        this.meter = meter;
        setBounds(300, 150, 1050, 450); //1050x450 pixels
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION"); // A label for the title of the window ("UPDATE CUSTOMER INFORMATION") is created and added.
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);
        
        JLabel lblname = new JLabel("Name"); //Labels for various fields (Name, Meter Number, Address, City, State, Email, Phone) are created and added.
        lblname.setBounds(30, 70, 100, 20);
        add(lblname);
        
        name = new JLabel("");
        name.setBounds(230, 70, 200, 20);
        add(name);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30, 110, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(230, 110, 200, 20);
        add(meternumber);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 150, 100, 20);
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(230, 150, 200, 20);
        add(tfaddress);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30, 190, 100, 20);
        add(lblcity);
        
        tfcity = new JTextField();
        tfcity.setBounds(230, 190, 200, 20);
        add(tfcity);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30, 230, 100, 20);
        add(lblstate);
        
        tfstate = new JTextField();
        tfstate.setBounds(230, 230, 200, 20);
        add(tfstate);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(30, 270, 100, 20);
        add(lblemail);
        
        tfemail = new JTextField(); //Corresponding JTextField components (tfaddress, tfcity, tfstate, tfemail, tfphone) are created for user input and added to the window.
        tfemail.setBounds(230, 270, 200, 20);
        add(tfemail);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 310, 100, 20);
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(230, 310, 200, 20);
        add(tfphone);
        
        try { //A try-catch block attempts to retrieve customer information from the database using the provided meter number.
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'"); //SQL query fetches customer details from the customer table based on the meter number.
            while(rs.next()) { //The fetched details are populated into the respective labels and text fields.
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        update = new JButton("Update"); //Two buttons, Update and Cancel, are created and added to the frame.
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(70, 360, 100, 25);
        add(update);
        update.addActionListener(this); //The Update button is set up to trigger the actionPerformed method when clicked.
        
        cancel = new JButton("Cancel"); //The Cancel button simply closes the window when clicked.
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230, 360, 100, 25);
        add(cancel);
        cancel.addActionListener(this);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg")); //An image (update.jpg) is loaded and displayed on the right side of the window to enhance the UI.
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 50, 400, 300);
        add(image);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) { //It retrieves the values entered in the text fields.
            String address = tfaddress.getText(); //A SQL UPDATE query is executed to update the customer's information in the database.
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            
            try {
                Conn c = new Conn(); //A SQL UPDATE query is executed to update the customer's information in the database.
                c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully"); //Upon successful update, a success message is shown using JOptionPane, and the window is closed.
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false); //The window is simply closed without any updates.
        }
    }

    public static void main(String[] args) {
        new UpdateInformation(""); //The entry point of the application, which creates a new instance of the UpdateInformation class with an empty meter number (presumably meant for testing or demonstration).
    }
}
