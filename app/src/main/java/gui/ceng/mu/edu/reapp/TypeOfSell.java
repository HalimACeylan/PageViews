package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeOfSell extends AppCompatActivity {
    int w = 150, h = 150;
    Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
    Bitmap bmp = Bitmap.createBitmap(w, h, conf);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_sell);
        Button plasticButton = findViewById(R.id.btnplastic) ;
        plasticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebaseStuff
                ArrayList<Material> plasticMaterial = new ArrayList<>();
                plasticMaterial.add(new Material("pet1",bmp));
                plasticMaterial.add(new Material("pet2",bmp));
                plasticMaterial.add(new Material("pet3",bmp));
                plasticMaterial.add(new Material("pet4",bmp));
                plasticMaterial.add(new Material("pet5",bmp));
                plasticMaterial.add(new Material("pet6",bmp));
                Intent intent = new Intent(TypeOfSell.this,SellPage.class);
                intent.putExtra("list",(Serializable) plasticMaterial);
                startActivity(intent);
            }
        });
        Button paperButton = findViewById(R.id.btnpaper);
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebaseStuff
                ArrayList<Material> plasticMaterial = new ArrayList<>();
                plasticMaterial.add(new Material("paper1",bmp));
                plasticMaterial.add(new Material("paper2",bmp));
                plasticMaterial.add(new Material("paper3",bmp));
                plasticMaterial.add(new Material("paper4",bmp));
                plasticMaterial.add(new Material("paper5",bmp));
                Intent intent = new Intent(TypeOfSell.this,SellPage.class);
                intent.putExtra("list",(Serializable) plasticMaterial);
                startActivity(intent);
            }
        });
        Button scrapButton = findViewById(R.id.btnscrap);
        scrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}