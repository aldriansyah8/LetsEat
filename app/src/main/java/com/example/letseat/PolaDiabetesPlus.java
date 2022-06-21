package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PolaDiabetesPlus extends AppCompatActivity {

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pola_diabetes_plus);

        btnBack = findViewById(R.id.bBackDiabetesPlus);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(PolaDiabetesPlus.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}