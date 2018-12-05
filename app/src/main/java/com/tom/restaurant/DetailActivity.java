package com.tom.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        // get selected menuItem from intent
        this.menuItem = (MenuItem) intent.getSerializableExtra("menuItem");

        TextView name = (TextView) findViewById(R.id.itemTitleView);
        TextView price = (TextView) findViewById(R.id.priceView);
        TextView description = (TextView) findViewById(R.id.descriptionView);
        ImageView img = (ImageView) findViewById(R.id.itemImgView);
        setTitle("Restaurant : "+menuItem.getCategory());

        // set views to show details of corresponding menuItem
        name.setText(menuItem.getName());
        price.setText("€ " + String.valueOf( (int) menuItem.getPrice())+ ",-");
        description.setText(menuItem.getDescription());
        Picasso.with(this).load(menuItem.getImageUrl()).into(img);
    }
}
