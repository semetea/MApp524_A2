package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] products;
    ListView product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        product_list = (ListView) findViewById(R.id.product_list);
        products = new String[] {"Pante", "Shoes", "Hats"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_row_layout, R.id.product_name, products);
        product_list.setAdapter(adapter);
    }
}