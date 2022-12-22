package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class ProductPage extends AppCompatActivity implements Serializable {
    private boolean resultReceived = false;
    int REQUEST_IMAGE_CAPTURE = 0;
    private Bitmap imageBitmap;
    Material material;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        material = (Material) getIntent().getSerializableExtra("onChosen");
        TextView txtHeader = findViewById(R.id.txtPHeader);
        TextView txtDesc = findViewById(R.id.txtPDesc);
        TextView txtName = findViewById(R.id.txtPUserName);
        TextView txtNumber = findViewById(R.id.txtPUserNumber);
        TextView txtAddress = findViewById(R.id.txtPUserAdress);
        ImageView imageView = imageView = findViewById(R.id.imgPView);
        Button btnBuyOrPost = findViewById(R.id.BuyOrPost);
        txtDesc.setText(material.getName());
        if(material.getImage() == null){
            new TakePhotoTask().execute();
            imageView.setImageBitmap(material.getImage());
        }else {
            imageView.setImageBitmap(material.getImage());
        }
        btnBuyOrPost.setText("Post");

    }
    private class TakePhotoTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            while (!resultReceived) {
                // wait for the result to be received
            }
            return imageBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap imageBitmap) {

            material.setImage(imageBitmap);
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