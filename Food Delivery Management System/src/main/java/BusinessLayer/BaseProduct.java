package BusinessLayer;

/**
 * The type Base product.
 */
public class BaseProduct extends MenuItem {


    /**
     * Instantiates a new Base product.
     *
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param proteins the proteins
     * @param fats     the fats
     * @param sodium   the sodium
     * @param price    the price
     */
    public BaseProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
    }

    @Override
    public int computePrice(){
            return price;
    }
}
