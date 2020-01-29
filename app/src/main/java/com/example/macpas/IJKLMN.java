package com.example.macpas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

public class IJKLMN extends AppCompatActivity {
    private TextView subdisplay;

    private Button I;
    private Button J;
    private Button K;
    private Button L;
    private Button M;
    private Button N;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijklmn);


        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);

        I = (Button) findViewById(R.id.button6);
        J = (Button) findViewById(R.id.button5);
        K = (Button) findViewById(R.id.button4);
        L = (Button) findViewById(R.id.button3);
        M = (Button) findViewById(R.id.button7);
        N = (Button) findViewById(R.id.button9);
        cancel = (Button) findViewById(R.id.button8);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "I";
                subdisplay.setText(str);
                openMain();
            }
        });

        J.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "J";
                subdisplay.setText(str);
                openMain();
            }
        });

        K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "K";
                subdisplay.setText(str);
                openMain();
            }
        });

        L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "L";
                subdisplay.setText(str);
                openMain();
            }
        });

        M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "M";
                subdisplay.setText(str);
                openMain();
            }
        });
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "N";
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
