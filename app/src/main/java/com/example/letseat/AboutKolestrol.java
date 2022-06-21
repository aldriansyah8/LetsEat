package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutKolestrol extends AppCompatActivity {

    private ImageView bBackKolestrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_kolestrol);

        bBackKolestrol = findViewById(R.id.btnBackKolestrol);

        bBackKolestrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AboutKolestrol.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}