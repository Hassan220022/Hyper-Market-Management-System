package project;

public class Product {

    protected static int id = 0;
    protected String name;
    protected String category;
    protected double price;
    protected int quantity;

    public Product(String name, String category, double price, int quantity) {
        id++;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}