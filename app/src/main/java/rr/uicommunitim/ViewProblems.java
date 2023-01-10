package rr.uicommunitim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class ViewProblems extends AppCompatActivity {

    ListView simpleList;
    String[] categoryList;
    String[] subcategoryList;
    String[] addressList;
    double[] lat;
    double[] lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problems);
        getSupportActionBar().hide();
        getProblems();
        ImageView logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProblems.this,Menu.class));
            }
        });

    }

    private void getProblems() {
        String url = "http://10.0.2.2:8080/problems";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        categoryList = new String[response.length()];
                        subcategoryList = new String[response.length()];
                        addressList = new String[response.length()];
                        lat = new double[response.length()];
                        lon = new double[response.length()];
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject problem = response.getJSONObject(i);
                                categoryList[i] = problem.getString("category");
                                subcategoryList[i] = problem.getString("subcategory");
                                lat[i]=problem.getDouble("latitude");
                                lon[i]=problem.getDouble("longitude");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Geocoder geocoder = new Geocoder(ViewProblems.this, Locale.getDefault());
                            try {
                                addressList[i] = geocoder.getFromLocation(lat[i],lon[i],1).get(0).getAddressLine(0);
                                simpleList=(ListView) findViewById(R.id.simpleListView);
                                ProblemsListAdapter problemsListAdapter= new ProblemsListAdapter(getApplicationContext(),categoryList,subcategoryList,addressList);
                                simpleList.setAdapter(problemsListAdapter);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(ViewProblems.this, "Viewing Problems failed", Toast.LENGTH_SHORT).show();
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/n/n"+error+"/n/n@@@@@@@@@@@@@@@@@@@@@@");
                    }
                });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}