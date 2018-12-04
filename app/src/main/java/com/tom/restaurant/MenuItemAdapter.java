package com.tom.restaurant;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem>{

    private ArrayList<MenuItem> menu;

    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> MenuItems){
        super(context, resource, MenuItems);
        this.menu = MenuItems;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_row, parent, false);
        }
        MenuItem currItem = menu.get(position);

        TextView name = convertView.findViewById(R.id.nameView);
        TextView price = convertView.findViewById(R.id.priceView);
        ImageView img = convertView.findViewById(R.id.itemImageView);

        // set all views
        name.setText(currItem.getName());
        price.setText("€"+String.valueOf((int) currItem.getPrice())+",-");
        Picasso.with(getContext()).load(currItem.getImageUrl()).into(img);

        return convertView;
    }

}
