package com.example.macpas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.macpas.ABCD;
import com.example.macpas.EFGH;
import com.example.macpas.IJKLMN;
import com.example.macpas.OPQRST;
import com.example.macpas.R;
import com.example.macpas.UVWXYZ;

import java.util.Locale;

public class HomeFragment extends Fragment {
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
    private Button buttonScan;
    private int[] arrButton = {R.id.button24,R.id.button25,R.id.button26,R.id.button27,R.id.button28,R.id.button20,R.id.button21,R.id.button22,R.id.button23};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        buttonABCD = (Button) root.findViewById(R.id.button24);
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

        buttonEFGH = (Button) root.findViewById(R.id.button25);
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

        buttonIJKLMN = (Button) root.findViewById(R.id.button26);
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

        buttonOPQRST = (Button) root.findViewById(R.id.button27);
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

        buttonUVWXYZ = (Button) root.findViewById(R.id.button28);
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

        clear = (ImageButton) root.findViewById(R.id.button20);
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
        speak = (ImageButton) root.findViewById(R.id.button21);
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
        backspace = (ImageButton) root.findViewById(R.id.button22);
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
        space = (ImageButton) root.findViewById(R.id.button23);
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

        display = (TextView) root.findViewById(R.id.textView4);

        buttonScan = (Button) root.findViewById(R.id.button12);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(9000, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
                            prevButton.setBackground(buttonScan.getBackground());
                            current = 0;
                            //buttonScan.setText("SCAN");
                            scanning = false;
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                            if (current < 5) {
                                Button currButton = (Button) root.findViewById(arrButton[current]);
                                currButton.setBackgroundColor(Color.BLUE);
                                mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                                if (current > 0) {
                                    Button prevButton = (Button) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackground(buttonScan.getBackground());
                                }
                            } else {
                                ImageButton currButton = (ImageButton) root.findViewById(arrButton[current]);
                                currButton.setBackgroundColor(Color.BLUE);
                                mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                                if (current == 5) {
                                    Button prevButton = (Button) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackground(buttonScan.getBackground());
                                } else {
                                    ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackground(buttonScan.getBackground());
                                }
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



        Bundle extras = getActivity().getIntent().getExtras();
        String value = "";
        if (extras != null) {
            value = extras.getString("subdisplay");
        }
        display.setText(value);




        mTTS = new TextToSpeech(this.getActivity(), new TextToSpeech.OnInitListener() {
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
        return root;
    }
    public void openABCD() {
        Intent intent = new Intent(this.getActivity(), ABCD.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openEFGH() {
        Intent intent = new Intent(this.getActivity(), EFGH.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openIJKLMN() {
        Intent intent = new Intent(this.getActivity(), IJKLMN.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openOPQRST() {
        Intent intent = new Intent(this.getActivity(), OPQRST.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openUVWXYZ() {
        Intent intent = new Intent(this.getActivity(), UVWXYZ.class);
        intent.putExtra("display",display.getText());
        startActivity(intent);
    }

    public void openClear() {
        buttonScan.setEnabled(true);
        String str = display.getText().toString();
        if (str.length() >= 1) {
            str = "";
            display.setText(str);
        }
    }

    public void openSpeak() {
        buttonScan.setEnabled(true);
        String text = display.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void openBackspace() {
        buttonScan.setEnabled(true);
        String str = display.getText().toString();
        if (str.length() >= 1) {
            str = str.substring(0, str.length() - 1);
            display.setText(str);
        }
    }
    public void openSpace() {
        buttonScan.setEnabled(true);
        String str = display.getText().toString();
        if (str.length() >= 1) {
            str += " ";
            display.setText(str);
        }
    }
    public void resetColour(int current){
        if(current < 6){
            Button prevButton = (Button) root.findViewById(arrButton[current - 1]);
            prevButton.setBackground(buttonScan.getBackground());
        }
        else{
            ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
            prevButton.setBackground(buttonScan.getBackground());
        }
    }
}