package org.Usenko;

import java.util.*;

public class ECommercePlatform {
    private Map<Integer, User> users;
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders;
    private Map<Integer, Order> processedOrders;

    public ECommercePlatform() {
        users = new HashMap<>();
        products = new HashMap<>();
        orders = new HashMap<>();
        processedOrders = new HashMap<>();
    }

    // Додавання користувача
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    // Додавання товару
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    // Створення замовлення
    public Order createOrder(User user) {
        Integer newOrderId = orders.size() + 1;
        Order order = new Order(newOrderId, user.getId());
        orders.put(newOrderId, order);
        return order;
    }

    // Перелік доступних товарів (товарів з запасами більше 0)
    public List<Product> listAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getStock() > 0) {
                availableProducts.add(product);
            }
        }
        return availableProducts;
    }

    // Перелік користувачів
    public List<User> listUsers() {
        return new ArrayList<>(users.values());
    }

    // Перелік замовлень
    public List<Order> listOrders() {
        return new ArrayList<>(orders.values());
    }

    // Оновлення запасів товарів
    public void updateStock(Product product, int newStock) {
        product.setStock(newStock);
    }

    public List<Product> sortProductsByStock() {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Comparator.comparingInt(Product::getStock));
        return productList;
    }

    public List<Product> sortProductsByName() {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Comparator.comparing(Product::getName));
        return productList;
    }

    public List<Product> sortProductsByPrice() {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Comparator.comparing(Product::getPrice));
        return productList;
    }

    public List<Product> filterProductsByStock(int minStock) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getStock() >= minStock) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // Функція для рекомендації товарів користувачам

    public List<Product> recommendProducts(User user) {
        List<Product> recommendedProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (!user.getCart().containsKey(product) && product.getStock() > 0) {
                recommendedProducts.add(product);
            }
        }
        return recommendedProducts;
    }

    // Метод для обробки замовлення
    public void processOrder(Order order) {
        // Перевірка наявності користувача та замовлення в системі
        if (users.containsKey(order.getUserId()) && orders.containsValue(order)) {
            // Отримуємо користувача та замовлення
            User user = users.get(order.getUserId());

            // Перевірка наявності товарів на складі та оновлення запасів
            for (Map.Entry<Product, Integer> entry : order.getOrderDetails().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                if (products.containsKey(product.getId())) {
                    Product existingProduct = products.get(product.getId());
                    int currentStock = existingProduct.getStock();

                    if (currentStock >= quantity) {
                        // Зменшуємо кількість товару на складі
                        existingProduct.setStock(currentStock - quantity);

                        // Додаємо товар до кошика користувача
                        user.addToCart(product, quantity);
                    } else {
                        System.out.println("Товару " + product.getName() + " недостатньо на складі.");
                    }
                }
            }

            // Додаємо замовлення до списку оброблених замовлень
            order.setTotalPrice(order.getTotalPrice());  // Оновлюємо загальну ціну (це не обов'язково)

            // Записуємо замовлення в список оброблених
            processedOrders.put(order.getId(), order);

            // Видаляємо замовлення зі списку незавершених
            orders.remove(order.getId());

            // Очищаємо кошик користувача
            user.clearCart();
        } else {
            System.out.println("Помилка: Неможливо обробити замовлення, користувач або замовлення не існує в системі.");
        }
    }

    public void displayUsers() {
        for (User user : users.values()) {
            System.out.println("User ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
        }
    }

    public void displayProducts() {
        for (Product product : products.values()) {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Stock: " + product.getStock());
        }
    }

    public void displayOrders() {
        for (Order order : orders.values()) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("User ID: " + order.getUserId());
        }
    }

    public User getUserById(Integer userId) {
        return users.get(userId);
    }
}
