package Main;

import BusinessLayer.DeliveryService;
import BusinessLayer.User;
import DataLayer.Serializator;
import PresentationLayer.Login;

import javax.swing.*;

/**
 * The type Main.
 */
public class Main {
    private static DeliveryService service;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        service = Serializator.deserializeDeliveryService();
        if(service.getUserList().size()==0) {
            User admin= new User("admin","admin","Admin",1);
            User emp= new User("emp","emp","Employee",2);
            User c1= new User("c1","c1","Client",3);
            service.getUserList().add(admin);
            service.getUserList().add(emp);
            service.getUserList().add(c1);
        }


        JFrame frame = new JFrame("Food Delivery");
        Login fr = new Login(service);
        frame.setContentPane(fr.getLoginPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
