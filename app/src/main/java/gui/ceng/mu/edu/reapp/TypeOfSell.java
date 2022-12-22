package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeOfSell extends AppCompatActivity {

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
                plasticMaterial.add(new Material("pet1",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("pet2",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("pet3",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("pet4",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("pet5",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("pet6",R.drawable.ic_baseline_info_24));
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
                plasticMaterial.add(new Material("paper1",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("paper2",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("paper3",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("paper4",R.drawable.ic_baseline_info_24));
                plasticMaterial.add(new Material("paper5",R.drawable.ic_baseline_info_24));
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