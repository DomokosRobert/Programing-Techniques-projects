package presentation;

import bll.ClientBLL;
import bll.OrderrBLL;
import bll.ProductBLL;
import model.Client;
import model.Orderr;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Gui.
 */
public class GUI {
    private JButton find;
    private JButton insert;
    private JButton update;
    private JButton delete;
    private JTabbedPane tabbedPane1;
    private JPanel Order1;
    private JPanel Product1;
    private JPanel Client1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTable table1;
    private JPanel FirstPanel;
    private JTextField textField13;

    /**
     * Gets first panel.
     *
     * @return the first panel
     */
    public JPanel getFirstPanel() {
        return FirstPanel;
    }

    /**
     * Write in table.
     *
     * @param i the
     * @param o the o
     */
    public void writeInTable(int i,List<Object> o)
    {
        DefaultTableModel tab = (DefaultTableModel) table1.getModel();
        tab.setColumnCount(0);
        tab.setRowCount(0);
        Class c;
        if(i == 0)
            c = Client.class;
        else if(i == 1)
            c = Product.class;
        else
            c = Orderr.class;
        int j=0;
        String[] arr = new String[c.getDeclaredFields().length];
        for(Field f: c.getDeclaredFields()){
            f.setAccessible(true);
            arr[j++]=f.getName();
        }
        tab.setColumnIdentifiers(arr);
        for(Object ob: o){
            List<String> st = new ArrayList<>();
            for(Field f: c.getDeclaredFields()){
               f.setAccessible(true);
                try {
                    st.add(f.get(ob).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            tab.addRow(st.toArray());
        }

    }

    /**
     * Instantiates a new Gui.
     */
    public GUI() {
        find.addActionListener(e -> {
            int i = tabbedPane1.getSelectedIndex();

            List<Object> o = new ArrayList<>();
            if(i == 0)
            {
                ClientBLL bll= new ClientBLL();
                o.addAll(bll.findAllClients());
            }
            if(i == 1)
            {
                ProductBLL bll= new ProductBLL();
                o.addAll(bll.findAllProducts());
            }
            if(i == 2)
            {
                OrderrBLL bll= new OrderrBLL();
                o.addAll(bll.findAllOrderrs());
            }
            writeInTable(i,o);

        });
        insert.addActionListener(e -> {
            if(tabbedPane1.getSelectedIndex()==0) {
                Client c = new Client(Integer.parseInt(textField1.getText()),textField2.getText(), textField3.getText(),textField4.getText(),Integer.parseInt(textField5.getText()));
                ClientBLL bll = new ClientBLL();
                bll.insertClient(c);
            }
            if(tabbedPane1.getSelectedIndex()==1) {
                Product p = new Product(Integer.parseInt(textField6.getText()),textField7.getText(),Integer.parseInt(textField8.getText()),Double.parseDouble(textField13.getText()));
                ProductBLL bll = new ProductBLL();
                bll.insertProduct(p);
            }
            if(tabbedPane1.getSelectedIndex()==2) {
                Orderr o = new Orderr(Integer.parseInt(textField9.getText()),Integer.parseInt(textField10.getText()),Integer.parseInt(textField11.getText()),Integer.parseInt(textField12.getText()));
                OrderrBLL bll = new OrderrBLL();
                bll.insertOrderr(o);
            } });
        update.addActionListener(e -> {
            if(tabbedPane1.getSelectedIndex()==0) {
                Client c = new Client(Integer.parseInt(textField1.getText()),textField2.getText(), textField3.getText(),textField4.getText(),Integer.parseInt(textField5.getText()));
                ClientBLL bll = new ClientBLL();
                bll.updateClient(c);
            }
            if(tabbedPane1.getSelectedIndex()==1) {
                Product p = new Product(Integer.parseInt(textField6.getText()),textField7.getText(),Integer.parseInt(textField8.getText()),Double.parseDouble(textField13.getText()));
                ProductBLL bll = new ProductBLL();
                bll.updateProduct(p);
            } });
        delete.addActionListener(e -> {
            if(tabbedPane1.getSelectedIndex()==0) {
                int c = Integer.parseInt(textField1.getText());
                ClientBLL bll = new ClientBLL();
                bll.deleteClient(c);
            }
            if(tabbedPane1.getSelectedIndex()==1) {
                int p = Integer.parseInt(textField6.getText());
                ProductBLL bll = new ProductBLL();
                bll.deleteProduct(p);
            }
            if(tabbedPane1.getSelectedIndex()==2){
                int o = Integer.parseInt(textField9.getText());
                OrderrBLL bll = new OrderrBLL();
                bll.deleteOrderr(o);
            } });
    }
}
