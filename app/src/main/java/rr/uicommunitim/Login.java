package rr.uicommunitim;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        username=findViewById(R.id.utilizator);
        password=findViewById(R.id.parola);
        loginButton=findViewById(R.id.button);

        loginButton.setOnClickListener(View -> {
            try {
                login();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void login() throws JSONException {
        String url = "http://10.0.2.2:8080/users/login";
        JSONObject js = new JSONObject();
        try {
            js.put("username",username.getText());
            js.put("password",password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonEmptyRequest jsonEmptyRequest = new JsonEmptyRequest
                (Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response: " + response.toString());
                        startActivity(new Intent(Login.this,Menu.class));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(Login.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsonEmptyRequest);
    }
}