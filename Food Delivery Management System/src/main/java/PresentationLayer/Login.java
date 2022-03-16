package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.User;

import javax.swing.*;
import java.awt.*;

/**
 * The type Login.
 */
public class Login extends Component {
    private JTextField username;
    private JPasswordField password;
    private JButton Register;
    private JButton Login;
    private JPanel LoginPanel;

    /**
     * Instantiates a new Login.
     *
     * @param service the service
     */
    public Login(DeliveryService service){
        Login.addActionListener(e -> {

                String u,p;
                u = username.getText();
                p = password.getText();
                for(User s: service.getUserList())
                {
                    if(u.equals(s.getUsername()) && p.equals(s.getPassword())) {

                        if(s.getType().equals("Admin")){
                            JFrame frame = new JFrame("AdministratorGUI");
                            AdministratorGUI fr = new AdministratorGUI(service);
                            frame.setContentPane(fr.getAdminPanel());
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                        }
                        if(s.getType().equals("Client")){
                            JFrame frame = new JFrame("ClientGUI");
                            ClientGUI fr = new ClientGUI(service,username);
                            frame.setContentPane(fr.getClientPanel());
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                        }
                        if(s.getType().equals("Employee")){

                            JFrame frame = new JFrame("EmployeeGUI");
                            EmployeeGUI fr = new EmployeeGUI(service);
                            frame.setContentPane(fr.getEmployeePanel());
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                        }


                    }

    } } );
        Register.addActionListener(e->{
            String u,p;
            u = username.getText();
            p = password.getText();
            int s = service.getUserList().size();
            service.getUserList().add(new User(u,p,"Client",s+1));
        });
    }

    /**
     * Gets login panel.
     *
     * @return the login panel
     */
    public JPanel getLoginPanel() {
        return LoginPanel;
    }

    /**
     * Sets login panel.
     *
     * @param loginPanel the login panel
     */
    public void setLoginPanel(JPanel loginPanel) {
        LoginPanel = loginPanel;
    }
}
