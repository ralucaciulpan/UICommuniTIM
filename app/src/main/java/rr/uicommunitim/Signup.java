package rr.uicommunitim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Signup extends AppCompatActivity {
    EditText username,password,email;
    Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=findViewById(R.id.utilizatorSignup);
        password=findViewById(R.id.parolaSignup);
        email=findViewById(R.id.emailSignup);
        signupButton=findViewById(R.id.button);
        signupButton.setOnClickListener(View -> {
            signup();
        });
    }

    private void signup() {
        String url = "http://10.0.2.2:8080/users/add";
        JSONObject js = new JSONObject();
        try {
            js.put("username",username.getText());
            js.put("password",password.getText());
            js.put("email",email.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonEmptyRequest jsonEmptyRequest = new JsonEmptyRequest
                (Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response: " + response.toString());
                        startActivity(new Intent(Signup.this,Login.class));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(Signup.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsonEmptyRequest);
    }
}