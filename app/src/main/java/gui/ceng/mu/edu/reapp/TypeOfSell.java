package gui.ceng.mu.edu.reapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class TypeOfSell extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_sell);
        Button plasticButton = findViewById(R.id.btnplastic);
        plasticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImagesFromFirebaseAndNavigate("Plastic");

            }
        });
        Button paperButton = findViewById(R.id.btnpaper);
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImagesFromFirebaseAndNavigate("Paper");
            }
        });
        Button scrapButton = findViewById(R.id.btnscrap);
        scrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImagesFromFirebaseAndNavigate("Scrap");
            }
        });
        Button glassButton = findViewById(R.id.btnglass);
        glassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImagesFromFirebaseAndNavigate("Glass");


            }
        });

        Button batteryButton = findViewById(R.id.btnbattery);
        batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImagesFromFirebaseAndNavigate("Battery");
            }
        });
    }

    public void getImagesFromFirebaseAndNavigate(String storeRef) {
        ArrayList<Material> mList = new ArrayList<>();
        StorageReference photosRef = storageRef.child(storeRef);
        photosRef.listAll().addOnSuccessListener(listResult -> {
            for (StorageReference photoRef : listResult.getItems()) {
                final long ONE_MEGABYTE = 1024 * 1024;
                photoRef.getBytes(ONE_MEGABYTE).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                    @Override
                    public void onComplete(@NonNull Task<byte[]> task) {
                        mList.add(new Material(photoRef.getName().substring(0, photoRef.getName().indexOf(".")), task.getResult()));
                    }
                });
            }

        });

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent i = new Intent(TypeOfSell.this, SellPage.class);
                i.putExtra("list", mList);
                startActivity(i);
            }
        }.start();
    }
}
