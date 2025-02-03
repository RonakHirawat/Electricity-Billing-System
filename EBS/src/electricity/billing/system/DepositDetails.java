//The DepositDetails.java class displays deposit information based on selected meter number and month in a GUI.
//also lets us print the results

package electricity.billing.system;

import java.awt.*; //Imports the AWT classes (like Color) for additional GUI functionality.
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; ////JDBC to table models
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener{ //window titled "Deposit Details" and implements ation listner

    Choice meternumber, cmonth; //o	Includes dropdowns for selecting a meter number (populated from the customer table) and a month (January to December).
    JTable table; //o	A JTable shows bill information, initially filled by querying the bill table.
    JButton search, print;
    
    DepositDetails(){
        super("Deposit Details");
        
        setSize(700, 700);
        setLocation(400, 100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber = new JLabel("Search By Meter Number"); 
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);
        
        meternumber = new Choice();
        meternumber.setBounds(180, 20, 150, 20);
        add(meternumber);
        
        try {
            Conn c  = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(400, 20, 100, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(520, 20, 150, 20);
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
        
        table = new JTable(); //o	A JTable shows bill information, initially filled by querying the bill table.
         
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs)); ////Initializes the table by querying the bill table and populating it with data using DbUtils.resultSetToTableModel().
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);  //Adds the table to a JScrollPane to allow scrolling through the displayed data.
        sp.setBounds(0, 100, 700, 600);
        add(sp);
        
        search = new JButton("Search"); //o	A "Search" button updates the table with results based on selected meter number and month.
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print"); //o	A "Print" button allows printing of the displayed table.
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) { //The actionPerformed method checks which button was clicked
        if (ae.getSource() == search) { //If the "Search" button is pressed, it constructs a query to filter the results based on the selected meter number and month, then updates the table with the results.
            String query = "select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";
            
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                
            }
        } else  { //If the "Print" button is pressed, it attempts to print the content of the table.
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositDetails(); //The main method creates an instance of the DepositDetails class, initializing the GUI and making it visible.
    }
}
