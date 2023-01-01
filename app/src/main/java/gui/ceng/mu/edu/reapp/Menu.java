package gui.ceng.mu.edu.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    Button btnBuy;
    Button btnSell;
    Button btnCLosest;
    Button btnThings;
    Button btnMyMaterial;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    DocumentReference userRef = db.collection("users").document(currentUser.getUid());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnBuy = findViewById(R.id.btnBuyMenu);
        btnSell = findViewById(R.id.btnSellMenu);
        btnCLosest = findViewById(R.id.btnDistanceMenu);
        btnThings = findViewById(R.id.btnThingsMenu);
        btnMyMaterial = findViewById(R.id.btnMyMaterial);
        btnBuy.setEnabled(false);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String userType = (String) task.getResult().get("userType");
                if (userType.equals("buyer")){
                    btnBuy.setEnabled(true);
                }else{
                    btnBuy.setEnabled(false);
                }
            }
        });


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
        btnMyMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this,MyMaterials.class);
                startActivity(i);
            }
        });
    }
}