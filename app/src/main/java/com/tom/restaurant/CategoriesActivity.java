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

        // dispay message to show app started
        Toast.makeText(this, "Started", Toast.LENGTH_LONG).show();

        // make a request to get categories
        CategoriesRequest cr = new CategoriesRequest(this);
        cr.getCategories(this);


    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ListView categoryList = findViewById(R.id.categoryView);

        // use a simple_list adapter to display result of the request and set listener
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categories);
        categoryList.setAdapter(adapter);
        categoryList.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        // if request returned error show the error message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // get clicked category and pass with intent to MenuActivity to show correct menuItems
            String category = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
