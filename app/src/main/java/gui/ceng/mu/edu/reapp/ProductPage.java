package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        TextView txtHeader = findViewById(R.id.txtPHeader);
        ImageView imgProduct = findViewById(R.id.imgPView);
        TextView txtDesc = findViewById(R.id.txtPDesc);
        TextView txtName = findViewById(R.id.txtPUserName);
        TextView txtNumber = findViewById(R.id.txtPUserNumber);
        TextView txtAddress = findViewById(R.id.txtPUserAdress);
        Button btnBuy = findViewById(R.id.BuyOrPost);

    }
}