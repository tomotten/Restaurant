package com.tom.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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
        Toast.makeText(this, categories.get(1), Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
