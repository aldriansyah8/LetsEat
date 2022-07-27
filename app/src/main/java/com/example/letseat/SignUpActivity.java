package com.example.letseat;

import static com.example.letseat.SignInActivity.usernm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    private EditText txtpassword, txtnama, txthandphone, txtemail;
    private TextView login;
    private Button bRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        txtnama = findViewById(R.id.txtNama);
        txtemail = findViewById(R.id.txtEmail);
        txtpassword = findViewById(R.id.txtPassword);
        txthandphone = findViewById(R.id.txtPassword);
        login = findViewById(R.id.txtMasuk);
        bRegister = findViewById(R.id.register);

        //Menuju Halaman OTP
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });

        //Menuju Halaman Sign in
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }

    private void registerNewUser(){

        String email, password, handphone;
        email = txtemail.getText().toString();
        password = txtpassword.getText().toString();
        handphone = txthandphone.getText().toString();

        usernm = txtnama.getText().toString();
        String newuser = email.substring(0,5);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user").child(newuser).child("name");
        myRef.setValue(usernm);

        myRef = database.getReference("user").child(newuser).child("email");
        myRef.setValue(email);

        myRef = database.getReference("user").child(newuser).child("password");
        myRef.setValue(password);

        myRef = database.getReference("user").child(newuser).child("asamUrat");
        myRef.setValue(0);

        myRef = database.getReference("user").child(newuser).child("diabetes");
        myRef.setValue(0);

        myRef = database.getReference("user").child(newuser).child("kolestrol");
        myRef.setValue(0);



        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter youremail!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }


        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();


                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(SignUpActivity.this,
                                    SignInActivity.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                });
    }
}