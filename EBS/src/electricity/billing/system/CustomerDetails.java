//The CustomerDetails.java class is designed to display customer information in a GUI table format within your electricity billing system.
//In essence, this class fetches customer details from the database and displays them in a table format, with an option to print the displayed information.

package electricity.billing.system;

import java.awt.*; //Imports the AWT classes (like Color) for additional GUI functionality.
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; //JDBC to table models
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{ //using action listner

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;
    
    CustomerDetails(){
        super("Customer Details");
        
        setSize(1200, 650); //window size in pixels
        setLocation(200, 150);
        
        table = new JTable(); //	It utilizes a JTable to present customer data fetched from the database.
        
        try {
            Conn c = new Conn(); //establish a connection to the database.
            ResultSet rs = c.s.executeQuery("select * from customer"); // SQL query (select * from customer) retrieves all customer records.
            
            table.setModel(DbUtils.resultSetToTableModel(rs)); //retrieved data is displayed in the table using 
        } catch (Exception e) {
            e.printStackTrace(); //error handling
        }
        
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        print = new JButton("Print"); //	A "Print" button is added to the window, allowing users to print the table contents.
        print.addActionListener(this);
        add(print, "South");
        
        
        setVisible(true); //o	The window is made visible using setVisible(true).
    }
    
    public void actionPerformed(ActionEvent ae) { //	The actionPerformed method triggers the printing action when the button is clicked.
        try {
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}
