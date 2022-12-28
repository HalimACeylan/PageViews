package gui.ceng.mu.edu.reapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    Button signup;
    Button btnCLosest;
    Button btnThings;
    Button btnAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        loginBtn = findViewById(R.id.btnlogin);
        signup = findViewById(R.id.btnsignup);
        btnCLosest = findViewById(R.id.btnClosest);
        btnThings = findViewById(R.id.btnThings);
        btnAbout = findViewById(R.id.btnAbout);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigation to login
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigation to Sign Up
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });
    btnCLosest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //navigation to ClosestLocation
            Intent i = new Intent(MainActivity.this,ClosestLocation.class);
            startActivity(i);
        }
    });
    btnThings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //navigation to ThingsToKnow
            Intent i = new Intent(MainActivity.this,ThingsToKnow.class);
            startActivity(i);
        }
    });


    }


}