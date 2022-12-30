package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SellPage extends AppCompatActivity {
    int REQUEST_IMAGE_CAPTURE = 0;

    Material onChosenMaterial = new Material("name",null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_page);
        Button btnNext  = findViewById(R.id.btnSellNext);
        //adapter
        SellMaterialAdapter adapter;
        //Get From TypeOfSell
        ArrayList<Material> materialList = (ArrayList<Material>) getIntent().getSerializableExtra("list");
        adapter = new SellMaterialAdapter(materialList);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        MaterialList mf = MaterialList.newInstance(adapter);
        fts.add(R.id.container,mf);
        fts.commit();
        btnNext.setOnClickListener(new View.OnClickListener() {
            // Calculate Name
            @Override
            public void onClick(View view) {
                String name = "";
                for (Material m: materialList) {
                    if (m.getCount() != 0) {
                        name = name + m.getName() +": " + m.getCount() + "\n";
                    }
                }
                if(name.equals("")){
                    Toast.makeText(SellPage.this,"Please Choose a Material",Toast.LENGTH_LONG).show();
                }else {
                    onChosenMaterial.setName(name);
                    Intent intent = new Intent(SellPage.this,ProductPage.class);
                    intent.putExtra("onChosen",onChosenMaterial);
                    startActivity(intent);
                    SellPage.this.finish();
                }
            }
        });
    }


}