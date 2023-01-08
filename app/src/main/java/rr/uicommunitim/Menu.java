package rr.uicommunitim;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
=======
import android.os.Bundle;
>>>>>>> 7cc79f4dbb5437adbe25bb97f19a34c1122a1b76

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        ImageView AddImageView = findViewById(R.id.addIcon);
        TextView AddTextView = findViewById(R.id.addText);

        ImageView LogoutImageView = findViewById(R.id.logoutIcon);
        TextView LogoutTextView = findViewById(R.id.logoutText);

        AddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this,Category.class));
            }
        });

        AddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this,Category.class));
            }
        });

        LogoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this,MainActivity.class));
            }
        });

        LogoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this,MainActivity.class));
            }
        });
    }
}