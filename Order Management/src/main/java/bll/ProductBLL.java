package bll;


import bll.validators.Validator;
import dao.ProductDAO;
import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Product bll.
 */
public class ProductBLL {

    private ProductDAO productDAO;

    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Find product by id product.
     *
     * @param id the id
     * @return the product
     */
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Find all products list.
     *
     * @return the list
     */
    public List<Product> findAllProducts(){
        List<Product> p;
        p = productDAO.findAll();
        if (p.isEmpty()) {
            throw new NoSuchElementException("No product found!");
        }
        return p;
    }

    /**
     * Insert product.
     *
     * @param p the p
     */
    public void insertProduct(Product p){
        productDAO.insert(p);
    }

    /**
     * Update product.
     *
     * @param p the p
     */
    public void updateProduct(Product p){
        productDAO.update(p);
    }

    /**
     * Delete product.
     *
     * @param id the id
     */
    public void deleteProduct(int id){
        productDAO.delete(id);
    }
}
