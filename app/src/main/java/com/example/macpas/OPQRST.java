package com.example.macpas;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Toast;

import java.util.Locale;

public class OPQRST extends AppCompatActivity {
    private TextView subdisplay;

    private Button O;
    private Button P;
    private Button Q;
    private Button R2;
    private Button S;
    private Button T;
    private Button cancel;

    private Button buttonScan;
    private int[] arrButton = {R.id.button6,R.id.button5,R.id.button4,R.id.button3,R.id.button,R.id.button2,R.id.button8,
                               R.id.button6,R.id.button5,R.id.button4,R.id.button3,R.id.button,R.id.button2,R.id.button8};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private TextToSpeech mTTS;

    private int subSpeed;
    private int subTheme;
    private String subText;
    private int buttonColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opqrst);

        Bundle extras = getIntent().getExtras();
        subText = extras.getString("toSubDisplay");
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(subText);
        subdisplay.setBackgroundColor(Color.WHITE);
        subdisplay.setTextColor(Color.BLACK);

        subSpeed = extras.getInt("toSubSpeed");
        extras.remove("toSubSpeed");
        subTheme = extras.getInt("toSubTheme");
        extras.remove("toSubTheme");

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

        /*
        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "O";
                subdisplay.setText(str);
                openMain();
            }
        });

        P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "P";
                subdisplay.setText(str);
                openMain();
            }
        });

        Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "Q";
                subdisplay.setText(str);
                openMain();
            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "R";
                subdisplay.setText(str);
                openMain();
            }
        });

        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "S";
                subdisplay.setText(str);
                openMain();
            }
        });

        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "T";
                subdisplay.setText(str);
                openMain();
            }
        });
        */

        setNewTheme();

        buttonScan = (Button) findViewById(R.id.button14);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(14*subSpeed, subSpeed) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            Button prevButton = (Button) findViewById(arrButton[current - 1]);
                            prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
                            current = 0;
                            //buttonScan.setText("SCAN");
                            scanning = false;
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                            Button currButton = (Button) findViewById(arrButton[current]);
                            currButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3EB0A6")));
                            mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                            if (current > 0) {
                                Button prevButton = (Button) findViewById(arrButton[current - 1]);
                                prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
                            }
                            current++;
                        }
                    }.start();
                }
                else{
                    buttonScan.setEnabled(false);
                    timer.cancel();
                    //buttonScan.setText("SCAN");
                    int temp = current-1;
                    resetColour(current);
                    current = 0;
                    String str;

                    if(temp > 6) {
                        temp = temp - 7;
                    }

                    switch(temp){
                        case 0:
                            str = subdisplay.getText().toString();
                            str += "O";
                            subdisplay.setText(str);
                            break;
                        case 1:
                            str = subdisplay.getText().toString();
                            str += "P";
                            subdisplay.setText(str);
                            break;
                        case 2:
                            str = subdisplay.getText().toString();
                            str += "Q";
                            subdisplay.setText(str);
                            break;
                        case 3:
                            str = subdisplay.getText().toString();
                            str += "R";
                            subdisplay.setText(str);
                            break;
                        case 4:
                            str = subdisplay.getText().toString();
                            str += "S";
                            subdisplay.setText(str);
                            break;
                        case 5:
                            str = subdisplay.getText().toString();
                            str += "T";
                            subdisplay.setText(str);
                            break;
                    }
                    openMain();
                    scanning = false;
                }

            }

        });
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

    }
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("toHomeDisplay",subdisplay.getText());
        intent.putExtra("toHomeSpeed",subSpeed);
        intent.putExtra("toHomeTheme",subTheme);
        startActivity(intent);
    }
    public void resetColour(int current){
        Button prevButton = (Button) findViewById(arrButton[current - 1]);
        prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
    }

    @Override
    public void onBackPressed() {
        Context context = getApplicationContext();
        CharSequence text = "Back Disabled";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void setNewTheme() {
        int textColor = Color.BLACK;
        buttonColor = Color.parseColor("#D7D8D6");
        int backgroundColor = Color.WHITE;

        switch (subTheme) {
            case 2:
                textColor = Color.WHITE;
                buttonColor = Color.BLACK;
                backgroundColor = Color.WHITE;
                break;
            case 3:
                textColor = Color.parseColor("#000080");
                buttonColor = Color.parseColor("#FFFF00");
                backgroundColor = Color.parseColor("#000080");
                break;
            case 4:
                textColor = Color.parseColor("#FFFF00");
                buttonColor = Color.parseColor("#000080");
                backgroundColor = Color.parseColor("#FFFF00");
                break;
        }

        LinearLayout back =(LinearLayout)findViewById(R.id.backlayout);
        back.setBackgroundColor(backgroundColor);

        O.setTextColor(textColor);
        O.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        P.setTextColor(textColor);
        P.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        Q.setTextColor(textColor);
        Q.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        R2.setTextColor(textColor);
        R2.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        S.setTextColor(textColor);
        S.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        T.setTextColor(textColor);
        T.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        cancel.setTextColor(textColor);
        cancel.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
    }
}
