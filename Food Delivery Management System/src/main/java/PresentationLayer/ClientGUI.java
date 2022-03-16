package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * The type Client gui.
 */
public class ClientGUI {
    private JPanel ClientPanel;
    private JButton viewSearchButton;
    private JTextField ratingField;
    private JButton orderButton;
    private JTable table;
    private JTextField orderField;
    private JTextField titleTextField;
    private JTextField caloriesTextField;
    private JTextField proteinsTextField;
    private JTextField fatsTextField;
    private JTextField sodiumTextField;
    private JTextField priceTextField;
    private JTextField finalField;

    /**
     * Set header.
     */
    public void setHeader(){

        DefaultTableModel tb = (DefaultTableModel) table.getModel();
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

        DefaultTableModel tb = (DefaultTableModel) table.getModel();
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
     * Instantiates a new Client gui.
     *
     * @param service  the service
     * @param username the username
     */
    public ClientGUI(DeliveryService service,JTextField username){
        setHeader();
        viewSearchButton.addActionListener(e->{
            setContent(service.searchContent(titleTextField,ratingField,caloriesTextField,proteinsTextField,fatsTextField,sodiumTextField,priceTextField));

        });
        orderButton.addActionListener(e->{
            service.createOrder(orderField,finalField,username);
        });

    }

    /**
     * Gets client panel.
     *
     * @return the client panel
     */
    public JPanel getClientPanel() {
        return ClientPanel;
    }

    /**
     * Sets client panel.
     *
     * @param clientPanel the client panel
     */
    public void setClientPanel(JPanel clientPanel) {
        ClientPanel = clientPanel;
    }
}
