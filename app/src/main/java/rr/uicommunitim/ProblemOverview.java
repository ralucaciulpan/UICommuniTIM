package rr.uicommunitim;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProblemOverview extends AppCompatActivity {
    Button photoButton;
    ImageView photoImageView;
    TextView adresaView,problemaView;
    String categorie;
    String subcategorie;
    String address;
    Button nextButton;
    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_overview);
        photoButton = findViewById(R.id.photoButton);
        photoImageView = findViewById(R.id.photoImageView);
        problemaView=findViewById(R.id.addTextProblema);
        adresaView=findViewById(R.id.addTextAdresa);
        nextButton = findViewById(R.id.nextButton);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            categorie = extras.getString("categorie");
            subcategorie = extras.getString("subcategorie");
            address = extras.getString("adresa");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n"+ categorie+"\n"+subcategorie+"\n"+address + "\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            problemaView.setText(subcategorie);
            adresaView.setText(address);
            latitude=extras.getDouble("latitude");
            longitude=extras.getDouble("longitude");
        }
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askForCameraPermission();
            }
        });
        nextButton.setOnClickListener(View -> {
            addProblem();
        });

    }

    private void addProblem() {
        String url = "http://10.0.2.2:8080/problems/add";
        JSONObject js = new JSONObject();
        try {
            js.put("category",categorie);
            js.put("subcategory",subcategorie);
            js.put("longitude",longitude);
            js.put("latitude",latitude);
            System.out.println(js);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonEmptyRequest jsonEmptyRequest = new JsonEmptyRequest
                (Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response: " + response.toString());
                        //startActivity(new Intent(Login.this,Menu.class));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(ProblemOverview.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsonEmptyRequest);
    }

    private void askForCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},101);
        }else{
            openCamera();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "You need to grant camera permission to take a photo.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private ActivityResultLauncher<Intent> launchCameraActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Bitmap image = (Bitmap) data.getExtras().get("data");
                        photoImageView.setImageBitmap(image);
                    }
                }
            }
    );
    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        launchCameraActivity.launch(camera);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}