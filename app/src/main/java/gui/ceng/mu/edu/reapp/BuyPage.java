package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class BuyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);
        BuyMaterialAdapter adapter;
        ArrayList<Material> materialList = (ArrayList<Material> ) getIntent().getSerializableExtra("list");
        adapter = new BuyMaterialAdapter(materialList,this);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        MaterialList mf = MaterialList.newInstance(adapter);
        fts.add(R.id.buyContainer,mf);
        fts.commit();
    }
}