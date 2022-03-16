package bll.validators;

import bll.ProductBLL;
import model.Orderr;
import model.Product;

/**
 * This class validates if the required quantity is smaller than the quantity of the product
 *
 */
public class OrderQuantityValidator implements Validator<Orderr>{


    @Override
    public void validate(Orderr orderr) {
        int id =orderr.getIdProduct();
        ProductBLL productBLL= new ProductBLL();
        Product p=productBLL.findProductById(id);
        if(p.getQuantity()< orderr.getQuantity()){
            throw new IllegalArgumentException("The quantity is too big for this order!");
        }
    }
}
