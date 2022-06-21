package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutAsamUrat extends AppCompatActivity {

    private ImageView bBackAsamurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_asam_urat);

        bBackAsamurat = findViewById(R.id.btnBackAsamurat);

        bBackAsamurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AboutAsamUrat.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}