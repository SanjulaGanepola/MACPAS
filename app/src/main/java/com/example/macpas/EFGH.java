package com.example.macpas;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import java.util.Locale;

public class EFGH extends AppCompatActivity {
    private TextView subdisplay;

    private Button E;
    private Button F;
    private Button G;
    private Button H;
    private Button cancel;

    private Button buttonScan;
    private int[] arrButton = {R.id.button6,R.id.button5,R.id.button4,R.id.button3,R.id.button8};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private TextToSpeech mTTS;

    private int subSpeed;
    private int subTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efgh);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);
        subSpeed = extras.getInt("toSubSpeed");
        extras.remove("toSubSpeed");

        subdisplay.setBackgroundColor(Color.WHITE);
        subdisplay.setTextColor(Color.BLACK);
        subTheme = extras.getInt("toSubTheme");
        extras.remove("toSubTheme");

        E = (Button) findViewById(R.id.button6);
        F = (Button) findViewById(R.id.button5);
        G = (Button) findViewById(R.id.button4);
        H = (Button) findViewById(R.id.button3);
        cancel = (Button) findViewById(R.id.button8);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "E";
                subdisplay.setText(str);
                openMain();
            }
        });

        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "F";
                subdisplay.setText(str);
                openMain();
            }
        });

        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "G";
                subdisplay.setText(str);
                openMain();
            }
        });

        H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "H";
                subdisplay.setText(str);
                openMain();
            }
        });

        setNewTheme();

        buttonScan = (Button) findViewById(R.id.button11);
        buttonScan.setText(Integer.toString(subTheme));
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(5*subSpeed, subSpeed) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            Button prevButton = (Button) findViewById(arrButton[current - 1]);
                            prevButton.setBackground(buttonScan.getBackground());
                            current = 0;
                            //buttonScan.setText("SCAN");
                            scanning = false;
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                            Button currButton = (Button) findViewById(arrButton[current]);
                            currButton.setBackgroundColor(Color.BLUE);
                            mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                            if (current > 0) {
                                Button prevButton = (Button) findViewById(arrButton[current - 1]);
                                prevButton.setBackground(buttonScan.getBackground());
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
                    switch(temp){
                        case 0:
                            str = subdisplay.getText().toString();
                            str += "E";
                            subdisplay.setText(str);
                            break;
                        case 1:
                            str = subdisplay.getText().toString();
                            str += "F";
                            subdisplay.setText(str);
                            break;
                        case 2:
                            str = subdisplay.getText().toString();
                            str += "G";
                            subdisplay.setText(str);
                            break;
                        case 3:
                            str = subdisplay.getText().toString();
                            str += "H";
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
        intent.putExtra("display",subdisplay.getText());
        intent.putExtra("toHomeSpeed",subSpeed);
        intent.putExtra("toHomeTheme",subTheme);
        startActivity(intent);
    }

    public void resetColour(int current){
        Button prevButton = (Button) findViewById(arrButton[current - 1]);
        prevButton.setBackground(buttonScan.getBackground());
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
        int buttonColor = Color.parseColor("#D7D8D6");
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

        E.setTextColor(textColor);
        E.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        F.setTextColor(textColor);
        F.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        G.setTextColor(textColor);
        G.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        H.setTextColor(textColor);
        H.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        cancel.setTextColor(textColor);
        cancel.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
    }
}
