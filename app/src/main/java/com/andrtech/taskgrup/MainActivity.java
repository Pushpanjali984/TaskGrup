package com.andrtech.taskgrup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView likeImg,dislikeImg,imageId;
    Button btn;
    RequestQueue requestQueue;
    DatabaseHelper databaseHelper;
    String URL="https://dog.ceo/api/breeds/image/random";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        likeImg=findViewById(R.id.likeId);
        dislikeImg=findViewById(R.id.dislikeId);
        btn=findViewById(R.id.nextAct);
        databaseHelper = new DatabaseHelper(this);
        requestQueue = Volley.newRequestQueue(this);
        imageId=findViewById(R.id.imageId);
        dataApi();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(in);
            }
        });
        likeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String imageString=response.getString("message");
                            Picasso.with(MainActivity.this).load(imageString).into(imageId);

                            AddData(imageString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest1);






            }
        });





        dislikeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String imageString=response.getString("message");
                            Picasso.with(MainActivity.this).load(imageString).into(imageId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest1);

            }
        });

    }

    public void dataApi(){


        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String imageString=response.getString("message");
                    Picasso.with(MainActivity.this).load(imageString).into(imageId);


                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                                            /* finish();
                                             startActivity(getIntent());*/




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest1);


    }



    public void AddData(String newEntry){


        boolean insertData=databaseHelper.addData(newEntry);


        if(insertData==true){
            Toast.makeText(getApplicationContext(),"Successfully saved image",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"image not saved",Toast.LENGTH_LONG).show();

        }
    }



}


