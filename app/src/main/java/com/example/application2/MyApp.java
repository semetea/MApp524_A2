package com.example.application2;

import android.app.Application;

public class MyApp extends Application {
    History historyManager = new History();
    ProductManager productManager = new ProductManager();
}
