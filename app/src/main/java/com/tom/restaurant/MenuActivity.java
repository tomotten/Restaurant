package com.tom.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        setTitle("Restaurant : "+category);
        MenuItemsRequest request = new MenuItemsRequest(this, category);
        request.getMenuItems(this);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menu) {
        ListView menuList = findViewById(R.id.menuListView);
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item_row, menu);
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener(new OnMenuClickListener());
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public class OnMenuClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("menuItem", item);
            startActivity(intent);
        }
    }
}
