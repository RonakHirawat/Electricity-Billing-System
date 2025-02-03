// The GenerateBill.java class generates and displays an electricity bill based on the selected meter number and month within a GUI.
// This class serves as a user interface for generating and displaying electricity bills based on selected criteria. It retrieves and displays detailed billing information by querying a database, providing a comprehensive view of the customer's billing status.
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;  //Imports the AWT classes (like Color) for additional GUI functionality.
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{ //The GenerateBill class extends JFrame to create a window and implements ActionListener for handling button actions.

    String meter; //String meter: Stores the meter number passed to the constructor.
    JButton bill; //JButton bill: A button for generating the bill.
    Choice cmonth; //Choice cmonth: A dropdown for selecting the month.
    JTextArea area; //JTextArea area: A text area for displaying the generated bill information.

    GenerateBill(String meter) {
        this.meter = meter;
        
        setSize(500, 800); //dimensions in pixels
        setLocation(550, 30);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(); //Creates a panel containing labels for the heading and selected meter number, and populates the cmonth dropdown with month names.
        
        JLabel heading = new JLabel("Generate Bill"); //Initializes the area text area with a default message indicating to click the button to generate the bill.
        JLabel meternumber = new JLabel(meter);
        
        cmonth = new Choice();
        
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
        
        area = new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));
        
        JScrollPane pane = new JScrollPane(area); //Scroll Pane: Wraps the area in a JScrollPane to allow scrolling through the bill details.
        
        bill = new JButton("Generate Bill"); //Button Setup: Adds the "Generate Bill" button and sets up an action listener.

        bill.addActionListener(this);
        
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        add(panel, "North");
        
        add(pane, "Center");
        add(bill, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            
            String month = cmonth.getSelectedItem();
            
            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF "+month+", 2022\n\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
         //Fetches customer details based on the selected meter number and appends them to the area.
            if(rs.next()) {
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                area.append("\n    Address             : " + rs.getString("address"));
                area.append("\n    City                 : " + rs.getString("city"));
                area.append("\n    State                : " + rs.getString("state"));
                area.append("\n    Email                : " + rs.getString("email"));
                area.append("\n    Phone                 : " + rs.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from meter_info where meter_no = '"+meter+"'");
         //Retrieves meter information (location, type, phase code, etc.) and adds it to the display.
            if(rs.next()) {
                area.append("\n    Meter Location: " + rs.getString("meter_location"));
                area.append("\n    Meter Type:     " + rs.getString("meter_type"));
                area.append("\n    Phase Code:        " + rs.getString("phase_code"));
                area.append("\n    Bill Type:          " + rs.getString("bill_type"));
                area.append("\n    Days:                " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from tax");
         //Retrieves tax details, including costs, service charges, and fixed taxes, appending them to the bill.
            if(rs.next()) {
                area.append("\n");
                area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent:     " + rs.getString("cost_per_unit"));
                area.append("\n    Service Charge:        " + rs.getString("service_charge"));
                area.append("\n    Service Tax:          " + rs.getString("service_charge"));
                area.append("\n    Swacch Bharat Cess:                " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax: " + rs.getString("fixed_tax"));
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' and month='"+month+"'");
         //Retrieves the billing information for the selected month, including units consumed and total charges, and displays them.
            if(rs.next()) {
                area.append("\n");
                area.append("\n    Current Month: " + rs.getString("month"));
                area.append("\n    Units Consumed:     " + rs.getString("units"));
                area.append("\n    Total Charges:        " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace(); //Catches exceptions and prints stack traces in case of errors during database queries.
        }
    }

    public static void main(String[] args) {
        new GenerateBill(""); //The main method creates an instance of GenerateBill with an empty meter string to initialize the GUI.
    }
}
