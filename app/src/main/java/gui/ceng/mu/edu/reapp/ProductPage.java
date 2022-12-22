package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ProductPage extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        Material material = (Material) getIntent().getSerializableExtra("onChoosen");
        TextView txtHeader = findViewById(R.id.txtPHeader);
        ImageView imageView = findViewById(R.id.imgPView);
        TextView txtDesc = findViewById(R.id.txtPDesc);
        TextView txtName = findViewById(R.id.txtPUserName);
        TextView txtNumber = findViewById(R.id.txtPUserNumber);
        TextView txtAddress = findViewById(R.id.txtPUserAdress);
        Button btnBuy = findViewById(R.id.BuyOrPost);

        txtDesc.setText(material.getName());
        imageView.setImageResource(material.getImage());

    }
}