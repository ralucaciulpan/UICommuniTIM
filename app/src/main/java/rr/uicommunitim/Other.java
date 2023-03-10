package rr.uicommunitim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Other extends AppCompatActivity {
    Button nextButton;
    String categorie;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        radioGroup=findViewById(R.id.radioGroup);
        nextButton=findViewById(R.id.nextbutton2);
        getSupportActionBar().hide();
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            categorie=extras.getString("categorie");
            Toast.makeText(this, categorie, Toast.LENGTH_SHORT).show();
        }


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Other.this,Maps.class);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                i.putExtra("categorie",categorie);
                i.putExtra("subcategorie", radioButton.getText());
                startActivity(i);
            }
        });

        ImageView logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Other.this,Menu.class));
            }
        });
    }
}