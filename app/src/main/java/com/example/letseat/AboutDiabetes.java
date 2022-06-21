package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutDiabetes extends AppCompatActivity {

    private ImageView bBackDiabetes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_diabetes);

        bBackDiabetes = findViewById(R.id.btnBackDiabetes);

        bBackDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AboutDiabetes.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}