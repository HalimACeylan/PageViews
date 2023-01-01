package gui.ceng.mu.edu.reapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        // seekBar
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
        // Create a List of Material to Show user
        // This material List feed by FireBase FireStore
        ArrayList<Material> materialList = new ArrayList<>();
        // Get the all SellerList of users from users collection
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.get("UserSelling") != null){
                            //user Selling List
                            List<Map<String, String>> userItems = (List<Map<String,String>>)document.get("UserSelling");
                            //User Information
                            String userName = (String) document.get("firstName");
                            String userLastName = (String) document.get("lastName");
                            String phoneNumber = (String) document.get("phoneNumber");
                            String userId = (String) document.getId();

                            final long ONE_MEGABYTE = 1024 * 1024;
                            // Wait the FireBase Thread to Continue MainThread
                            final CountDownLatch latch = new CountDownLatch(userItems.size());
                            for (Map<String,String> materialInfo:userItems) {
                                // Get The photo from FireBase FireStore
                                storageRef.child(materialInfo.get("url")).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        // Create a Material of Material List
                                        Material index = new Material(materialInfo.get("name"),bytes);
                                        index.getOwner().put("firstName",userName);
                                        index.getOwner().put("lastName",userLastName);
                                        index.getOwner().put("phoneNumber",phoneNumber);
                                        index.getOwner().put("userId",userId);
                                        materialList.add(index);
                                        latch.countDown();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        latch.countDown();
                                    }
                                });
                            }
                            try {
                                // Wait 2 second At most until complete Firebase Thread
                                latch.await(2, TimeUnit.SECONDS);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }else{
                            continue;
                        }
                    }
                    // Create Cards and them adapter to show
                    final BuyMaterialAdapter adapter = new BuyMaterialAdapter(materialList);
                    // call fragment and put in adapter
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