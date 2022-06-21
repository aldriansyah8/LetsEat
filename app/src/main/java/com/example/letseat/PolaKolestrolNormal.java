package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PolaKolestrolNormal extends AppCompatActivity {

    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pola_kolestrol_normal);

        btnBack = findViewById(R.id.bBackKolestrolNormal);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(PolaKolestrolNormal.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}