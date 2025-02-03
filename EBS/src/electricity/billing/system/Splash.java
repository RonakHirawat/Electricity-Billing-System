//The Splash class in the electricity billing system is designed to display a splash screen when the application starts. 
//The Splash class provides a visual introduction to the electricity billing system application. It shows an animated splash screen for a few seconds before transitioning to the login interface, enhancing the user experience during the application's startup.
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable { //It implements the Runnable interface, allowing the class to be executed by a thread.
    Thread t;
    Splash() {
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg")); //An image (elect.jpg) is loaded from the resources using ClassLoader.
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT); //dimensions 730x550 pixels.
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3); //A JLabel is created with the resized image and added to the frame. This effectively displays the splash image.
        add(image);
        
        setVisible(true);
        
        int x = 1;
        for (int i = 2; i < 600; i+=4, x+=1) {
            setSize(i + x, i);
            setLocation(700 - ((i + x)/2), 400 - (i/2)); // loop is used to gradually increase the size of the splash screen window.
            try { //The position of the window is adjusted to keep it centered as it expands.
                Thread.sleep(5); // introduces a slight delay to control the speed of the animation.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        t = new Thread(this); //A new thread is started by creating a Thread object with this (the current instance of the Splash class) as the target.
        t.start();
         //This thread will execute the run() method.
        setVisible(true);
    }
    
    public void run() {
        try {
            Thread.sleep(7000); //It makes the splash screen visible for 7 seconds (Thread.sleep(7000)).
            setVisible(false); //After 7 seconds, it hides the splash screen (setVisible(false)) and creates a new instance of the Login class, which presumably displays the login interface of the application.
            
            // login frame
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Splash(); //The entry point of the application, which creates a new instance of the Splash class, initiating the splash screen process.
    }
}
