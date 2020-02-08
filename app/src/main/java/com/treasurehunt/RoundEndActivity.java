package com.treasurehunt;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RoundEndActivity extends AppCompatActivity {

    ConstraintLayout mainLayout;
    RequestQueue requestQueue;
    TextView position;
    EditText feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_end);
        mainLayout= findViewById(R.id.round_end_layout);
        mainLayout.setVisibility(View.GONE);
        position=findViewById(R.id.position);
        feedback =findViewById(R.id.feedback);
//        requestQueue.add(resultRequest);
    }

    public void onClickFeedBack(View v) {

        StringRequest resultRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject res = new JSONObject(response);
                    //TODO Login result in JSON
                    int status = (int) res.get("status");
                    if (status > 0) {
                        int pos = res.getInt("position");
                        position.setText(pos);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    position.setText("ERR");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                position.setText("ERR");
            }
        }){};

        requestQueue.add(feedbackRequest);


        StringRequest feedbackRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject res = new JSONObject(response);
                    //TODO Login result in JSON
                    int status = (int) res.get("status");
                    if (status > 0) {

                    }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }