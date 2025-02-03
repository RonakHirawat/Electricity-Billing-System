//The Conn.java class is responsible for establishing a connection between the Java application and a MySQL database in your electricity billing system.

package electricity.billing.system;

import java.sql.*;

public class Conn {

    Connection c;
    Statement s;
    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///ebs", "root", "Wastepeople@21"); //The constructor (Conn()) uses DriverManager.getConnection() to establish a connection to the ebs database.
            s = c.createStatement(); //	A Statement object (s) is created, allowing the program to execute SQL queries on the connected database.
        } catch (Exception e) {
            e.printStackTrace(); //error handling
        }
    }
}
