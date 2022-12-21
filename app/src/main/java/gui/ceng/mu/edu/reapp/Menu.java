package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                ArrayList<Material> plasticMaterial = new ArrayList<>();
                plasticMaterial.add(new Material("pet1",R.drawable.ic_baseline_person_24));
                plasticMaterial.add(new Material("pet2",R.drawable.ic_baseline_person_24));
                plasticMaterial.add(new Material("pet3",R.drawable.ic_baseline_person_24));
                plasticMaterial.add(new Material("pet4",R.drawable.ic_baseline_person_24));
                plasticMaterial.add(new Material("pet5",R.drawable.ic_baseline_person_24));
                Intent i = new Intent(Menu.this,BuyPage.class);
                i.putExtra("list",(Serializable) plasticMaterial);
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
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this,About.class);
            }
        });
    }
}