package com.example.letseat;

import static com.example.letseat.SignInActivity.formattedDate;
import static com.example.letseat.SignInActivity.newuser;
import static com.example.letseat.SignInActivity.usernm;
import static com.example.letseat.SignInActivity.asamUrat;
import static com.example.letseat.SignInActivity.diabetes;
import static com.example.letseat.SignInActivity.colestrol;
import static com.example.letseat.SignInActivity.status;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnPolaAsamurat, btnPolaDiabetes, btnPolaKolestrol, btnScan,
            btnDetailAsamurat, btnDetailDiabetes, btnDetailKolestrol, btnRiwayat;
    private TextView valDiabetTV,valAsamuratTV,valKolestrolTV,txtUser,
            resAsamUratTV, resDiabetesTV, resKolestrolTV;
    private Integer irValue;
    private long epoch;
    private boolean read = true;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        resAsamUratTV = findViewById(R.id.ResultAsamUrat);
        resDiabetesTV = findViewById(R.id.ResultDiabetes);
        resKolestrolTV = findViewById(R.id.ResultKolestrol);
        txtUser = findViewById(R.id.txtNamaPengguna);
        btnDetailAsamurat = findViewById(R.id.DetailAsamUrat);
        btnDetailDiabetes = findViewById(R.id.DetailDiabetes);
        btnDetailKolestrol = findViewById(R.id.DetailKolestrol);
        btnPolaDiabetes = findViewById(R.id.PolaDiabetes);
        btnPolaAsamurat = findViewById(R.id.PolaAsamUrat);
        btnPolaKolestrol = findViewById(R.id.PolaKolestrol);
        btnRiwayat = findViewById(R.id.riwayat);
        btnScan = findViewById(R.id.scan);
        valDiabetTV = findViewById(R.id.ValueDiabetes);
        valAsamuratTV = findViewById(R.id.ValueAsamurat);
        valKolestrolTV = findViewById(R.id.ValueKolestrol);

        epoch = System.currentTimeMillis();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        formattedDate = df.format(c);

        //Nama Pengguna
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference irv = database.getReference("reading").child("irValue");

        DatabaseReference myRef = database.getReference("user").child(newuser);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usernm  = snapshot.child("name").getValue(String.class);

                if (usernm != null) {
                    txtUser.setText(usernm + " !");
                }

                asamUrat = snapshot.child("asamUrat").getValue(Integer.class);
                diabetes = snapshot.child("diabetes").getValue(Integer.class);
                colestrol = snapshot.child("kolestrol").getValue(Integer.class);

                //if(kolestrol != null && asamUrat != null && diabetes != null){

                    if (colestrol == 0){
                        valKolestrolTV.setVisibility(View.GONE);
                        resKolestrolTV.setVisibility(View.GONE);
                    } else {
                        valKolestrolTV.setVisibility(View.VISIBLE);
                        resKolestrolTV.setVisibility(View.VISIBLE);
                    }
                    if (asamUrat == 0){
                        valAsamuratTV.setVisibility(View.GONE);
                        resAsamUratTV.setVisibility(View.GONE);
                    } else {
                        valAsamuratTV.setVisibility(View.VISIBLE);
                        resAsamUratTV.setVisibility(View.VISIBLE);
                    }
                    if (diabetes == 0){
                        valDiabetTV.setVisibility(View.GONE);
                        resDiabetesTV.setVisibility(View.GONE);
                    } else {
                        valDiabetTV.setVisibility(View.VISIBLE);
                        resDiabetesTV.setVisibility(View.VISIBLE);
                    }
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

        irv.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                irValue = snapshot.getValue(Integer.class);
                btnScan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        read = true;
                        if(irValue<90000){
                            Toast.makeText(getApplicationContext(),
                                            "Please put your finger in the right way on the scanner",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }else if(read){
                            getScanner();
                            read = false;
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        getreset();
        getValAsamUrat();
        getValDiabetes();
        getValKolestrol();

    }

    private void getScanner(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("reading");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                asamUrat = snapshot.child("asamUrat").getValue(Integer.class);
                diabetes = snapshot.child("gulaDarah").getValue(Integer.class);
                colestrol = snapshot.child("kolestrol").getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //READING
        myRef1 = database.getReference("user").child(newuser).child("asamUrat");
        myRef1.setValue(asamUrat);
        myRef1 = database.getReference("user").child(newuser).child("diabetes");
        myRef1.setValue(diabetes);
        myRef1 = database.getReference("user").child(newuser).child("kolestrol");
        myRef1.setValue(colestrol);
        myRef1 = database.getReference("reading").child("status");
        myRef1.setValue(0);

        //HISTORY
        myRef1 = database.getReference("user").child(newuser).child("history").child(""+epoch).child("asamurat");
        if(asamUrat<=2){
            myRef1.setValue("RENDAH");
        } else if(asamUrat<=7.5){
            myRef1.setValue("NORMAL");
        } else {
            myRef1.setValue("TINGGI");
        }

        myRef1 = database.getReference("user").child(newuser).child("history").child(""+epoch).child("guladarah");
        if(diabetes<=70){
            myRef1.setValue("RENDAH");
        } else if(diabetes<=100){
            myRef1.setValue("NORMAL");
        } else {
            myRef1.setValue("TINGGI");
        }

        myRef1 = database.getReference("user").child(newuser).child("history").child(""+epoch).child("kolestrol");
        if(colestrol<=200){
            myRef1.setValue("RENDAH");
        } else if(colestrol<=239){
            myRef1.setValue("NORMAL");
        } else {
            myRef1.setValue("TINGGI");
        }

        myRef1 = database.getReference("user").child(newuser).child("history").child(""+epoch).child("tanggal");
        myRef1.setValue(formattedDate);


    }

    private void getreset(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("user").child(newuser);

        if(status == 1){
            myref.child("asamUrat").setValue(0);
            myref.child("diabetes").setValue(0);
            myref.child("kolestrol").setValue(0);
            myref.child("status").setValue(0);
            status = 0;
        }
    }

    private void getValAsamUrat()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("user").child(newuser).child("asamUrat");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                asamUrat = snapshot.getValue(Integer.class);
                resAsamUratTV.setText(String.valueOf(asamUrat));

                if (asamUrat <= 2) {
                    valAsamuratTV.setText("Rendah");
                    btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaAsamuratMinus.class);
                            startActivity(i);
                        }
                    });
                } else if (asamUrat <= 7.5){
                    valAsamuratTV.setText("Normal");
                    btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaAsamuratNormal.class);
                            startActivity(i);
                        }
                    });
                } else {
                    valAsamuratTV.setText("Tinggi");
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
        DatabaseReference myref = database.getReference("user").child(newuser).child("diabetes");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                diabetes = snapshot.getValue(Integer.class);
                resDiabetesTV.setText(String.valueOf(diabetes));

                if (diabetes <= 70) {
                    valDiabetTV.setText("Rendah");
                    btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaDiabetesMinus.class);
                            startActivity(i);
                        }
                    });
                } else if (diabetes <= 100) {
                    valDiabetTV.setText("Normal");
                    btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaDiabetesNormal.class);
                            startActivity(i);
                        }
                    });
                } else {
                    valDiabetTV.setText("Tinggi");
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
        DatabaseReference myref = database.getReference("user").child(newuser).child("kolestrol");


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                colestrol = snapshot.getValue(Integer.class);
                resKolestrolTV.setText(String.valueOf(colestrol));

                if (colestrol <= 200) {
                    valKolestrolTV.setText("Rendah");
                    btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaKolestrolMinus.class);
                            startActivity(i);
                        }
                    });
                } else if (colestrol <= 239) {
                    valKolestrolTV.setText("Normal");
                    btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, PolaKolestrolNormal.class);
                            startActivity(i);
                        }
                    });
                } else {
                    valKolestrolTV.setText("Tinggi");
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




