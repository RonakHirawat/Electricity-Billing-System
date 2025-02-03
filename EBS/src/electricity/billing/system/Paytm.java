//The Paytm class is part of the electricity billing system, and it provides a graphical user interface (GUI) for users to make payments through the Paytm online payment service. The class is implemented using Java Swing for GUI components.
// The Paytm class provides a user interface for making payments through Paytm by loading the Paytm payment page within a scrollable editor pane. It includes a "Back" button that allows users to return to the previous screen (the bill payment screen). The class employs Java Swing for the GUI and handles user actions through an event listener.
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{

    String meter;//his variable holds the meter number, which is passed to the constructor.
    JButton back;
    Paytm(String meter) {
        this.meter = meter;//he constructor takes a String meter as an argument, initializing the meter variable:
        
        JEditorPane j = new JEditorPane(); //A JEditorPane is created, which is a component that can display HTML content. It is set to non-editable:
        j.setEditable(false);
        
        try {
            j.setPage("https://paytm.com/online-payments"); //The code attempts to load the Paytm online payments page into the JEditorPane:
        } catch (Exception e) {
            j.setContentType("text/html"); //If there is an exception (e.g., if the URL cannot be loaded), it sets the content type to HTML and displays an error message:
            j.setText("<html>Could not load<html>");
            
        }
        
        JScrollPane pane = new JScrollPane(j); //he JEditorPane is added to a JScrollPane, which provides scroll bars if the content exceeds the viewable area. This JScrollPane is then added to the frame:
        add(pane);
        
        back = new JButton("Back"); //A "Back" button is created, which is positioned within the frame. An ActionListener is added to it:
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800, 600);//The size and location of the frame are set, and it is made visible:
        setLocation(400, 150);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) { // Back button- When clicked, it sets the current frame to invisible and creates a new instance of the PayBill class, passing the meter number:
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args) {
        new Paytm(""); //The main method creates an instance of the Paytm class with an empty string as the meter number to initiate the GUI:
    }
}
