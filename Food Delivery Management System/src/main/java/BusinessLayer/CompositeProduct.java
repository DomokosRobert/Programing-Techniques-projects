package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Composite product.
 */
public class CompositeProduct extends MenuItem {
    private List<MenuItem> itemList= new ArrayList<>();

    /**
     * Instantiates a new Composite product.
     *
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param proteins the proteins
     * @param fats     the fats
     * @param sodium   the sodium
     * @param price    the price
     * @param itemList the item list
     */
    public CompositeProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price, List<MenuItem> itemList) {
        super(title, rating, calories, proteins, fats, sodium, price);
        this.itemList = itemList;
    }

    /**
     * Add.
     *
     * @param men the men
     */
    public void add(MenuItem men){
        itemList.add(men);
    }

    @Override
    public int computePrice(){
        return itemList.stream().mapToInt(MenuItem::computePrice).sum();
    }


}
