package com.example.macpas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.macpas.R.style.DefaultColor;
import static com.example.macpas.R.style.WhiteOnBlackTheme;
import static com.example.macpas.R.style.BlueOnYellowTheme;
import static com.example.macpas.R.style.YellowOnBlueTheme;
import java.util.Locale;

public class ABCD extends AppCompatActivity {
    private TextView subdisplay;

    private Button A;
    private Button B;
    private Button C;
    private Button D;
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
        setContentView(R.layout.activity_abcd);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("display");
        }
        subdisplay = (TextView) findViewById(R.id.textView2);
        subdisplay.setText(value);
        subSpeed = extras.getInt("toSubSpeed");
        extras.remove("toSubSpeed");

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
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "A";
                subdisplay.setText(str);

                openMain();
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "B";
                subdisplay.setText(str);
                openMain();
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "C";
                subdisplay.setText(str);
                openMain();
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                String str = subdisplay.getText().toString();
                str += "D";
                subdisplay.setText(str);
                openMain();
            }
        });

        buttonScan = (Button) findViewById(R.id.button10);
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
                            str += "A";
                            subdisplay.setText(str);
                            break;
                        case 1:
                            str = subdisplay.getText().toString();
                            str += "B";
                            subdisplay.setText(str);
                            break;
                        case 2:
                            str = subdisplay.getText().toString();
                            str += "C";
                            subdisplay.setText(str);
                            break;
                        case 3:
                            str = subdisplay.getText().toString();
                            str += "D";
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

    public void resetColour(int current) {
        Button prevButton = (Button) findViewById(arrButton[current - 1]);
        prevButton.setBackground(buttonScan.getBackground());
    }


    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            subTheme = extras.getInt("toSubTheme");
            extras.remove("toSubTheme");


            switch (subTheme) {
                case 1:
                    theme.applyStyle(DefaultColor, true);
                    break;
                case 2:
                    theme.applyStyle(WhiteOnBlackTheme, true);
                    break;
                case 3:
                    theme.applyStyle(BlueOnYellowTheme, true);
                    break;
                case 4:
                    theme.applyStyle(YellowOnBlueTheme, true);
                    break;
            }
        }

        // you could also use a switch if you have many themes that could apply
        return theme;
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