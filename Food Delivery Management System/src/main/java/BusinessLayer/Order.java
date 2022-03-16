package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private LocalDate orderDate;
    private GregorianCalendar hour;
    private int price;

    /**
     * Instantiates a new Order.
     *
     * @param orderId   the order id
     * @param clientId  the client id
     * @param orderDate the order date
     */
    public Order(int orderId, int clientId, LocalDate orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.hour = new GregorianCalendar();
        this.price=0;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets hour.
     *
     * @return the hour
     */
    public GregorianCalendar getHour() {
        return hour;
    }

    /**
     * Sets hour.
     *
     * @param hour the hour
     */
    public void setHour(GregorianCalendar hour) {
        this.hour = hour;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderDate,hour);
    }
}
