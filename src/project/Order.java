package project;

public class Order {

    protected static int id = 0;
    private int productId;
    private int quantity;
    private double totalPrice;

    public Order(int id, int productId, int quantity, double totalPrice) {
        id++;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}