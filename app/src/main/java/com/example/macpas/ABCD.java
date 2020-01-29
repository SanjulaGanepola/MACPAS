package com.example.macpas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ABCD extends AppCompatActivity {
    private TextView subdisplay;

    private Button A;
    private Button B;
    private Button C;
    private Button D;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abcd);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);

        A = (Button) findViewById(R.id.button6);
        B = (Button) findViewById(R.id.button5);
        C = (Button) findViewById(R.id.button4);
        D = (Button) findViewById(R.id.button3);
        cancel = (Button) findViewById(R.id.button8);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "A";
                subdisplay.setText(str);
                openMain();
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "B";
                subdisplay.setText(str);
                openMain();
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "C";
                subdisplay.setText(str);
                openMain();
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = subdisplay.getText().toString();
                str += "D";
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