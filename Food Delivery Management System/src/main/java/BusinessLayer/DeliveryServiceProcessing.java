package BusinessLayer;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * The interface Delivery service processing.
 */
public interface DeliveryServiceProcessing extends Serializable {

    /**
     * Import products.
     */
    void importProducts();

    /**
     * Add item.
     *
     * @pre t0!=null
     * @param t0 the t 0
     * @param t1 the t 1
     * @param t2 the t 2
     * @param t3 the t 3
     * @param t4 the t 4
     * @param t5 the t 5
     * @param t6 the t 6
     */
    void addItem(JTextField t0, JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6);

    /**
     * Remove item.
     * @pre t0!=null
     * @param t0 the t 0
     */
    void removeItem(JTextField t0);

    /**
     * Update item.
     * @pre t0!=null
     * @param t0 the t 0
     * @param t1 the t 1
     * @param t2 the t 2
     * @param t3 the t 3
     * @param t4 the t 4
     * @param t5 the t 5
     * @param t6 the t 6
     */
    void updateItem(JTextField t0, JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6);

    /**
     * Create composite item.
     * @pre t0!=null
     * @param t0 the t 0
     * @param t1 the t 1
     */
    void createCompositeItem(JTextField t0, JTextField t1);

    /**
     * Create order.
     * @pre t0!=null
     * @param t0 the t 0
     * @param t1 the t 1
     * @param t2 the t 2
     */
    void createOrder(JTextField t0, JTextField t1, JTextField t2);

    /**
     * Report hour list.
     * @pre t0!=null
     * @post list != null
     * @param t0 the t 0
     * @param t1 the t 1
     * @return the list
     */
    List<Order> reportHour(JTextField t0,JTextField t1);

    /**
     * Report product list.
     * @pre t0!=null
     * @post list != null
     * @param t0 the t 0
     * @return the list
     */
    List<MenuItem> reportProduct(JTextField t0);

    /**
     * Report client list.
     * @pre t0!=null
     * @post list != null
     * @param t0 the t 0
     * @param t1 the t 1
     * @return the list
     */
    List<User> reportClient(JTextField t0,JTextField t1);

    /**
     * Report day list.
     * @pre t0!=null
     * @post list != null
     * @param t0 the t 0
     * @return the list
     */
    List<Order> reportDay(JTextField t0);

    /**
     * Generate bill.
     * @param o order
     */
    void generateBill(Order o) throws IOException;

    /**
     * Search content list.
     * @pre t0!=null
     * @post list != null
     * @param t0 the t 0
     * @param t1 the t 1
     * @param t2 the t 2
     * @param t3 the t 3
     * @param t4 the t 4
     * @param t5 the t 5
     * @param t6 the t 6
     * @return the list
     */
    List<MenuItem> searchContent(JTextField t0, JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6);
}
