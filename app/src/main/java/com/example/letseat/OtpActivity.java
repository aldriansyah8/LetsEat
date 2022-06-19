package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class OtpActivity extends AppCompatActivity {

    private TextView otpvalue;
    private EditText otpin;
    private ImageButton bResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpvalue = findViewById(R.id.otpView);
        otpin = findViewById(R.id.otpInput);
        bResend = findViewById(R.id.btnOtpResend);

        //Realtime OTP input text
        otpin.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {   //Convert the Text to String
                String inputText = otpin.getText().toString();
                otpvalue.setText(inputText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }
        });

        //Tombol
        bResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(OtpActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}