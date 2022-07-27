package com.example.letseat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private ImageButton blogin;
    private EditText txtemail, txtpassword;
    private TextView otp;

    protected static String usernm, newuser,formattedDate;
    protected static Integer asamUrat, diabetes, colestrol, status;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        blogin = findViewById(R.id.btnLogin);
        otp = findViewById(R.id.linkOtp);
        txtemail = findViewById(R.id.txtEmail2);
        txtpassword = findViewById(R.id.txtPassword2);

        //Tombol Login
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                loginUserAccount();
            }
        });

        //Lupa password >> ke halaman Registrasi
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUserAccount(){

        String email, password;
        email = txtemail.getText().toString();
        password = txtpassword.getText().toString();
        newuser = email.substring(0,5);

        status =1;

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter your name!!",
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

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar

                                    // if sign-in is successful
                                    // intent to home activity


                                    Intent intent
                                            = new Intent(SignInActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);

                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                }
                            }
                        });

    }
}