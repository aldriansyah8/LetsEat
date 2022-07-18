package com.example.letseat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnPolaAsamurat, btnPolaDiabetes, btnPolaKolestrol,
            btnDetailAsamurat, btnDetailDiabetes, btnDetailKolestrol, btnLogout;

    private TextView valDiabet,valAsamurat,valKolestrol,txtUser;
    float ValueAsamurat,ValueDiabetes,ValueKolestrol;
    public static EditText txtemail,txtpassword;

    protected static String usernm, newuser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        txtUser = findViewById(R.id.txtNamaPengguna);
        btnDetailAsamurat = findViewById(R.id.DetailAsamUrat);
        btnDetailDiabetes = findViewById(R.id.DetailDiabetes);
        btnDetailKolestrol = findViewById(R.id.DetailKolestrol);
        btnPolaDiabetes = findViewById(R.id.PolaDiabetes);
        btnPolaAsamurat = findViewById(R.id.PolaAsamUrat);
        btnPolaKolestrol = findViewById(R.id.PolaKolestrol);
        btnLogout = findViewById(R.id.logout);
        valDiabet = findViewById(R.id.ValueDiabetes);
        valAsamurat = findViewById(R.id.ValueAsamurat);
        valKolestrol = findViewById(R.id.ValueKolestrol);

        //Button Selengkapnya Asam Urat
        btnDetailAsamurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutAsamUrat.class);
                startActivity(i);
            }
        });

        //Button Selengkapnya Diabetes
        btnDetailDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutDiabetes.class);
                startActivity(i);
            }
        });

        //Button Selengkapnya Kolestrol
        btnDetailKolestrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutKolestrol.class);
                startActivity(i);
            }
        });

        getValAsamUrat();
        getValDiabetes();
        getValKolestrol();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                logoutAccount();
            }
        });

    }

    private void getValAsamUrat()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("reading").child("asamUrat");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ValueAsamurat = snapshot.getValue(Integer.class);

                if (ValueAsamurat <= 2) {
                    valAsamurat.setText("Rendah");
                    btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaAsamuratMinus.class);
                            startActivity(i);
                        }
                    });
                } else if (ValueAsamurat <= 7.5){
                    valAsamurat.setText("Normal");
                    btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaAsamuratNormal.class);
                            startActivity(i);
                        }
                    });
                } else {
                    valAsamurat.setText("Tinggi");
                    btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaAsamuratPlus.class);
                            startActivity(i);
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getValDiabetes(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("reading").child("gulaDarah");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ValueDiabetes = snapshot.getValue(Integer.class);

                if (ValueDiabetes <= 70) {
                    valDiabet.setText("Rendah");
                    btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaDiabetesMinus.class);
                            startActivity(i);
                        }
                    });
                } else if (ValueDiabetes <= 100) {
                    valDiabet.setText("Normal");
                    btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaDiabetesNormal.class);
                            startActivity(i);
                        }
                    });
                } else {
                    valDiabet.setText("Tinggi");
                    btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaDiabetesPlus.class);
                            startActivity(i);
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getValKolestrol() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("reading").child("kolestrol");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ValueKolestrol = snapshot.getValue(Integer.class);

                if (ValueKolestrol <= 200) {
                    valKolestrol.setText("Rendah");
                    btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaKolestrolMinus.class);
                            startActivity(i);
                        }
                    });
                } else if (ValueKolestrol <= 239) {
                    valKolestrol.setText("Normal");
                    btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaKolestrolNormal.class);
                            startActivity(i);
                        }
                    });
                } else {
                    valKolestrol.setText("Tinggi");
                    btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaKolestrolPlus.class);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void logoutAccount (){
        mAuth.signOut();
            Intent intent
                    = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Log out berhasil", Toast.LENGTH_LONG)
                    .show();

                }


        }




