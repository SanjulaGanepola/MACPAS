package com.example.macpas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

//MACPAS v1.0.1
public class MainActivity extends AppCompatActivity {
    private Button buttonABCD;
    private Button buttonEFGH;
    private Button buttonIJKLMN;
    private Button buttonOPQRST;
    private Button buttonUVWXYZ;
    private ImageButton backspace;
    private ImageButton space;
    private ImageButton clear;
    private ImageButton speak;
    private TextToSpeech mTTS;
    private TextView display;
    private AppBarConfiguration mAppBarConfiguration;

    private Button buttonScan;
    private int[] arrButton = {R.id.button24,R.id.button25,R.id.button26,R.id.button27,R.id.button28,R.id.button20,R.id.button21,R.id.button22,R.id.button23};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonABCD = (Button) findViewById(R.id.button24);
        buttonABCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openABCD();
            }
        });

        buttonEFGH = (Button) findViewById(R.id.button25);
        buttonEFGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openEFGH();
            }
        });

        buttonIJKLMN = (Button) findViewById(R.id.button26);
        buttonIJKLMN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openIJKLMN();
            }
        });

        buttonOPQRST = (Button) findViewById(R.id.button27);
        buttonOPQRST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openOPQRST();
            }
        });

        buttonUVWXYZ = (Button) findViewById(R.id.button28);
        buttonUVWXYZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openUVWXYZ();
            }
        });

        clear = (ImageButton) findViewById(R.id.button20);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    resetColour(current);
                    current = 0;
                }
                openClear();
            }
        });
        speak = (ImageButton) findViewById(R.id.button21);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    resetColour(current);
                    current = 0;
                }
                openSpeak();
            }
        });
        backspace = (ImageButton) findViewById(R.id.button22);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    resetColour(current);
                    current = 0;
                }
                openBackspace();
            }
        });
        space = (ImageButton) findViewById(R.id.button23);
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    resetColour(current);
                    current = 0;
                }
                openSpace();
            }
        });

        display = (TextView) findViewById(R.id.textView4);

        buttonScan = (Button) findViewById(R.id.button12);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(18000, 2000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            ImageButton prevButton = (ImageButton) findViewById(arrButton[current - 1]);
                            prevButton.setBackground(buttonScan.getBackground());
                            current = 0;
                            //buttonScan.setText("SCAN");
                            scanning = false;
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                            if (current < 5) {
                                Button currButton = (Button) findViewById(arrButton[current]);
                                currButton.setBackgroundColor(Color.BLUE);
                                mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                                if (current > 0) {
                                    Button prevButton = (Button) findViewById(arrButton[current - 1]);
                                    prevButton.setBackground(buttonScan.getBackground());
                                }
                            } else {
                                ImageButton currButton = (ImageButton) findViewById(arrButton[current]);
                                currButton.setBackgroundColor(Color.BLUE);
                                mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                                if (current == 5) {
                                    Button prevButton = (Button) findViewById(arrButton[current - 1]);
                                    prevButton.setBackground(buttonScan.getBackground());
                                } else {
                                    ImageButton prevButton = (ImageButton) findViewById(arrButton[current - 1]);
                                    prevButton.setBackground(buttonScan.getBackground());
                                }
                            }
                            current++;
                        }
                    }.start();
                }
                else{
                    timer.cancel();
                    //buttonScan.setText("SCAN");
                    int temp = current-1;
                    resetColour(current);
                    current = 0;
                    switch(temp){
                        case 0:
                            openABCD();
                            break;
                        case 1:
                            openEFGH();
                            break;
                        case 2:
                            openIJKLMN();
                            break;
                        case 3:
                            openOPQRST();
                            break;
                        case 4:
                            openUVWXYZ();
                            break;
                        case 5:
                            openClear();
                            break;
                        case 6:
                            openSpeak();
                            break;
                        case 7:
                            openBackspace();
                            break;
                        case 8:
                            openSpace();
                            break;
                    }

                    scanning = false;
                }
            }
        });



        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("subdisplay");
        }
        display.setText(value);




        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        speak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
    }

    public void openABCD() {
        Intent intent = new Intent(this, ABCD.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openEFGH() {
        Intent intent = new Intent(this, EFGH.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openIJKLMN() {
        Intent intent = new Intent(this, IJKLMN.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openOPQRST() {
        Intent intent = new Intent(this, OPQRST.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openUVWXYZ() {
        Intent intent = new Intent(this, UVWXYZ.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openClear() {
        String str = display.getText().toString();
        if (str.length() >= 1) {
            str = "";
            display.setText(str);
        }
    }

    public void openSpeak() {
        String text = display.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void openBackspace() {
        String str = display.getText().toString();
        if (str.length() >= 1) {
            str = str.substring(0, str.length() - 1);
            display.setText(str);
        }
    }
    public void openSpace() {
        String str = display.getText().toString();
        if (str.length() >= 1) {
            str += " ";
            display.setText(str);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void resetColour(int current){
        if(current < 6){
            Button prevButton = (Button) findViewById(arrButton[current - 1]);
            prevButton.setBackground(buttonScan.getBackground());
        }
        else{
            ImageButton prevButton = (ImageButton) findViewById(arrButton[current - 1]);
            prevButton.setBackground(buttonScan.getBackground());
        }
    }
}
