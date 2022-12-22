package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThingsToKnow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thingstoknow);
        Button btnWhyRec = findViewById(R.id.btnWhyRec);
        Button btnInside =findViewById(R.id.btnWaste);
        Button btnShouldKnow =findViewById(R.id.btnShouldKnow);

        btnWhyRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnShouldKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}