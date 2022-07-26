package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class HistoryActivity extends AppCompatActivity {

    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                logoutAccount();
            }
        });
    }

    private void logoutAccount (){
        mAuth.signOut();
        Intent intent
                = new Intent(HistoryActivity.this, SignInActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Log out berhasil", Toast.LENGTH_LONG)
                .show();

    }
}