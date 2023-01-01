package gui.ceng.mu.edu.reapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView email;
    TextView password;
    TextView firstName;
    TextView lastName;
    TextView phoneNumber;
    TextView address;
    TextView birthDate;
    TextView passwordAgain;
    CheckBox buyer;
    CheckBox seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //User
        HashMap<String ,String> user = new HashMap<>();
        //fireBase Stuff
        FirebaseFirestore db = 	FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = findViewById(R.id.txtpassword);
                passwordAgain = findViewById(R.id.txtPasswordAgain);
                email = findViewById(R.id.txtemail);
                if(!password.getText().toString().equals(passwordAgain.getText().toString())){
                    Toast.makeText(Register.this,"Password Again and password must be same ",Toast.LENGTH_SHORT).show();
                }else {
                    firstName = findViewById(R.id.txtfirstname);
                    lastName = findViewById(R.id.txtlastname);
                    phoneNumber = findViewById(R.id.txtphone);
                    address = findViewById(R.id.txtaddress);
                    birthDate = findViewById(R.id.txtdate);
                    buyer = findViewById(R.id.chkBuyer);
                    seller = findViewById(R.id.chkSeller);
                    user.put("firstName", firstName.getText().toString());
                    user.put("lastName", lastName.getText().toString());
                    user.put("phoneNumber", phoneNumber.getText().toString());
                    user.put("address", address.getText().toString());
                    user.put("birthDate", birthDate.getText().toString());
                    user.put("email",email.getText().toString());
                    if(buyer.isChecked() && !seller.isChecked()){
                        user.put("userType","buyer");
                    }else if(seller.isChecked() && !buyer.isChecked()){
                        user.put("userType","seller");
                    }else {
                        Toast.makeText(Register.this, "Please choose your user type", Toast.LENGTH_SHORT).show();
                    }
                    password = findViewById(R.id.txtpassword);
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign up success, update UI with the signed-in user's information
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DocumentReference userRef = db.collection("users").document(currentUser.getUid());
                                        userRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Register.this, "Student inserted with id " +
                                                        userRef.getId(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                        Register.this.finish();
                                    } else {
                                        // If sign up fails, display a message to the user
                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}