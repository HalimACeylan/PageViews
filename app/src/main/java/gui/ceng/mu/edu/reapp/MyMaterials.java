package gui.ceng.mu.edu.reapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyMaterials extends AppCompatActivity {
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DocumentReference userRef = db.collection("users").document(currentUser.getUid());
    StorageReference storageRef = storage.getReference();
    Button btnOkay;
    CardFragment cf;
    List<Map<String, String>> userItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_materials);
        ArrayList<Material> currentUserSelling= new ArrayList<>();
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                userItems = (List<Map<String, String>>)task.getResult().get("UserSelling");
                if(userItems != null){
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
                                currentUserSelling.add(index);
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
                        if (!latch.await(1, TimeUnit.SECONDS)){
                            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                            cf = CardFragment.newInstance(currentUserSelling);
                            fts.add(R.id.myContainer,cf);
                            fts.commit();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        btnOkay = findViewById(R.id.btnMyMaterialOkay);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //there is no item
              if(userItems != null){
                  List<HashMap<String,String>> updatedList = new ArrayList<>();
                  //if fragment list is empty
                  if (!cf.getItemList().isEmpty()) {
                      for (Material m: cf.getItemList()) {
                          for (Map<String,String> materialInfo:userItems) {
                              if(materialInfo.get("name").equals(m.getName())){
                                  HashMap<String,String> item = new HashMap<>();
                                  item.put("name",materialInfo.get("name"));
                                  item.put("url",materialInfo.get("url"));
                                  updatedList.add(item);
                              }
                          }
                          userRef.update("UserSelling",updatedList);
                      }
                      // if user remove all selling
                  }else if (userItems !=null && cf.getItemList().isEmpty()) {
                      userRef.update("UserSelling",updatedList);
                  }
              }
                MyMaterials.this.finish();
            }
        });
    }
}