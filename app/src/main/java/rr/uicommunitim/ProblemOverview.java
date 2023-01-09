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

public class ProblemOverview extends AppCompatActivity {
    Button photoButton;
    ImageView photoImageView;
    TextView adresaView,problemaView;
    String categorie;
    String subcategorie;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_overview);
        photoButton = findViewById(R.id.photoButton);
        photoImageView = findViewById(R.id.photoImageView);
        problemaView=findViewById(R.id.addTextProblema);
        adresaView=findViewById(R.id.addTextAdresa);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            categorie = extras.getString("categorie");
            subcategorie = extras.getString("subcategorie");
            address = extras.getString("adresa");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n"+ categorie+"\n"+subcategorie+"\n"+address + "\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            problemaView.setText(subcategorie);
            adresaView.setText(address);
        }
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askForCameraPermission();
            }
        });
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