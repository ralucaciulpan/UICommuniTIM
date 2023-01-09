package rr.uicommunitim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Lights extends AppCompatActivity {
    Button nextButton;
    String categorie;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        nextButton=findViewById(R.id.nextbutton2);
        radioGroup=findViewById(R.id.radioGroup);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
             categorie = extras.getString("categorie");
            Toast.makeText(this, categorie, Toast.LENGTH_SHORT).show();
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Lights.this,Maps.class);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                i.putExtra("categorie",categorie);
                i.putExtra("subcategorie", radioButton.getText());
                startActivity(i);
            }
        });
    }
}