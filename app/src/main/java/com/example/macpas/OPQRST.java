package com.example.macpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

public class OPQRST extends AppCompatActivity {
    private TextView subdisplay;

    private Button O;
    private Button P;
    private Button Q;
    private Button R2;
    private Button S;
    private Button T;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opqrst);


        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);

        O = (Button) findViewById(R.id.button6);
        P = (Button) findViewById(R.id.button5);
        Q = (Button) findViewById(R.id.button4);
        R2 = (Button) findViewById(R.id.button3);
        S = (Button) findViewById(R.id.button);
        T = (Button) findViewById(R.id.button2);
        cancel = (Button) findViewById(R.id.button8);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "O";
                subdisplay.setText(str);
                openMain();
            }
        });

        P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "P";
                subdisplay.setText(str);
                openMain();
            }
        });

        Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "Q";
                subdisplay.setText(str);
                openMain();
            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "R";
                subdisplay.setText(str);
                openMain();
            }
        });

        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "S";
                subdisplay.setText(str);
                openMain();
            }
        });

        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "T";
                subdisplay.setText(str);
                openMain();
            }
        });


    }
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("subdisplay",subdisplay.getText());
        startActivity(intent);
    }


}
