package com.example.application2;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<Product> products = new ArrayList<>(0);

    public void addProducts(Product product) {
        products.add(product);
    }
}
