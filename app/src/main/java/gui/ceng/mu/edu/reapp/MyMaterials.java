package gui.ceng.mu.edu.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyMaterials extends AppCompatActivity {
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DocumentReference userRef = db.collection("users").document(currentUser.getUid());
    StorageReference photosRef = storage.getReference().child("users").child(currentUser.getUid()).child("photos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_materials);
        ArrayList<Material> currentUserSelling= new ArrayList<>();
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                List<Map<String, String>> userItems = (List<Map<String, String>>)task.getResult().get("UserSelling");
                for ( Map<String, String> item: userItems) {

                }
            }
        });
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        CardFragment cf = CardFragment.newInstance(currentUserSelling);
        fts.add(R.id.myContainer,cf);
        fts.commit();
    }
}