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
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        things_Info thi = things_Info.newInstance("Why Recycle is Important?",R.mipmap.whyrecycle_foreground,"Burası Uzun Yazı1");
        fts.add(R.id.thingsContainer,thi);
        fts.commit();

        btnWhyRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                things_Info thi = things_Info.newInstance("Why Recycle is Important?",R.mipmap.whyrecycle_foreground,"Burası Uzun Yazı1");
                fts.add(R.id.thingsContainer,thi);
                fts.commit();
            }
        });
        btnInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                things_Info thi = things_Info.newInstance("Which Waste Goes to Which Bin?",R.mipmap.insidewaste_foreground,"Burası Uzun Yazı2");
                fts.add(R.id.thingsContainer,thi);
                fts.commit();

            }
        });
        btnShouldKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                things_Info thi = things_Info.newInstance("False Information About Recycling",R.mipmap.should_foreground,"Burası Uzun Yazı3");
                fts.add(R.id.thingsContainer,thi);
                fts.commit();
            }
        });
    }
}