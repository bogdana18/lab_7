package org.example;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Integer id;
    private Integer userId;
    private Map<Product, Integer> orderDetails;
    private double totalPrice;

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
        this.orderDetails = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<Product, Integer> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<Product, Integer> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void addProduct(Product product, int quantity) {
        orderDetails.put(product, quantity);
        totalPrice += product.getPrice() * quantity;
    }
}
