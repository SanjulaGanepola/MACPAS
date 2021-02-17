package com.example.macpas;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class NumPunc extends AppCompatActivity {
    private TextView subdisplay;

    private Button num;
    private Button punc;
    private Button cancel;

    private Button buttonScan;
    private int[] arrButton = {R.id.button6,R.id.button4,R.id.button8,
                               R.id.button6,R.id.button4,R.id.button8};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private TextToSpeech mTTS;

    private int subSpeed;
    private int subTheme;
    private String subText;
    private int buttonColor;

    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_punc);

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

        cancel = (Button) findViewById(R.id.button8);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        num = (Button) findViewById(R.id.button6);
        punc = (Button) findViewById(R.id.button4);

        /*
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openNum();
            }
        });
        punc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openPunc();
            }
        });
         */

        setNewTheme();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                if(!isStarted){
                    scanning();
                }
            }
        }, 1000);

        buttonScan = (Button) findViewById(R.id.button10);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanning();
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

    public void openNum() {
        Intent intent = new Intent(this, Num.class);
        intent.putExtra("toNumDisplay",subdisplay.getText());
        intent.putExtra("toNumSpeed",subSpeed);
        intent.putExtra("toNumTheme",subTheme);
        startActivity(intent);
    }

    public void openPunc() {
        Intent intent = new Intent(this, Punc.class);
        intent.putExtra("toPuncDisplay",subdisplay.getText());
        intent.putExtra("toPuncSpeed",subSpeed);
        intent.putExtra("toPuncTheme",subTheme);
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

        num.setTextColor(textColor);
        num.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        punc.setTextColor(textColor);
        punc.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        cancel.setTextColor(textColor);
        cancel.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
    }

    public void scanning() {
        isStarted = true;

        if(scanning == false) {
            //buttonScan.setText("SELECT");
            scanning = true;
            timer = new CountDownTimer(6*subSpeed, subSpeed) {
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

            if(temp > 2) {
                temp = temp - 3;
            }

            switch(temp){
                case 0:
                    openNum();
                    break;
                case 1:
                    openPunc();
                    break;
                case 2:
                    openMain();
                    break;
            }
            scanning = false;
        }
    }
}