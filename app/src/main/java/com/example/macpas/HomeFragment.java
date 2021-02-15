package com.example.macpas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

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
    private ImageButton numPunc;
    private ImageButton abbrev;
    private TextToSpeech mTTS;
    private TextView display;
    private Button buttonScan;
    private int[] arrButton = {R.id.button24,R.id.button25,R.id.button26,R.id.button27,R.id.button28,
                               R.id.button23,R.id.button22,R.id.button29,R.id.button21,R.id.button20,
                               R.id.button24,R.id.button25,R.id.button26,R.id.button27,R.id.button28,
                               R.id.button23,R.id.button22,R.id.button29,R.id.button21,R.id.button20};
    public int current = 0;
    public boolean scanning = false;
    public CountDownTimer timer;
    private View root;
    private int homeSpeed;
    private int homeTheme;
    private int buttonColor;
    private String homeDisplay;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
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

        numPunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    current = 0;
                }
                openNumPunc();
            }
        });

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

        abbrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    resetColour(current);
                    current = 0;
                }
                abbreviation((String) display.getText());
            }
        });
        */

        root = inflater.inflate(R.layout.fragment_home, container, false);
        //Bundle extras = getActivity().getIntent().getExtras();

        homeTheme = ((MainActivity) getActivity()).currentTheme;

        homeSpeed = ((MainActivity) getActivity()).speed;
        buttonABCD = (Button) root.findViewById(R.id.button24);
        buttonEFGH = (Button) root.findViewById(R.id.button25);
        buttonIJKLMN = (Button) root.findViewById(R.id.button26);
        buttonOPQRST = (Button) root.findViewById(R.id.button27);
        buttonUVWXYZ = (Button) root.findViewById(R.id.button28);
        clear = (ImageButton) root.findViewById(R.id.button20);
        numPunc = (ImageButton) root.findViewById(R.id.button21);
        backspace = (ImageButton) root.findViewById(R.id.button22);
        space = (ImageButton) root.findViewById(R.id.button23);
        abbrev = (ImageButton) root.findViewById(R.id.button29);
        display = (TextView) root.findViewById(R.id.textView4);
        display.setBackgroundColor(Color.WHITE);
        display.setTextColor(Color.BLACK);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scanning) {
                    timer.cancel();
                    scanning = false;
                    resetColour(current);
                    current = 0;
                    ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }

                openSpeak();
            }
        });

        setNewTheme();

        buttonScan = (Button) root.findViewById(R.id.button12);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(20*homeSpeed, homeSpeed) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
                            prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
                            current = 0;
                            //buttonScan.setText("SCAN");
                            scanning = false;
                            ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                            if (current < 5 || (9 < current && current < 15)) {
                                Button currButton = (Button) root.findViewById(arrButton[current]);
                                currButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3EB0A6")));
                                mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                                if (current != 0 && current != 10) {
                                    Button prevButton = (Button) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
                                } else if(current == 10) {
                                    ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
                                }
                            } else {
                                ImageButton currButton = (ImageButton) root.findViewById(arrButton[current]);
                                currButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3EB0A6")));
                                mTTS.speak((String)currButton.getContentDescription(), TextToSpeech.QUEUE_FLUSH, null);
                                if (current == 5 || current == 15) {
                                    Button prevButton = (Button) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
                                } else {
                                    ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
                                    prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
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

                    if(temp > 9) {
                        temp = temp - 10;
                    }
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
                            openSpace();
                            break;
                        case 6:
                            openBackspace();
                            break;
                        case 7:
                            abbreviation((String) homeDisplay);
                            break;
                        case 8:
                            openNumPunc();
                            break;
                        case 9:
                            openClear();
                            break;
                    }

                    scanning = false;
                    ((MainActivity) getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            }
        });

        homeDisplay = ((MainActivity) getActivity()).currentDisplay.toString();

        display.setText(homeDisplay);

        mTTS = new TextToSpeech(this.getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        display.setEnabled(true);
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
        intent.putExtra("toSubDisplay",homeDisplay);
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openEFGH() {
        Intent intent = new Intent(this.getActivity(), EFGH.class);
        intent.putExtra("toSubDisplay",homeDisplay);
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openIJKLMN() {
        Intent intent = new Intent(this.getActivity(), IJKLMN.class);
        intent.putExtra("toSubDisplay",homeDisplay);
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openOPQRST() {
        Intent intent = new Intent(this.getActivity(), OPQRST.class);
        intent.putExtra("toSubDisplay",homeDisplay);
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openUVWXYZ() {
        Intent intent = new Intent(this.getActivity(), UVWXYZ.class);
        intent.putExtra("toSubDisplay",homeDisplay);
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }
    public void openNumPunc() {
        Intent intent = new Intent(this.getActivity(), NumPunc.class);
        intent.putExtra("toSubDisplay",homeDisplay);
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }
    public void openClear() {
        buttonScan.setEnabled(true);
        homeDisplay = homeDisplay.substring(0, 0);
        ((MainActivity) getActivity()).setCurrentDisplay(homeDisplay);
        display.setText(homeDisplay);
    }
    public void openSpeak() {
        buttonScan.setEnabled(true);
        String text = homeDisplay;
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void openBackspace() {
        buttonScan.setEnabled(true);
        if (homeDisplay.length() >= 1) {
            homeDisplay = homeDisplay.substring(0, homeDisplay.length() - 1);
            ((MainActivity) getActivity()).setCurrentDisplay(homeDisplay);
            display.setText(homeDisplay);
        }
    }
    public void openSpace() {
        buttonScan.setEnabled(true);
        if (homeDisplay.length() >= 1) {
            homeDisplay += " ";
            ((MainActivity) getActivity()).setCurrentDisplay(homeDisplay);
            display.setText(homeDisplay);
        }
    }
    public void resetColour(int current){
        if(current < 6 || (10 < current && current < 16)){
            Button prevButton = (Button) root.findViewById(arrButton[current - 1]);
            prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        }
        else{
            ImageButton prevButton = (ImageButton) root.findViewById(arrButton[current - 1]);
            prevButton.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        }
    }
    public void abbreviation(String displayText) {
        buttonScan.setEnabled(true);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
        String phrase = sharedPreferences.getString(displayText, "");

        if(phrase != ""){
            if(displayText.equals("IW")){
                mTTS.speak("I HAVE AN ITCH PLEASE VERBALLY SCAN MY BODY TO FIND OUT WHERE.", TextToSpeech.QUEUE_FLUSH, null);
            } else {
                homeDisplay = phrase;
                ((MainActivity) getActivity()).setCurrentDisplay(homeDisplay);
                display.setText(homeDisplay);
                openSpeak();
            }
        }
    }

    public void setNewTheme() {
        int textColor = Color.BLACK;
        buttonColor = Color.parseColor("#D7D8D6");
        int backgroundColor = Color.WHITE;

        switch (homeTheme) {
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

        root.setBackgroundColor(backgroundColor);

        buttonABCD.setTextColor(textColor);
        buttonABCD.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        buttonEFGH.setTextColor(textColor);
        buttonEFGH.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        buttonIJKLMN.setTextColor(textColor);
        buttonIJKLMN.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        buttonOPQRST.setTextColor(textColor);
        buttonOPQRST.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        buttonUVWXYZ.setTextColor(textColor);
        buttonUVWXYZ.setBackgroundTintList(ColorStateList.valueOf(buttonColor));

        clear.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        space.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        abbrev.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        numPunc.setBackgroundTintList(ColorStateList.valueOf(buttonColor));
        backspace.setBackgroundTintList(ColorStateList.valueOf(buttonColor));

        clear.setImageTintList(ColorStateList.valueOf(textColor));
        space.setImageTintList(ColorStateList.valueOf(textColor));
        abbrev.setImageTintList(ColorStateList.valueOf(textColor));
        numPunc.setImageTintList(ColorStateList.valueOf(textColor));
        backspace.setImageTintList(ColorStateList.valueOf(textColor));
    }
}

/*
if(displayText.equals("TY")) {
            homeDisplay = "THANK YOU";
        } else if(displayText.equals("YW")) {
            homeDisplay = "YOU'RE WELCOME";
        } else if(displayText.equals("DK")) {
            homeDisplay = "I DON'T KNOW";
        } else if(displayText.equals("TL")) {
            homeDisplay = "TALK TO YOU LATER";
        } else if(displayText.equals("LOL")) {
            homeDisplay = "LAUGHING OUT LOUD";
        } else if(displayText.equals("NP")) {
            homeDisplay = "NO PROBLEM";
        } else if(displayText.equals("HH")) {
            homeDisplay = "HELLO. HOW ARE YOU?";
        } else if(displayText.equals("P")) {
            homeDisplay = "I HAVE PAIN. ASK ME WHERE";
        } else if(displayText.equals("IW")) {
            String text = "I HAVE AN ITCH PLEASE VERBALLY SCAN MY BODY TO FIND OUT WHERE.";
            mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            return;
        } else if(displayText.equals("SW")) {
            homeDisplay = "SOMETHING IS WRONG. ASK ME QUESTIONS";
        } else if(displayText.equals("T")) {
            homeDisplay = "I AM TIRED AND WANT TO REST";
        } else if(displayText.equals("S")) {
            homeDisplay = "I WANT TO STOP NOW";
        } else if(displayText.equals("AP")) {
            homeDisplay = "PLEASE ADD A PHRASE TO MY DICTIONARY";
        } else if(displayText.equals("H")) {
            homeDisplay = "I NEED HELP";
        } else if(displayText.equals("GB")) {
            homeDisplay = "GOODBYE! NICE TO SEE YOU";
        } else if(displayText.equals("M")) {
            homeDisplay = "MEDICATION IS NOT WORKING";
        }
 */