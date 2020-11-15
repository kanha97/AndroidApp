package com.example.android.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String url="https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=a76d1362ad094e63b2c27cb8f0312a1d";
private String title,imageUrl,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list=findViewById(R.id.list);

        ArrayList<news> newses=new ArrayList<>();



        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray=response.optJSONArray("articles");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject articles_JsonObject=jsonArray.getJSONObject(i);
                         title=articles_JsonObject.getString("title");
                         imageUrl=articles_JsonObject.getString("urlToImage");
                         description=articles_JsonObject.getString("description");
                        Log.d("Main","i"+i);
                        newses.add(new news(title,imageUrl,description));
                    }


                } catch (JSONException e) {

                    Log.d("MainActivity"," Errorsssss "+e.getMessage());
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MainActivity"," Error "+error);

                Toast.makeText(MainActivity.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");


                return params;
            }

        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);





        newsAdapter adapter=new newsAdapter(this);
        adapter.setNewses(newses);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));



    }


}
