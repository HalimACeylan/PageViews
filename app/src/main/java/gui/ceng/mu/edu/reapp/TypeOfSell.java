package gui.ceng.mu.edu.reapp;

import androidx.annotation.NonNull;
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
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TypeOfSell extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_sell);
        Button plasticButton = findViewById(R.id.btnplastic) ;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        plasticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebaseStuff
                ArrayList<Material> plasticMaterial = new ArrayList<>();
                StorageReference photosRef = storageRef.child("Plastic");
                photosRef.listAll().addOnSuccessListener(listResult -> {
                    for (StorageReference photoRef : listResult.getItems()) {
                        final long ONE_MEGABYTE = 1024 * 1024;
                        photoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(
                                bytes -> {
                                    plasticMaterial.add(new Material(photoRef.getPath(),bytes));
                                    Log.d("adapterwork0",plasticMaterial.toString());
                                }

                                ).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle any errors
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
                new CountDownTimer(2000, 1000) {
                    public void onFinish() {
                        Intent i = new Intent(TypeOfSell.this,SellPage.class);
                        Log.d("adapterwork3",plasticMaterial.toString());
                        i.putExtra("list",plasticMaterial);
                        startActivity(i);
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();
            }
        });

        Button paperButton = findViewById(R.id.btnpaper);
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebaseStuff
                ArrayList <Material> paperMaterial = new ArrayList<>();
                StorageReference photosRef = storageRef.child("Paper");
                photosRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference photoRef : listResult.getItems()) {
                            final long ONE_MEGABYTE = 1024 * 1024;
                            photoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    paperMaterial.add(new Material("pet1",bytes));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    public void onFinish() {
                        Intent i = new Intent(TypeOfSell.this, SellPage.class);
                        Log.d("adapterwork3", paperMaterial.toString());
                        i.putExtra("list", paperMaterial);
                        startActivity(i);
                    }
                }.start();
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