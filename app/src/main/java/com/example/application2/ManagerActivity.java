package com.example.application2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerActivity extends AppCompatActivity {
    Button history_btn;
    Button stock_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        history_btn = findViewById(R.id.historyBtn);
        history_btn.setOnClickListener(this::onClick);
        stock_btn = findViewById(R.id.stockBtn);
        stock_btn.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.historyBtn:
                Intent history = new Intent(this, HistoryActivity.class);
                startActivity(history);
                break;
            case R.id.stockBtn:
                Intent stock = new Intent(this, StockActivity.class);
                startActivity(stock);
                break;
        }
    }

}
