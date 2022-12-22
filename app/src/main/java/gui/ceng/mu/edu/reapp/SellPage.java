package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class SellPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_page);
        Button btnNext  = findViewById(R.id.btnSellNext);

        SellMaterialAdapter adapter;
        ArrayList<Material> materialList = (ArrayList<Material>) getIntent().getSerializableExtra("list");
        adapter = new SellMaterialAdapter(materialList);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        MaterialList mf = MaterialList.newInstance(adapter);
        fts.add(R.id.container,mf);
        fts.commit();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellPage.this,ProductPage.class);
                intent.putExtra("onChoosen",new Material("pet1",R.drawable.ic_baseline_info_24));
                startActivity(intent);
            }
        });
    }


}