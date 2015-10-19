package com.example.wishbook;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.wishbook.adapter.WishListAdapter;
import com.example.wishbook.data.DbHelper;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper = new DbHelper(this);
    private WishListAdapter adapter;


    private void loadData(){

        Cursor cursor = dbHelper.getAllWish();

        adapter = new WishListAdapter(this, cursor);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }
}
