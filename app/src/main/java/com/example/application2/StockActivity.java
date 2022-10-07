package com.example.application2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StockActivity extends AppCompatActivity {

    Button update;
    Button cancel;
    EditText quantity;
    TextView product_type;
    ListView products;
    int selected = -1;
    int qty = 0;
    ProductManager productManager;
    ProductBaseAdapter productBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        update = findViewById(R.id.update);
        update.setOnClickListener(this::onClick);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this::onClick);
        quantity = findViewById(R.id.quantity_input);
        product_type = findViewById(R.id.prodType);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        products = findViewById(R.id.products);
        productManager = ((MyApp)getApplication()).productManager;

        productBaseAdapter = new ProductBaseAdapter(this, productManager.products);
        products.setAdapter(productBaseAdapter);

        products.setOnItemClickListener((adapterView, view, i, l) -> {
            selected = i;
            product_type.setText(productManager.products.get(i).name);
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void isNumber(){
        try {
            int q = Integer.parseInt(quantity.getText().toString());
            if(q >= 0){
                qty = q;
            }
        }catch (Exception error){
            CharSequence text = "All fields required";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.update:
                isNumber();
                if(selected >= 0) {
                    productManager.products.get(selected).stock += qty;
                    productBaseAdapter.notifyDataSetChanged();
                    qty = 0;
                } else {
                    Toast.makeText(this, "Please select a product", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel:
                this.finish();
                break;
        }
    }
}