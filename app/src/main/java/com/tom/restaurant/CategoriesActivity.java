package com.tom.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest cr = new CategoriesRequest(this);
        cr.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_LONG).show();

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ListView categoryList = findViewById(R.id.categoryView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        categoryList.setAdapter(adapter);
        categoryList.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
