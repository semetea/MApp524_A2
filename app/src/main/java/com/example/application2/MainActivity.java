package com.example.application2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NumberPicker numPicker;
    TextView product_type;
    TextView product_quantity;
    TextView product_total;
    ListView product_list;
    Button buyButton;
    Button mangerButton;
    Product selected_product;
    History historyManger;
    ProductManager productManager;
    ArrayList<Product> listOfProducts;
    ProductBaseAdapter productBaseAdapter;
    double total = 0.0;
    Product pante = new Product("Pante", 10, 20.44);
    Product shoes = new Product("Shoes", 100, 10.44);
    Product hats = new Product("Hats", 30, 5.9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ListView
        product_list = (ListView) findViewById(R.id.product_list);
        // TextView
        product_type = findViewById(R.id.product_type);
        product_quantity = findViewById(R.id.quantity);
        product_total = findViewById(R.id.total);
        // NumPicker
        numPicker = findViewById(R.id.num_picker);
        // Button
        buyButton = findViewById(R.id.buy_btn);
        buyButton.setOnClickListener(this::onClick);
        mangerButton = findViewById(R.id.manager_btn);
        mangerButton.setOnClickListener(this::onClick);

        historyManger = ((MyApp)getApplication()).historyManager;
        productManager = ((MyApp)getApplication()).productManager;
        productManager.addProducts(pante);
        productManager.addProducts(shoes);
        productManager.addProducts(hats);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(100);
        numPicker.setValue(1);

        productBaseAdapter = new ProductBaseAdapter(this, productManager.products);
        product_list.setAdapter(productBaseAdapter);
        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productBaseAdapter.notifyDataSetChanged();
                selected_product = productManager.products.get(i);
                product_type.setText(selected_product.name);
                if(!product_quantity.getText().toString().equals("Quantity")) {
                    total = selected_product.price * Double.parseDouble(product_quantity.getText().toString());
                    product_total.setText(String.valueOf(total));
                }
            }
        });

        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                product_quantity.setText(String.valueOf(i1));
                if(product_type.getText().toString().equals("Product Type")) {
                    Toast.makeText(MainActivity.this, "Item is not selected", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(selected_product.stock < i1) {
                        Toast.makeText(MainActivity.this, "Not enough products in stock", Toast.LENGTH_SHORT).show();
                    }
                    total = selected_product.price * Double.parseDouble(product_quantity.getText().toString());
                    product_total.setText(String.valueOf(total));
                }

            }
        });

    }
    public boolean validate() {
        if(!product_type.getText().toString().equals("Product Type") &&
                !product_total.getText().toString().equals("Total") &&
                !product_quantity.getText().toString().equals("Quantity")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEnough() {
        if(Integer.parseInt(product_quantity.getText().toString()) <= selected_product.stock){
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        int id = view.getId();

        switch(id) {
            case R.id.buy_btn:

                if(validate()) {
                    if(isEnough()) {
                        int qty = Integer.parseInt(product_quantity.getText().toString());
                        total = selected_product.price * Double.parseDouble(product_quantity.getText().toString());
                        historyManger.addItem(selected_product.name, qty, total);
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Thank you for purchase");
                        builder.setMessage("Your purchase is " + qty + " " + selected_product.name + " for " + product_total.getText());
                        builder.show();
                        if(selected_product.name == "Pante") {
                            productManager.products.get(0).stock -= qty;
                        }
                        else if(selected_product.name == "Shoes") {
                            productManager.products.get(1).stock -= qty;
                        }
                        else if(selected_product.name == "Hats") {
                            productManager.products.get(2).stock -= qty;
                        }
                        productBaseAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.manager_btn:
                Intent myIntent = new Intent(this, ManagerActivity.class);
                startActivity(myIntent);
                break;
        }

    }

}