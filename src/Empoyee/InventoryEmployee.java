package Empoyee;

import java.util.*;

import Order.*;
import User.User;

class InventoryEmployee extends User {
    private List<Product> products;
    private int notificationRange;

    public InventoryEmployee(String name, String username, String password) {
        super(username, password, "inventory");
        this.products = new ArrayList<>();
        this.notificationRange = 0;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int productId) {
        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
        }
    }

    public void updateProduct(Product product) {
        Product existingProduct = findProductById(product.getId());
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setExpiryDate(product.getExpiryDate());
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public List<Product> searchProduct(String keyword) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(keyword)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    public void setNotificationRange(int range) {
        this.notificationRange = range;
    }

    public void manageDamagedItems(DamagedItem damagedItem) {
        // Logic to manage damaged items
        System.out.println("Managing damaged item: " + damagedItem);
    }

    public void manageSalesReturn(SalesReturn salesReturn) {
        // Logic to manage sales returns
        System.out.println("Managing sales return: " + salesReturn);
    }
}
