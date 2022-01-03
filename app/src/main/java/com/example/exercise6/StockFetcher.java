package com.example.exercise6;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class StockFetcher {
    private RequestQueue _queue;
    private final static String REQUEST_URL = "http://10.0.2.2:8080/";

    public class StockResponse {
        public boolean isError;
        public String price;
        public String name;

        public StockResponse(boolean isError, String price, String name) {
            this.isError = isError;
            this.price = price;
            this.name = name;
        }

    }

    public interface StockResponseListener {
        public void onResponse(StockResponse response);
    }

    public StockFetcher(Context context) {
        _queue = Volley.newRequestQueue(context);
    }

    private StockResponse createErrorResponse() {
        return new StockResponse(true, null, null);
    }

    public void dispatchRequest(final StockResponseListener listener, String ticker) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, REQUEST_URL + ticker , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            StockResponse res = new StockResponse(false,
                                    response.getString("price"),
                                    response.getString("name"));
                            listener.onResponse(res);
                        }
                        catch (JSONException e) {
                            listener.onResponse(createErrorResponse());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onResponse(createErrorResponse());
            }
        });

        _queue.add(req);
    }
}