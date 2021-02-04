package com.example.macpas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Punc extends AppCompatActivity {
    private TextView subdisplay;

    private Button question;
    private Button exclamation;
    private Button comma;
    private Button and;
    private Button period;
    private Button at;
    private Button cancel;

    private Button buttonScan;
    private int[] arrButton = {R.id.button6,R.id.button5,R.id.button4,R.id.button3,R.id.button7,R.id.button9,R.id.button8};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private TextToSpeech mTTS;
    private int subSpeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punc);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subSpeed = extras.getInt("toPuncSpeed");
        extras.remove("toPuncSpeed");
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);

        question = (Button) findViewById(R.id.button6);
        exclamation = (Button) findViewById(R.id.button5);
        comma = (Button) findViewById(R.id.button4);
        and = (Button) findViewById(R.id.button3);
        period = (Button) findViewById(R.id.button7);
        at = (Button) findViewById(R.id.button9);
        cancel = (Button) findViewById(R.id.button8);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNumPunc();
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "?";
                subdisplay.setText(str);
                openMain();
            }
        });

        exclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "!";
                subdisplay.setText(str);
                openMain();
            }
        });

        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += ",";
                subdisplay.setText(str);
                openMain();
            }
        });

        and.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "&";
                subdisplay.setText(str);
                openMain();
            }
        });

        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += ".";
                subdisplay.setText(str);
                openMain();
            }
        });
        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "@";
                subdisplay.setText(str);
                openMain();
            }
        });
        buttonScan = (Button) findViewById(R.id.button13);
        buttonScan.setText(Integer.toString(subSpeed));
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(7*subSpeed, subSpeed) {
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
                            str += "?";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 1:
                            str = subdisplay.getText().toString();
                            str += "!";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 2:
                            str = subdisplay.getText().toString();
                            str += ",";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 3:
                            str = subdisplay.getText().toString();
                            str += "&";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 4:
                            str = subdisplay.getText().toString();
                            str += ".";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 5:
                            str = subdisplay.getText().toString();
                            str += "@";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 6:
                            openNumPunc();
                            break;
                    }
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
        startActivity(intent);
    }

    public void openNumPunc() {
        Intent intent = new Intent(this, NumPunc.class);
        intent.putExtra("display",subdisplay.getText());
        intent.putExtra("toSubSpeed",subSpeed);
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
}
