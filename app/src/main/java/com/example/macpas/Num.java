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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Num extends AppCompatActivity {
    private TextView subdisplay;

    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button cancel;

    private Button buttonScan;
    private int[] arrButton = {R.id.button6,R.id.button5,R.id.button16,R.id.button4,R.id.button3,R.id.button17,R.id.button7,R.id.button9,R.id.button18,R.id.button19,R.id.button8,
                               R.id.button6,R.id.button5,R.id.button16,R.id.button4,R.id.button3,R.id.button17,R.id.button7,R.id.button9,R.id.button18,R.id.button19,R.id.button8};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private TextToSpeech mTTS;
    private int subSpeed;
    private int subTheme;
    private int buttonColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subSpeed = extras.getInt("toNumSpeed");
        extras.remove("toNumSpeed");
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);

        subdisplay.setBackgroundColor(Color.WHITE);
        subdisplay.setTextColor(Color.BLACK);
        subTheme = extras.getInt("toNumTheme");
        extras.remove("toNumTheme");

        zero = (Button) findViewById(R.id.button6);
        one = (Button) findViewById(R.id.button5);
        two = (Button) findViewById(R.id.button16);
        three = (Button) findViewById(R.id.button4);
        four = (Button) findViewById(R.id.button3);
        five = (Button) findViewById(R.id.button17);
        six = (Button) findViewById(R.id.button7);
        seven = (Button) findViewById(R.id.button9);
        eight = (Button) findViewById(R.id.button18);
        nine = (Button) findViewById(R.id.button19);
        cancel = (Button) findViewById(R.id.button8);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNumPunc();
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "0";
                subdisplay.setText(str);
                openMain();
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "1";
                subdisplay.setText(str);
                openMain();
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "2";
                subdisplay.setText(str);
                openMain();
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "3";
                subdisplay.setText(str);
                openMain();
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "4";
                subdisplay.setText(str);
                openMain();
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "5";
                subdisplay.setText(str);
                openMain();
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "6";
                subdisplay.setText(str);
                openMain();
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "7";
                subdisplay.setText(str);
                openMain();
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "8";
                subdisplay.setText(str);
                openMain();
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "9";
                subdisplay.setText(str);
                openMain();
            }
        });

        setNewTheme();

        buttonScan = (Button) findViewById(R.id.button13);
        buttonScan.setText(Integer.toString(subTheme));
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(22*subSpeed, subSpeed) {
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

                    if(temp > 10) {
                        temp = temp - 11;
                    }

                    switch(temp){
                        case 0:
                            str = subdisplay.getText().toString();
                            str += "0";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 1:
                            str = subdisplay.getText().toString();
                            str += "1";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 2:
                            str = subdisplay.getText().toString();
                            str += "2";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 3:
                            str = subdisplay.getText().toString();
                            str += "3";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 4:
                            str = subdisplay.getText().toString();
                            str += "4";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 5:
                            str = subdisplay.getText().toString();
                            str += "5";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 6:
                            str = subdisplay.getText().toString();
                            str += "6";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 7:
                            str = subdisplay.getText().toString();
                            str += "7";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 8:
                            str = subdisplay.getText().toString();
                            str += "8";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 9:
                            str = subdisplay.getText().toString();
                            str += "9";
                            subdisplay.setText(str);
                            openMain();
                            break;
                        case 10:
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
        intent.putExtra("toHomeTheme",subTheme);
        startActivity(intent);
    }

    public void openNumPunc() {
        Intent intent = new Intent(this, NumPunc.class);
        intent.putExtra("display",subdisplay.getText());
        intent.putExtra("toSubSpeed",subSpeed);
        intent.putExtra("toSubTheme",subTheme);
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

        zero.setTextColor(textColor);
        zero.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        one.setTextColor(textColor);
        one.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        two.setTextColor(textColor);
        two.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        three.setTextColor(textColor);
        three.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        four.setTextColor(textColor);
        four.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        five.setTextColor(textColor);
        five.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        six.setTextColor(textColor);
        six.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        seven.setTextColor(textColor);
        seven.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        eight.setTextColor(textColor);
        eight.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        nine.setTextColor(textColor);
        nine.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        cancel.setTextColor(textColor);
        cancel.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
    }
}
