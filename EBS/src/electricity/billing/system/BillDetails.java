//Bill Details Fucntion
//It connects to a database, fetches bill details based on the meter number, and displays the results in a table within a scrollable window
package electricity.billing.system;

import javax.swing.*; //API for GUI
import java.awt.*; //Imports the AWT classes (like Color) for additional GUI functionality.
import java.sql.*;
import net.proteanit.sql.DbUtils; //JDBC to table models

public class BillDetails extends JFrame{

    BillDetails(String meter) {
        
        setSize(700, 650);  //size of window in pixels
        setLocation(400, 150);
        
        getContentPane().setBackground(Color.WHITE); //background color
        
        JTable table = new JTable(); //display billing details in tabular format
        
        try {
            Conn c = new Conn(); //connects to database
            String query = "select * from bill where meter_no = '"+meter+"'";
            ResultSet rs = c.s.executeQuery(query); // fetches data from mysql so that the meter number matches the given input 
            
            table.setModel(DbUtils.resultSetToTableModel(rs)); //The ResultSet from the query is converted into a table model using DbUtils, making it easy to display in the JTable.
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table); //The data is shown inside a scrollable pane (JScrollPane), allowing the user to scroll through the records.
        sp.setBounds(0, 0, 700, 650);
        add(sp);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails(""); //The main method creates a new instance of BillDetails, launching the window (currently with an empty meter number).
    }
}
