package Empoyee;

import java.util.ArrayList;
import java.util.List;

import Order.*;
import User.User;

class Sales extends User {

    private List<Product> products;

    public Sales(String name, String username, String password) {
        super(username, password, "Sales");
        this.products = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void searchProduct(String keyword) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(keyword)) {
                searchResults.add(product);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No products found matching the keyword: " + keyword);
        } else {
            System.out.println("Search results for keyword: " + keyword);
            for (Product product : searchResults) {
                System.out.println(product);
            }
        }
    }

    public void listAllProducts() {
        System.out.println("List of all available products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void makeOrder(Product product, int quantity) {
        // Logic to create an order for the given product and quantity
        // You can add your implementation here
        System.out.println("Order placed: " + quantity + " " + product.getName());
    }

    public void cancelOrder(Product product) {
        // Logic to cancel an order for the given product
        // You can add your implementation here
        System.out.println("Order canceled: " + product.getName());
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product.getName());
    }

    public void deleteProduct(Product product) {
        products.remove(product);
        System.out.println("Product deleted: " + product.getName());
    }

    public void updateProduct(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
                p.setExpiryDate(product.getExpiryDate());
                System.out.println("Product updated: " + p.getName());
                return;
            }
        }
        System.out.println("Product not found for update.");
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() { // TODO: implement this
        return 0;
    }

    public int getQuantity() { // TODO: implement this
        return 0;
    }
}
