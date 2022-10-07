package com.example.application2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> listOfProducts = new ArrayList<>(0);

    ProductBaseAdapter(Context c, ArrayList<Product> list) {
        context = c;
        listOfProducts = list;
    }


    @Override
    public int getCount() {
        return listOfProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_row_layout, null);
        TextView productName = view.findViewById(R.id.product_name);
        TextView productPrice = view.findViewById(R.id.product_price);
        TextView productStock = view.findViewById(R.id.product_stock);

        productName.setText(listOfProducts.get(i).name);
        productPrice.setText(String.valueOf(listOfProducts.get(i).price));
        productStock.setText(String.valueOf(listOfProducts.get(i).stock));

        return view;
    }
}
