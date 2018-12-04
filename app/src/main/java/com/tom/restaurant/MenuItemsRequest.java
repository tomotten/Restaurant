package com.tom.restaurant;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private Callback activity;
    private String category;

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    // constructor
    public MenuItemsRequest(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    // add new request to que
    public void getMenuItems(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu?category="+category;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.getMessage());
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
        try {
            JSONArray responseArray = response.getJSONArray("items");

            // add all results to arraylist
            for(int i=0; i < responseArray.length(); i++) {
                // current menu item
                JSONObject item = responseArray.getJSONObject(i);

                // make new MenuItem object and fill with corresponding values
                MenuItem entry = new MenuItem();
                entry.setCategory(item.getString("category"));
                entry.setDescription(item.getString("description"));
                entry.setName(item.getString("name"));
                entry.setImageUrl(item.getString("image_url"));
                entry.setPrice(item.getDouble("price"));

                // add menuItem to list
                menuItems.add(entry);
            }
        }
        catch (Exception e) {
            activity.gotMenuError(e.getMessage());
        }
        // pass list of menu items to MenuActivity
        activity.gotMenuItems(menuItems);
    }
}
