package org.example;

import java.time.Instant;
import java.util.Random;

public class Purchase {

    private final String product;

    private final Integer quantity;

    private final Instant time;

    public Purchase() {
        this.product = generateProductName();
        this.quantity = new Random().nextInt(0,20);
        this.time = Instant.now();
    }

    public Purchase(String product, Integer quantity, Instant time) {
        this.product = product;
        this.quantity = quantity;
        this.time = time;
    }

    private String generateProductName() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 30;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", time=" + time +
                '}';
    }
}
