package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.List;

/**
 * The type Administrator gui.
 */
public class AdministratorGUI implements Serializable {
    private JPanel AdminPanel;
    private JTextField priceField;
    private JTextField sodField;
    private JTextField fatField;
    private JTextField protField;
    private JTextField calField;
    private JTextField ratingField;
    private JTextField titleField;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton importItemsButton;
    private JTable table1;
    private JTextField startHourTextField;
    private JTextField finishHourTextField;
    private JTextField nrOfTimesOrderedTextField;
    private JTextField nrOfTimesOrderedTextField1;
    private JButton intervalReportButton;
    private JButton ordersInADayButton;
    private JButton productReportButton;
    private JButton clientReportButton;
    private JTextField specifiedPriceTextField;
    private JTextField specifiedDayTextField;
    private JButton viewButton;
    private JButton addCompButton;
    private JTextField compField;
    private JTextField baseField;
    private JTextArea reportArea;

    /**
     * Set header.
     */
    public void setHeader(){

        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        String[] col= new String[7];
        col[0]="Title";
        col[1]="Rating";
        col[2]="Calories";
        col[3]="Proteins";
        col[4]="Fat";
        col[5]="Sodium";
        col[6]="Price";

        tb.setColumnIdentifiers(col);

    }

    /**
     * Set content.
     *
     * @param mem the mem
     */
    public void setContent(List<MenuItem> mem){

        DefaultTableModel tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        for(MenuItem m: mem)
        {

            Object[] ob=new Object[7];
            ob[0]=m.getTitle();
            ob[1]=m.getRating();
            ob[2]=m.getCalories();
            ob[3]=m.getProteins();
            ob[4]=m.getFats();
            ob[5]=m.getSodium();
            ob[6]=m.getPrice();
            tb.addRow(ob);
        }

    }

    /**
     * Instantiates a new Administrator gui.
     *
     * @param service the service
     */
    public AdministratorGUI(DeliveryService service){
            setHeader();
            importItemsButton.addActionListener(e->{
                service.importProducts();

            });
            viewButton.addActionListener(e->{
                setContent(service.getItemList());
            });
            addButton.addActionListener(e->{
                service.addItem(titleField,priceField,ratingField,calField,protField,fatField,sodField);
            });
            deleteButton.addActionListener(e->
            {
                service.removeItem(titleField);
            });
            modifyButton.addActionListener(e -> {
                service.updateItem(titleField,ratingField,calField,protField,fatField,sodField,priceField);
            });
            addCompButton.addActionListener(e-> {
                service.createCompositeItem(compField, baseField);
            });
            intervalReportButton.addActionListener(e->{
                List<Order> orders =service.reportHour(startHourTextField, finishHourTextField);
                reportArea.setText("");
                for(Order o: orders)
                {
                    reportArea.setText(reportArea.getText() + " IdOrder: " + o.getOrderId() + "; IdClient: " + o.getClientId() + "; OrderDate: " + o.getOrderDate() + "\n" );
                }

            });
            productReportButton.addActionListener(e->{
                List<MenuItem> items=service.reportProduct(nrOfTimesOrderedTextField);
                reportArea.setText("");
                for(MenuItem m: items)
                {
                    reportArea.setText(reportArea.getText() + " Title: " + m.getTitle() + "\n" );
                }
            });
            clientReportButton.addActionListener(e->{
                List<User> users=service.reportClient(nrOfTimesOrderedTextField1, specifiedPriceTextField);
                reportArea.setText("");
                for(User m: users)
                {
                    reportArea.setText(reportArea.getText() + " User: " + m.getUsername() + "\n" );
                }
            });
            ordersInADayButton.addActionListener(e->{
                List<Order> orders = service.reportDay(specifiedDayTextField);
                reportArea.setText("");
                for(Order o: orders)
                {
                    reportArea.setText(reportArea.getText() + " IdOrder: " + o.getOrderId() + "; IdClient: " + o.getClientId() + "; OrderDate: " + o.getOrderDate() + "\n" );
                }
            });


    }

    /**
     * Gets admin panel.
     *
     * @return the admin panel
     */
    public JPanel getAdminPanel() {
        return AdminPanel;
    }

    /**
     * Sets admin panel.
     *
     * @param adminPanel the admin panel
     */
    public void setAdminPanel(JPanel adminPanel) {
        AdminPanel = adminPanel;
    }
}
