package gui.ceng.mu.edu.reapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button btnLogin;
    Button btnBack;
    TextView email;
    TextView password;
    FirebaseAuth mAuth;
    CheckBox chkRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txtEmail);
        chkRemember = findViewById(R.id.cbRememberMe);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnlogin_pagelogin);
        btnBack = findViewById(R.id.btnback_pagelogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Remember me Part
                SharedPreferences pref = getSharedPreferences("apppref",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if (chkRemember.isChecked()) {
                    editor.putString("username", email.getText().toString());
                } else {
                    editor.remove("username");
                }
                editor.putBoolean("remember", chkRemember.isChecked());
                editor.commit();
                // Authentication part send the information to fireBase Auth to check
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).
                        addOnCompleteListener(Login.this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("AUTH", "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Intent i = new Intent(Login.this, Menu.class);
                                            startActivity(i);
                                            Login.this.finish();
                                        } else {
                                            // If sign in fails, display a message to the user
                                            Log.d("AUTH", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(Login.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

            }
        });
        // save the username in device
        SharedPreferences pref = getSharedPreferences("apppref",
                Context.MODE_PRIVATE);
        String username = pref.getString("username","");
        email.setText(username);
        chkRemember.setChecked(pref.getBoolean("remember",false));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.this.finish();
            }
        });
    }
}