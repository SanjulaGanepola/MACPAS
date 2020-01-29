package com.example.macpas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button buttonABCD;
    private Button buttonEFGH;
    private Button buttonIJKLMN;
    private Button buttonOPQRST;
    private Button buttonUVWXYZ;
    private Button backspace;
    private Button space;
    private Button clear;
    private Button speak;
    private TextToSpeech mTTS;
    private TextView display;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonABCD = (Button) findViewById(R.id.button24);
        buttonABCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openABCD();
            }
        });

        buttonEFGH = (Button) findViewById(R.id.button25);
        buttonEFGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEFGH();
            }
        });

        buttonIJKLMN = (Button) findViewById(R.id.button26);
        buttonIJKLMN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIJKLMN();
            }
        });

        buttonOPQRST = (Button) findViewById(R.id.button27);
        buttonOPQRST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOPQRST();
            }
        });

        buttonUVWXYZ = (Button) findViewById(R.id.button28);
        buttonUVWXYZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUVWXYZ();
            }
        });

        backspace = (Button) findViewById(R.id.button22);
        space = (Button) findViewById(R.id.button23);
        display = (TextView) findViewById(R.id.textView4);

        Bundle extras = getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("subdisplay");
        }
        display.setText(value);

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = display.getText().toString();
                if (str.length() >= 1) {
                    str += " ";
                    display.setText(str);
                }
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = display.getText().toString();
                if (str.length() >= 1) {
                    str = str.substring(0, str.length() - 1);
                    display.setText(str);
                }
            }
        });

        clear = (Button) findViewById(R.id.button20);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = display.getText().toString();
                if (str.length() >= 1) {
                    str = "";
                    display.setText(str);
                }
            }
        });

        speak = findViewById(R.id.button21);
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

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = display.getText().toString();
                mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
