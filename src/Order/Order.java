package Order;

import java.sql.SQLException;
import java.util.*;

class Order {
    private int id;
    private int customerId;
    private List<Product> products;
    private double totalPrice;

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addProduct(Product product) throws SQLException {
        products.add(product);
        totalPrice += product.getPrice();
        System.out.println("Product added to the order: " + product.getName());
    }

    public void removeProduct(Product product) throws SQLException {
        if (products.remove(product)) {
            totalPrice -= product.getPrice();
            System.out.println("Product removed from the order: " + product.getName());
        } else {
            System.out.println("Product not found in the order: " + product.getName());
        }
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Customer ID: " + customerId + ", Total Price: $" + totalPrice;
    }
}