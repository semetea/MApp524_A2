package com.example.application2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

    TextView product_type;
    NumberPicker numPicker;
    TextView product_quantity;
    TextView product_total;
    ListView product_list;
    Product selected_product;
    Button buyButton;
    ArrayList<Product> listOfProducts;
    ProductBaseAdapter productBaseAdapter;
    double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        product_list = (ListView) findViewById(R.id.product_list);
        product_type = findViewById(R.id.product_type);
        numPicker = findViewById(R.id.num_picker);
        product_quantity = findViewById(R.id.quantity);
        product_total = findViewById(R.id.total);
        buyButton = findViewById(R.id.buy_btn);
        buyButton.setOnClickListener(this::onClick);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(100);

        listOfProducts = new ArrayList<>(3);
        listOfProducts.add(new Product("Pante", 20.44, 10));
        listOfProducts.add(new Product("Shoes", 10.44, 100));
        listOfProducts.add(new Product("Hats", 5.9, 30));

        productBaseAdapter = new ProductBaseAdapter(this, listOfProducts);
        product_list.setAdapter(productBaseAdapter);
        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected_product = listOfProducts.get(i);
                product_type.setText(selected_product.name);
            }
        });

        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                product_quantity.setText(String.valueOf(String.valueOf(i1)));
                if(product_type.getText().toString().equals("Product Type")) {
                    CharSequence text = "Item is not selected";
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                } else {
                    if(selected_product.stock < i1) {
                        CharSequence text = "Not enough products in stock";
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
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
        if(Integer.parseInt(product_quantity.getText().toString()) == selected_product.stock){
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Thank you for purchase");
                        builder.setMessage("Your purchase is " + qty + " " + selected_product.name + " for " + product_total.getText());
                        builder.show();
                        if(selected_product.name == "Pante") {
                            listOfProducts.get(0).stock -= qty;
                        }
                        else if(selected_product.name == "Shoes") {
                            listOfProducts.get(0).stock -= qty;
                        }
                        else if(selected_product.name == "Hats") {
                            listOfProducts.get(0).stock -= qty;
                        }
                        productBaseAdapter.notifyDataSetChanged();
                    }
                }
        }

    }

}