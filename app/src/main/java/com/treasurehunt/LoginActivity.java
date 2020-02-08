package com.treasurehunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    Button button;
    EditText teamID,teamName;
    String login_url = "";
    Team team;
    LinearLayout loginLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.login_btn);
        teamID = findViewById(R.id.team_id);
        teamName = findViewById(R.id.team_name);
        loginLayout = findViewById(R.id.login_layout);

        loginLayout.setVisibility(View.VISIBLE);
    }

    public void OnLogin(View view){
        if(teamName.getText().toString().equals("") || teamID.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"incorrect data!",Toast.LENGTH_LONG).show();
        }
        else{
//            team.setTeamname(teamName.getText().toString());
  //          team.setUid(teamID.getText().toString());

            loginLayout.setVisibility(View.GONE);
        //requestQueue.add(loginRequest);
        startActivity(new Intent(LoginActivity.this,RoundProgressActivity.class));
        }
    }

    StringRequest loginRequest= new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject res = new JSONObject(response);
                //TODO Login result in JSON
                int status = (int) res.get("status");
                if(status>0){
                    Table table = new Table(getApplicationContext());
                    table.addTeam(team);
                    loginLayout.setVisibility(View.VISIBLE);
                    startActivity(new Intent(LoginActivity.this,RoundProgressActivity.class));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            //TODO change this
            return super.getParams();
        }
    };

  //  JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,"",new )
}
