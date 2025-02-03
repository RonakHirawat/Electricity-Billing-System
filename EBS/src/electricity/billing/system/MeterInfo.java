//The MeterInfo.java class is part of the electricity billing system that allows users to input and submit information regarding a specific meter.
//The MeterInfo class is designed to collect and submit meter-related information in a structured manner. It provides an interactive interface for users to input necessary details, which are then stored in a database. The class leverages Swing components for the GUI and implements basic database interaction using SQL.
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener{

    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone; //Text fields for various input fields (though not all are used in the provided code).
    JButton next, cancel; //Buttons for submitting the form and possibly canceling (though cancel functionality is not implemented).
    JLabel lblmeter; //Label for meter information display.
    Choice meterlocation, metertype, phasecode, billtype; //Dropdowns for selecting meter location, type, phase code, and billing type.
    String meternumber; //Stores the meter number passed to the constructor.
    MeterInfo(String meternumber) {
        this.meternumber = meternumber;
        
        setSize(700, 500); //dimensions in pixels
        setLocation(400, 200);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230)); //light blue background
        add(p);
        //several labels
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);
        
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240, 80, 100, 20);
        p.add(lblmeternumber);
        
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);
        
        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 200, 20);
        p.add(meterlocation);
        
        JLabel lbladdress = new JLabel("Meter Type");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);
        
        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240, 160, 200, 20);
        p.add(metertype);
        
        JLabel lblcity = new JLabel("Phase Code");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);
        
        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");
        phasecode.setBounds(240, 200, 200, 20);
        p.add(phasecode);
        
        JLabel lblstate = new JLabel("Bill Type");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);
        
        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industial");
        billtype.setBounds(240, 240, 200, 20);
        p.add(billtype);
        
        JLabel lblemail = new JLabel("Days");
        lblemail.setBounds(100, 280, 100, 20);
        p.add(lblemail);
        
        JLabel lblemails = new JLabel("30 Days");
        lblemails.setBounds(240, 280, 100, 20);
        p.add(lblemails);
        
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100, 320, 100, 20);
        p.add(lblphone);
        
        JLabel lblphones = new JLabel("By Default Bill is calculated for 30 days only"); // Labels are added to show default days for billing and a note about billing duration (30 days).
        lblphones.setBounds(240, 320, 500, 20);
        p.add(lblphones);
        
        next = new JButton("Submit"); // A "Submit" button is created, styled, and set to trigger an action when clicked.
        next.setBounds(220, 390, 100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg")); //An image is added to the left of the panel using ImageIcon, enhancing the GUI's appearance.
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE); //The main panel is added to the frame, and the background is set to white.
        
        setVisible(true); //display
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) { //Collects the selected values from the dropdowns and constructs a SQL INSERT query to add meter information to the database:
            String meter = meternumber;//submit button
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+code+"', '"+typebill+"', '"+days+"')";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query); //executes query using conn
                                         //If successful, it shows a success message and closes the current window.
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false); //If another button (not implemented) is clicked, it closes the window.
        }
    }
    
    public static void main(String[] args) {
        new MeterInfo(""); //The main method creates an instance of MeterInfo, passing an empty string for the meter number. This starts the GUI.
    }
}
