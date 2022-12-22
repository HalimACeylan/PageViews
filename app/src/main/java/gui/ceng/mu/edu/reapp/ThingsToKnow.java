package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                things_Info thi = things_Info.newInstance("Hello1",R.drawable.ic_baseline_info_24,"Burası Uzun Yazı1");
                fts.add(R.id.thingsContainer,thi);
                fts.commit();
            }
        });
        btnInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                things_Info thi = things_Info.newInstance("Hello2",R.drawable.ic_baseline_info_24,"Burası Uzun Yazı2");
                fts.add(R.id.thingsContainer,thi);
                fts.commit();

            }
        });
        btnShouldKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                things_Info thi = things_Info.newInstance("Hello3",R.drawable.ic_baseline_info_24,"Burası Uzun Yazı3");
                fts.add(R.id.thingsContainer,thi);
                fts.commit();
            }
        });
    }
}