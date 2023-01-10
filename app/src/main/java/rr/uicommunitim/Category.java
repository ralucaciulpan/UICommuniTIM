package rr.uicommunitim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Category extends AppCompatActivity {

    Button drumuriButton;
    Button iluminareButton;
    Button alteleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().hide();
        drumuriButton = findViewById(R.id.drumuributton);
        iluminareButton = findViewById(R.id.iluminarebutton);
        alteleButton = findViewById(R.id.altelebutton);

        drumuriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,Problem.class);
                i.putExtra("categorie",drumuriButton.getText());
                startActivity(i);
            }
        });

        iluminareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,Lights.class);
                i.putExtra("categorie",iluminareButton.getText());
                startActivity(i);
            }
        });

        alteleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Category.this,Other.class);
                i.putExtra("categorie",alteleButton.getText());
                startActivity(i);
            }
        });

        ImageView logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category.this,Menu.class));
            }
        });
    }
}