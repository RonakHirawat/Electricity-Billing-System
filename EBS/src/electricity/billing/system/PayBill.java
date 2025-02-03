//The PayBill class is part of the electricity billing system, allowing users to view their bills and pay them. It provides a graphical user interface (GUI) for displaying bill details such as meter number, customer name, units used, total bill amount, and status. Users can select a month for which they want to view the bill and can proceed to pay it. 
//The PayBill class serves as a user interface for customers to view and pay their electricity bills based on their meter number. It retrieves relevant information from a database, displays it to the user, and allows them to update the payment status. The class employs Swing for the GUI and utilizes SQL queries for database operations. 
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener{//PayBill extends JFrame, which allows it to create a window for displaying the billing information and implements ActionListener for handling button click events.
    Choice cmonth;
    JButton pay, back;
    String meter;
    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(300, 150, 900, 600); //The constructor initializes the GUI window
        
        JLabel heading = new JLabel("Electicity Bill"); //A heading "Electricity Bill" is created and added to the frame.
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);
        //Labels are created to display the meter number, customer name, units used, total bill, and status.
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 200, 20);//The meter number label is created but left empty initially.The customer name label is also created but left empty initially.
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);
        
        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        JLabel lblunits = new JLabel("Units"); //Labels for units, total bill, and status are created but initially left empty.
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);
        
        JLabel labelunits = new JLabel("");
        labelunits.setBounds(300, 260, 200, 20);
        add(labelunits);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);
        
        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);
        
        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'"); 
            while(rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = 'January'"); //The constructor queries the database to fetch customer details and bill information for January using the provided meter number:
            while(rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cmonth.addItemListener(new ItemListener(){ //An ItemListener is added to the Choice dropdown to update the bill information whenever the selected month changes:
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'"); //Inside this listener, another query fetches the bill details for the selected month.
                    while(rs.next()) {
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE); //background white
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png")); //An image of a bill is added to the frame for aesthetic purposes.
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) { 
            try {
                Conn c = new Conn();////When clicked, it updates the bill status to "Paid" for the selected month:
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' AND month='"+cmonth.getSelectedItem()+"'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);//After processing, it closes the current window and opens a new Paytm frame
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new PayBill("");//The main method creates an instance of the PayBill class, passing an empty string as the meter number to initiate the GUI.
    }
}
