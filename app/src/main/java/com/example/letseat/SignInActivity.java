package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    private ImageButton blogin;
    private TextView otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        blogin = findViewById(R.id.btnLogin);
        otp = findViewById(R.id.linkOtp);

        //Tombol Login
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        //Lupa password >> ke halaman OTP
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SignInActivity.this, OtpActivity.class);
                startActivity(i);
            }
        });
    }
}