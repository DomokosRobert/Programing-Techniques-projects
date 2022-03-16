package bll;

import bll.validators.OrderQuantityValidator;
import bll.validators.Validator;
import dao.OrderrDAO;
import model.Client;
import model.Orderr;
import model.Product;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Orderr bll.
 */
public class OrderrBLL {
    private List<Validator<Orderr>> validators;
    private OrderrDAO orderrDAO;
    private Writer bill;

    /**
     * Instantiates a new Orderr bll.
     */
    public OrderrBLL(){
        validators = new ArrayList<>();
        validators.add(new OrderQuantityValidator());
        orderrDAO = new OrderrDAO();
        try {
            bill = new FileWriter("Bill.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find orderr by id orderr.
     *
     * @param id the id
     * @return the orderr
     */
    public Orderr findOrderrById(int id) {
        Orderr st = orderrDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Find all orderrs list.
     *
     * @return the list
     */
    public List<Orderr> findAllOrderrs(){
        List<Orderr> o;
        o = orderrDAO.findAll();
        if (o.isEmpty()) {
            throw new NoSuchElementException("No order found!");
        }
        return o;
    }

    /**
     * Write txt string for the bill.
     *
     * @param o the o
     * @return the string
     */
    public String writeTxt(Orderr o){
        String s = "";
        ClientBLL cbll = new ClientBLL();
        ProductBLL pbll = new ProductBLL();
        int pid,cid;
        pid = o.getIdProduct();
        cid = o.getIdClient();
        Client c = cbll.findClientById(cid);
        Product p = pbll.findProductById(pid);
        s = s + c.getName() + " bought " + o.getQuantity() + " " + p.getName() + " at the price " + p.getPrice();
        return s;
    }

    /**
     * Insert orderr.
     *
     * @param o the o
     */
    public void insertOrderr(Orderr o){
        for(Validator<Orderr> v:validators)
            v.validate(o);
        ProductBLL bll = new ProductBLL();
        int id = o.getIdProduct();
        Product p = bll.findProductById(id);
        p.setQuantity(p.getQuantity()-o.getQuantity());
        bll.updateProduct(p);
        orderrDAO.insert(o);
        String s = writeTxt(o);
        try {
            bill.write(s);
            bill.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete orderr.
     *
     * @param id the id
     */
    public void deleteOrderr(int id){ orderrDAO.delete(id);
    }
}
