package gui.ceng.mu.edu.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BuyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_buy_page);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        TextView distance = findViewById(R.id.distanceTxt);
        SeekBar seekBar = findViewById(R.id.seekBar2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                distance.setText(String.valueOf(i) + "Km");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ArrayList<Material> materialList = new ArrayList<>();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        List<Map<String, String>> userItems = (List<Map<String, String>>)document.get("UserSelling");
                        final long ONE_MEGABYTE = 1024 * 1024;
                        final CountDownLatch latch = new CountDownLatch(userItems.size());
                        for (Map<String,String> materialInfo:userItems) {
                            storageRef.child(materialInfo.get("url")).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    materialList.add(new Material(materialInfo.get("name"),bytes));
                                    latch.countDown();
                                    Log.d("itemsss", materialList.toString());

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    latch.countDown();
                                }
                            });
                        }
                        try {
                            latch.await(2, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.d("itemsss", materialList.toString());
                    final BuyMaterialAdapter adapter = new BuyMaterialAdapter(materialList);
                    FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                    MaterialList mf = MaterialList.newInstance(adapter);
                    fts.add(R.id.buyContainer,mf);
                    fts.commit();
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }

                }
        });

    }
}