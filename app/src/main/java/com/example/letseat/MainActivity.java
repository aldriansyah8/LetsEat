package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnPolaAsamurat, btnPolaDiabetes, btnPolaKolestrol,
            btnDetailAsamurat, btnDetailDiabetes, btnDetailKolestrol;

    private TextView valDiabet,valAsamurat,valKolestrol;

    String ValueDiabetes, ValueAsamurat, ValueKolestrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDetailAsamurat = findViewById(R.id.DetailAsamUrat);
        btnDetailDiabetes = findViewById(R.id.DetailDiabetes);
        btnDetailKolestrol = findViewById(R.id.DetailKolestrol);
        btnPolaDiabetes = findViewById(R.id.PolaDiabetes);
        btnPolaAsamurat = findViewById(R.id.PolaAsamUrat);
        btnPolaKolestrol = findViewById(R.id.PolaKolestrol);
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

        //Val Status Asam Urat data nantinya dari firebase
        ValueAsamurat = "+";
        valAsamurat.setText(ValueAsamurat);

        //Button perulangan Pola Makan berdasarkan status Asam Urat
        if (ValueAsamurat == "-") {
            btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaAsamuratMinus.class);
                    startActivity(i);
                }
            });
        } else if (ValueAsamurat == "N") {
            btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaAsamuratNormal.class);
                    startActivity(i);
                }
            });
        } else if (ValueAsamurat == "+") {
            btnPolaAsamurat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaAsamuratPlus.class);
                    startActivity(i);
                }
            });
        }


        //Val Status Diabetes data nantinya dari firebase
        ValueDiabetes = "+";
        valDiabet.setText(ValueDiabetes);

        //Button perulangan Pola Makan berdasarkan status Diabetes
        if (ValueDiabetes == "-") {
            btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaDiabetesMinus.class);
                    startActivity(i);
                }
            });
        } else if (ValueDiabetes == "N") {
            btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaDiabetesNormal.class);
                    startActivity(i);
                }
            });
        } else if (ValueDiabetes == "+") {
            btnPolaDiabetes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaDiabetesPlus.class);
                    startActivity(i);
                }
            });
        }

        //Val Status Kolestrol data nantinya dari firebase
        ValueKolestrol = "+";
        valKolestrol.setText(ValueKolestrol);

        //Button perulangan Pola Makan berdasarkan status Kolestrol
        if (ValueKolestrol == "-") {
            btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaKolestrolMinus.class);
                    startActivity(i);
                }
            });
        } else if (ValueKolestrol == "N") {
            btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaKolestrolNormal.class);
                    startActivity(i);
                }
            });
        } else if (ValueKolestrol == "+") {
            btnPolaKolestrol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PolaKolestrolPlus.class);
                    startActivity(i);
                }
            });
        }


    }
}