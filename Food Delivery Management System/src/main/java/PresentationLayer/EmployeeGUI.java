package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The type Employee gui.
 */
public class EmployeeGUI implements Observer {
    private JPanel EmployeePanel;
    private JTextArea orderArea;

    /**
     * Instantiates a new Employee gui.
     *
     * @param service the service
     */
    public EmployeeGUI(DeliveryService service){

        service.addObserver(this);

    }

    /**
     * Gets employee panel.
     *
     * @return the employee panel
     */
    public JPanel getEmployeePanel() {
        return EmployeePanel;
    }

    /**
     * Sets employee panel.
     *
     * @param employeePanel the employee panel
     */
    public void setEmployeePanel(JPanel employeePanel) {
        EmployeePanel = employeePanel;
    }

    /**
     * Gets order area.
     *
     * @return the order area
     */
    public JTextArea getOrderArea() {
        return orderArea;
    }

    /**
     * Sets order area.
     *
     * @param orderArea the order area
     */
    public void setOrderArea(JTextArea orderArea) {
        this.orderArea = orderArea;
    }

    @Override
    public void update(Observable o, Object arg) {
        List<MenuItem> mem = (List<MenuItem>)arg;
        orderArea.setText("");
        for(MenuItem m: mem)
        {
            orderArea.setText(orderArea.getText() + m.getTitle() + "; ");
        }
    }
}
