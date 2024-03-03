package org.example;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {

        CustomList<Purchase> list = new CustomList<>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5_000_000; i++) {
            Purchase purchase = new Purchase();
            list.add(purchase);
            if (i == 500_000) {
                System.out.println(purchase);
            }
        }
        if (!list.isEmpty()) {
            System.out.println(list.get(500_000));
        }

        long end = System.currentTimeMillis();
        System.out.println("customList result: " + (end - start));

        ArrayList<Purchase> arrayList = new ArrayList<>();

        start = System.currentTimeMillis();

        for (int i = 0; i < 5_000_000; i++) {
            Purchase purchase = new Purchase();
            arrayList.add(purchase);
            if (i == 500_000) {
                System.out.println(purchase);
            }
        }

        System.out.println(arrayList.get(500_000));

        end = System.currentTimeMillis();
        System.out.println("arrayList result: " + (end - start));


        CustomMap<String, Purchase> someMap = new CustomMap<>();

        start = System.currentTimeMillis();
        String forSearch = "";

        for (int i = 0; i < 5_000_000; i++) {
            Purchase purchase = new Purchase();
            someMap.put(purchase.getProduct(), purchase);
            if (i == 500_000) {
                forSearch = purchase.getProduct();
                System.out.println(purchase);
            }
        }

        System.out.println(someMap.getValue(forSearch));
        end = System.currentTimeMillis();
        System.out.println("someMap result: " + (end - start));


        HashMap<String, Purchase> hashMap = new HashMap<>();
        start = System.currentTimeMillis();

        for (int i = 0; i < 5_000_000; i++) {
            Purchase purchase = new Purchase();
            hashMap.put(purchase.getProduct(), purchase);
            if (i == 500_000) {
                forSearch = purchase.getProduct();
                System.out.println(purchase);
            }
        }

        System.out.println(hashMap.get(forSearch));
        end = System.currentTimeMillis();
        System.out.println("someMap result: " + (end - start));

    }

}