package com.example.letseat;

import static com.example.letseat.SignInActivity.newuser;
import static com.example.letseat.SignInActivity.usernm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnPolaAsamurat, btnPolaDiabetes, btnPolaKolestrol, btnScan,
            btnDetailAsamurat, btnDetailDiabetes, btnDetailKolestrol, btnRiwayat;

    private TextView valDiabet,valAsamurat,valKolestrol,txtUser,resAsamUrat,resDiabetes,resKolestrol;
    public float ValueAsamurat,ValueDiabetes,ValueKolestrol;
    //public static EditText txtemail,txtpassword;
    //protected static String usernm, newuser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        resAsamUrat = findViewById(R.id.ResultAsamUrat);
        resDiabetes = findViewById(R.id.ResultDiabetes);
        resKolestrol = findViewById(R.id.ResultKolestrol);
        txtUser = findViewById(R.id.txtNamaPengguna);
        btnDetailAsamurat = findViewById(R.id.DetailAsamUrat);
        btnDetailDiabetes = findViewById(R.id.DetailDiabetes);
        btnDetailKolestrol = findViewById(R.id.DetailKolestrol);
        btnPolaDiabetes = findViewById(R.id.PolaDiabetes);
        btnPolaAsamurat = findViewById(R.id.PolaAsamUrat);
        btnPolaKolestrol = findViewById(R.id.PolaKolestrol);
        btnRiwayat = findViewById(R.id.riwayat);
        btnScan = findViewById(R.id.scan);
        valDiabet = findViewById(R.id.ValueDiabetes);
        valAsamurat = findViewById(R.id.ValueAsamurat);
        valKolestrol = findViewById(R.id.ValueKolestrol);

        //Nama Pengguna
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user").child(newuser).child("name");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usernm  = snapshot.getValue(String.class);
                txtUser.setText(usernm + " !");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
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

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScanner();
            }
        });

        getValAsamUrat();
        getValDiabetes();
        getValKolestrol();


    }

    private void getScanner(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("user").child(newuser).child("history")
                                .child("Asam Urat");
        myRef.setValue(ValueAsamurat);

        myRef = database.getReference("user").child(newuser).child("history").child("Diabetes");
        myRef.setValue(ValueDiabetes);

        myRef = database.getReference("user").child(newuser).child("history").child("Kolestrol");
        myRef.setValue(ValueKolestrol);

    }

    private void getValAsamUrat()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("reading").child("asamUrat");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ValueAsamurat = snapshot.getValue(Integer.class);
                resAsamUrat.setText(String.valueOf(ValueAsamurat));

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
                resDiabetes.setText(String.valueOf(ValueDiabetes));

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
                resKolestrol.setText(String.valueOf(ValueKolestrol));

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
}




