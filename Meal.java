public class Meal {
    private int mealID;
    private String name;
    private double price;

    public Meal(int mealID, String name, double price) {
        this.mealID = mealID;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public int getMealID() { return mealID; }
    public void setMealID(int mealID) { this.mealID = mealID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return mealID + "," + name + "," + price;
    }
}
