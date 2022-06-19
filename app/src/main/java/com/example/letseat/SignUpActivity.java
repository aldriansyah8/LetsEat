package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView login;
    private ImageButton bOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = findViewById(R.id.txtMasuk);
        bOtp = findViewById(R.id.btnOtp);

        //Menuju Halaman OTP
        bOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SignUpActivity.this, OtpActivity.class);
                startActivity(i);
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
}