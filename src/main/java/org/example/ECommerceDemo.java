package org.Usenko;

import java.util.List;

public class ECommerceDemo {
    public static void main(String[] args) {
        ECommercePlatform platform = new ECommercePlatform();

        // Додавання користувачів та товарів
        User user1 = new User(1, "Андрій");
        User user2 = new User(2, "Володимир");
        platform.addUser(user1);
        platform.addUser(user2);

        Product product1 = new Product(1, "Ананас", 10.0, 100);
        Product product2 = new Product(2, "Вино", 15.0, 50);

        platform.addProduct(product1);
        platform.addProduct(product2);

        // Симуляція взаємодії користувачів

        user1.addToCart(product1, 3);
        user2.addToCart(product2, 2);


        // Створення та обробка замовлень

        Order order1 = platform.createOrder(user1);
        order1.addProduct(product1, 3);
        platform.processOrder(order1);

        Order order2 = platform.createOrder(user2);
        order2.addProduct(product2, 2);
        platform.processOrder(order2);

        System.out.println("Користувачі:");
        for (User user : platform.listUsers()) {
            System.out.println(user);
        }

        System.out.println("Товари:");
        for (Product product : platform.listAvailableProducts()) {
            System.out.println(product);
        }

        System.out.println("Замовлення:");
        for (Order order : platform.listOrders()) {
            System.out.println(order);
        }

        List<Product> sortedProductsByName = platform.sortProductsByName();
        List<Product> sortedProductsByPrice = platform.sortProductsByPrice();
        List<Product> sortedProductsByStock = platform.sortProductsByStock();
        List<Product> filteredProducts = platform.filterProductsByStock(50);

        System.out.println("Сортування за назвою:");
        for (Product product : sortedProductsByName) {
            System.out.println(product);
        }

        System.out.println("Сортування за ціною:");
        for (Product product : sortedProductsByPrice) {
            System.out.println(product);
        }

        System.out.println("Сортування за запасами:");
        for (Product product : sortedProductsByStock) {
            System.out.println(product);
        }

        System.out.println("Фільтрація за мінімальним запасом 50:");
        for (Product product : filteredProducts) {
            System.out.println(product);
        }

        // Використання рекомендацій

        User user = platform.getUserById(1); // Отримуємо користувача за ідентифікатором
        List<Product> recommendedProducts = platform.recommendProducts(user);

        System.out.println("Рекомендації для користувача " + user.getUsername() + ":");
        for (Product product : recommendedProducts) {
            System.out.println(product);
        }

    }

    // Вивести інформацію про користувачів
        platform.displayUsers();

    // Вивести інформацію про товари
        platform.displayProducts();

    // Вивести інформацію про замовлення
        platform.displayOrders();
}