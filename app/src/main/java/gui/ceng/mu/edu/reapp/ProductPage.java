package gui.ceng.mu.edu.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProductPage extends AppCompatActivity implements Serializable {
    FirebaseAuth mAuth;
    private boolean resultReceived = false;
    int REQUEST_IMAGE_CAPTURE = 0;
    private Bitmap imageBitmap;
    Material material;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference userRef = db.collection("users").document(currentUser.getUid());
        StorageReference photosRef = storage.getReference().child("users").child(currentUser.getUid()).child("photos");
        material = (Material) getIntent().getSerializableExtra("onChosen");
        TextView txtHeader = findViewById(R.id.txtPHeader);
        TextView txtDesc = findViewById(R.id.txtPDesc);
        TextView txtName = findViewById(R.id.txtPUserName);
        TextView txtNumber = findViewById(R.id.txtPUserNumber);
        TextView txtAddress = findViewById(R.id.txtPUserAdress);
        imageView = imageView = findViewById(R.id.imgPView);
        Button btnBuyOrPost = findViewById(R.id.BuyOrPost);
        txtDesc.setText(material.getName());
        if (material.getImage() == null) {
            new TakePhotoTask().execute();
            btnBuyOrPost.setText("Post");
        } else {
            imageView.setImageBitmap(material.getImage());
            btnBuyOrPost.setText("Buy");
        }
        btnBuyOrPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnBuyOrPost.getText().toString() == "Post") {
                    String photoName = "photo_" + System.currentTimeMillis() + ".jpg";
                    StorageReference photoRef = photosRef.child(photoName);
                    UploadTask uploadTask = photoRef.putBytes(material.getImageInByte());
                    uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                // Get the download URL for the uploaded photo
                                String downloadUrl = task.getResult().getStorage().getPath();
                                Map<String, String> item = new HashMap<>();
                                Map<String, Object> UserSelling= new HashMap<>();
                                item.put("name",material.getName());
                                item.put("url",downloadUrl);
                                userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot snapshot) {
                                        // Check if the field exists in the document
                                        if (!snapshot.contains("UserSelling")) {
                                            List<Map<String, String>> userItems = new ArrayList<>();
                                            userItems.add(item);
                                            UserSelling.put("UserSelling",userItems);
                                            userRef.update(UserSelling).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProductPage.this,"Succesfully Uploaded",Toast.LENGTH_SHORT).show();
                                                    ProductPage.this.finish();
                                                }
                                            });
                                        }else {
                                            List<Map<String, String>> userItems = (List<Map<String, String>>)snapshot.get("UserSelling");
                                            userItems.add(item);
                                            UserSelling.put("UserSelling",userItems);
                                            userRef.update(UserSelling).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(ProductPage.this,"Succesfully Uploaded",Toast.LENGTH_SHORT).show();
                                                    ProductPage.this.finish();
                                                }
                                            });

                                        }

                                    }
                                });
                            }
                        }
                    });
                }


            }
        });
    }

    private class TakePhotoTask extends AsyncTask<Void, Void, byte[]> {

        @Override
        protected byte[] doInBackground(Void... voids) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            while (!resultReceived) {
                // wait for the result to be received
            }
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
            byte[] imageInByte = byteStream.toByteArray();
            Log.d("MyByte", imageInByte.toString());
            return imageInByte;
        }

        @Override
        protected void onPostExecute(byte[] imageInByte) {
            material.setImage(imageInByte);
            imageView.setImageBitmap(material.getImage());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            resultReceived = true;
        }
    }
}