package BusinessLayer;

import java.io.Serializable;

/**
 * The type Menu item.
 */
public abstract class MenuItem implements Serializable {
    /**
     * The Title.
     */
    protected String title;
    /**
     * The Rating.
     */
    protected double rating;
    /**
     * The Calories.
     */
    protected int calories;
    /**
     * The Proteins.
     */
    protected int proteins;
    /**
     * The Fats.
     */
    protected int fats;
    /**
     * The Sodium.
     */
    protected int sodium;
    /**
     * The Price.
     */
    protected int price;

    /**
     * Instantiates a new Menu item.
     *
     * @param title    the title
     * @param rating   the rating
     * @param calories the calories
     * @param proteins the proteins
     * @param fats     the fats
     * @param sodium   the sodium
     * @param price    the price
     */
    public MenuItem(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets calories.
     *
     * @return the calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets calories.
     *
     * @param calories the calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Gets proteins.
     *
     * @return the proteins
     */
    public int getProteins() {
        return proteins;
    }

    /**
     * Sets proteins.
     *
     * @param proteins the proteins
     */
    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    /**
     * Gets fats.
     *
     * @return the fats
     */
    public int getFats() {
        return fats;
    }

    /**
     * Sets fats.
     *
     * @param fats the fats
     */
    public void setFats(int fats) {
        this.fats = fats;
    }

    /**
     * Gets sodium.
     *
     * @return the sodium
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Sets sodium.
     *
     * @param sodium the sodium
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
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
     * Compute price int.
     *
     * @return the int
     */
    abstract int computePrice();

}
