package com.example.application2;

import java.util.ArrayList;

public class History {
    ArrayList<Operation> operationHistory = new ArrayList<>(0);
    class Operation{
        String name;
        int quantity;
        double price;
        java.util.Date date;
        Operation(String title, int qty, double p){
            name = title;
            quantity = qty;
            price = p;
            date = new java.util.Date();
        }
    }
    public void addItem(String title, int qty, double total){
        Operation newOperation = new Operation(title, qty, total);
        operationHistory.add(newOperation);
    }
}
