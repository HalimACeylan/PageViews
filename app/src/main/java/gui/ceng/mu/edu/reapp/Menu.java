package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    Button btnBuy;
    Button btnSell;
    Button btnCLosest;
    Button btnThings;
    Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnBuy = findViewById(R.id.btnBuyMenu);
        btnSell = findViewById(R.id.btnSellMenu);
        btnCLosest = findViewById(R.id.btnDistanceMenu);
        btnThings = findViewById(R.id.btnThingsMenu);
        btnAbout = findViewById(R.id.btnAboutMenu);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this,BuyPage.class);
                startActivity(i);
            }
        });
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this,TypeOfSell.class);
                startActivity(i);
            }
        });

        btnCLosest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this,ClosestLocation.class);
                startActivity(i);
            }
        });
        btnThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this,ThingsToKnow.class);
                startActivity(i);
            }
        });

    }
}