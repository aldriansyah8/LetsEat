package com.example.letseat;

import static com.example.letseat.SignInActivity.newuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HistoryActivity extends AppCompatActivity {

    private Button btnLogout;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private historyAdapter adapter; // Create Object of the Adapter class
    private DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnLogout = findViewById(R.id.logout);
        mbase = FirebaseDatabase.getInstance().getReference("user").child(newuser).child("history");

        recyclerView = findViewById(R.id.historyUser_rc);
        // To display the Recycler view linearlly

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to mke a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<History> options
                = new FirebaseRecyclerOptions.Builder<History>()
                .setQuery(mbase, History.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new historyAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                logoutAccount();
            }
        });
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    private void logoutAccount (){
        //mAuth.signOut();
        Intent intent
                = new Intent(HistoryActivity.this, SignInActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Log out berhasil", Toast.LENGTH_LONG)
                .show();

    }
}