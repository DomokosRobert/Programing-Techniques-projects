package model;

/**
 * The type Orderr.
 */
public class Orderr {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    /**
     * Instantiates a new Orderr.
     *
     * @param id        the id
     * @param idClient  the id client
     * @param idProduct the id product
     * @param quantity  the quantity
     */
    public Orderr(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * Instantiates a new Orderr.
     */
    public Orderr(){

    }

    /**
     * Instantiates a new Orderr.
     *
     * @param idClient  the id client
     * @param idProduct the id product
     * @param quantity  the quantity
     */
    public Orderr(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets id client.
     *
     * @return the id client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Sets id client.
     *
     * @param idClient the id client
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Gets id product.
     *
     * @return the id product
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Sets id product.
     *
     * @param idProduct the id product
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", idClient=" + idClient + ", idProduct=" + idProduct + ", quantity=" + quantity + "]";
    }
}
