package Empoyee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Order.*;
import User.User;

class Sales extends User {

    private List<Product> products;
    private Order order;

    public Sales(String name, String username, String password) {
        super(username, password, "Sales");
        this.products = new ArrayList<>();
    }

    public void SetUsername(String username) throws SQLException {
        setUsername(username);
    }

    public void SetPassword(String password) throws SQLException {
        setPassword(password);
    }

    public List<Product> SearchProduct(String keyword) throws SQLException {
        return Product.searchProduct(keyword);
    }

    public List<Product> GetAllProduct() {
        return Product.getAllProducts();
    }

    public void makeOrder(Product product, int quantity) throws SQLException { // TODO: implement this
        // Logic to create an order for the given product and quantity

    }

    public void cancelOrder() throws SQLException {
        order.cancelOrder();
    }

    public void addProductOrder(Product product) throws SQLException {
        order.addProduct(product);
    }

    public void deleteProduct(Product product) throws SQLException {// check if it updates with the database
        order.removeProduct(product);
        products.remove(product);// remove product from list
    }

    public Product findProductById(int productId) throws SQLException {
        return Product.findProductById(productId);
    }

    public double getTotalPrice() throws SQLException { // TODO: implement this
        return order.getTotalPrice();
    }

}
