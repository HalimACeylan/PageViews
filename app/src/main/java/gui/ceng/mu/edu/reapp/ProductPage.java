package gui.ceng.mu.edu.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        //FireBase Stuff
        FirebaseStorage storage = FirebaseStorage.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get the Current User
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        // Get the UserId
        DocumentReference userRef = db.collection("users").document(currentUser.getUid());
        // FireStore Photo path to put photo
        StorageReference photosRef = storage.getReference().child("users").child(currentUser.getUid()).child("photos");
        // Get the Material From BuyPage or SellPage
        material = (Material) getIntent().getSerializableExtra("onChosen");
        // XML
        TextView txtHeader = findViewById(R.id.txtPHeader);
        TextView txtDesc = findViewById(R.id.txtPDesc);
        TextView txtName = findViewById(R.id.txtPUserName);
        TextView txtNumber = findViewById(R.id.txtPUserNumber);
        TextView txtAddress = findViewById(R.id.txtPUserAdress);
        imageView = imageView = findViewById(R.id.imgPView);
        Button btnBuyOrPost = findViewById(R.id.BuyOrPost);
        txtDesc.setText(material.getName());
        // if material doesn't have photo this mean photo came from SellPage and Need to add photo to this Material
        if (material.getImageInBitmap() == null) {
            // Take Photo
            new TakePhotoTask().execute();
            txtHeader.setText(material.getName());
            btnBuyOrPost.setText("Post");
            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot curUser = task.getResult();
                    // Get Use information From FireBase Auth User Already Login
                    String userName = curUser.get("firstName") + "" +curUser.get("lastName");
                    txtName.setText(userName);
                    txtNumber.setText((String) curUser.get("phoneNumber"));
                    txtAddress.setText((String) curUser.get("firstName"));
                }
            });
            // else Material obj have photo this mean material must be came from BuyPage
        } else {
            imageView.setImageBitmap(material.getImageInBitmap());
            btnBuyOrPost.setText("Buy");
            txtHeader.setText(material.getName());
            // Extract User information From material Already put in BuyPage
            HashMap<String,String> curUser = material.getOwner();
            String userName = curUser.get("firstName") + "" +curUser.get("lastName");
            txtName.setText(userName);
            txtNumber.setText(curUser.get("phoneNumber"));
            txtAddress.setText(curUser.get("firstName"));
        }
        btnBuyOrPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnBuyOrPost.getText().toString() == "Post") {
                    // Unique photo Name
                    String photoName = "photo_" + System.currentTimeMillis() + ".jpg";
                    StorageReference photoRef = photosRef.child(photoName);
                    // upload Image to FireStore
                    UploadTask uploadTask = photoRef.putBytes(material.getImageInByte());
                    uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                // Get the download URL to put in User UserSelling List
                                String downloadUrl = task.getResult().getStorage().getPath();
                                Map<String, String> item = new HashMap<>();
                                Map<String, Object> UserSelling= new HashMap<>();
                                item.put("name",material.getName());
                                item.put("url",downloadUrl);
                                userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot snapshot) {
                                        // Check if the UserSelling exists in the document
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
    // Take photo in another Thread
    private class TakePhotoTask extends AsyncTask<Void, Void, byte[]> {

        @Override
        protected byte[] doInBackground(Void... voids) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            while (!resultReceived) {
                // wait for the result to be received
            }
            if (imageBitmap == null) {
                // Return null if the imageBitmap is null
                return null;
            }else {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
                byte[] imageInByte = byteStream.toByteArray();
                return imageInByte;
            }
        }

        @Override
        protected void onPostExecute(byte[] imageInByte) {
            if (imageInByte == null) {
                // Finish the activity if the imageInByte array is null
                Toast.makeText(ProductPage.this, "Please Add Image your Material", Toast.LENGTH_LONG).show();
                ProductPage.this.finish();

            }else {
                material.setImage(imageInByte);
                imageView.setImageBitmap(material.getImageInBitmap());
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            resultReceived = true;
        }else {
            Toast.makeText(ProductPage.this, "Please Add Image your Material", Toast.LENGTH_LONG).show();                ProductPage.this.finish();
            ProductPage.this.finish();
        }
    }
}