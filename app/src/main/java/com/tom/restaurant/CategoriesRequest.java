package com.tom.restaurant;

import android.app.Activity;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener
{

    private Context context;
    private Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // add request to que and save activity that send it
    public void getCategories(Callback activity) {
        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    // send error back to activity where request came from
    public void onErrorResponse(VolleyError error) {
        System.out.println("-----------------------------------------------------You got an ErrorResponse!!-----------------------------------------");
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    // this function will executed when response to request is received, it will list the items and send them back to Callback activity with gotCategories
    public void onResponse(JSONObject response) {
        ArrayList<String> categories = new ArrayList<String>();
        try {
            JSONArray responseArray = response.getJSONArray("categories");

            // add all results to arraylist
            for(int i=0; i < responseArray.length(); i++) {
                categories.add(responseArray.optString(i));
            }
        System.out.println(categories);
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        activity.gotCategories(categories);
    }

}
