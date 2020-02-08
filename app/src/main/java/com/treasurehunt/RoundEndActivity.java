package com.treasurehunt;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

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

    public void onClickFeedBack(View v){

        requestQueue.add(feedbackRequest);
    }

    StringRequest resultRequest;
    StringRequest feedbackRequest;
}
