package com.example.macpas;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.macpas.R.style.DefaultColor;
import static com.example.macpas.R.style.WhiteOnBlackTheme;
import static com.example.macpas.R.style.BlueOnYellowTheme;
import static com.example.macpas.R.style.YellowOnBlueTheme;

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
    private int[] arrButton = {R.id.button24,R.id.button25,R.id.button26,R.id.button27,R.id.button28,R.id.button23,R.id.button22,R.id.button29,R.id.button21,R.id.button20};
    private int current = 0;
    private boolean scanning = false;
    private CountDownTimer timer;
    private View root;
    private int homeSpeed;
    private int homeTheme;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle extras = getActivity().getIntent().getExtras();

        homeTheme = ((MainActivity) getActivity()).currentTheme;

        homeSpeed = ((MainActivity) getActivity()).speed;
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
                }
                openSpeak();
            }
        });
        numPunc = (ImageButton) root.findViewById(R.id.button21);
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
        abbrev = (ImageButton) root.findViewById(R.id.button29);
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

        setNewTheme();

        buttonScan = (Button) root.findViewById(R.id.button12);
        buttonScan.setText(Integer.toString(homeTheme));
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(scanning == false) {
                    //buttonScan.setText("SELECT");
                    scanning = true;
                    timer = new CountDownTimer(10*homeSpeed, homeSpeed) {
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
                            openSpace();
                            break;
                        case 6:
                            openBackspace();
                            break;
                        case 7:
                            abbreviation((String) display.getText());
                            break;
                        case 8:
                            openNumPunc();
                            break;
                        case 9:
                            openClear();
                            break;
                    }

                    scanning = false;
                }
            }
        });

        String value = "";
        if (extras != null) {
            value = extras.getString("display");
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
        intent.putExtra("display",display.getText());
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openEFGH() {
        Intent intent = new Intent(this.getActivity(), EFGH.class);
        intent.putExtra("display",display.getText());
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openIJKLMN() {
        Intent intent = new Intent(this.getActivity(), IJKLMN.class);
        intent.putExtra("display",display.getText());
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openOPQRST() {
        Intent intent = new Intent(this.getActivity(), OPQRST.class);
        intent.putExtra("display",display.getText());
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }

    public void openUVWXYZ() {
        Intent intent = new Intent(this.getActivity(), UVWXYZ.class);
        intent.putExtra("display",display.getText());
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
        startActivity(intent);
    }
    public void openNumPunc() {
        Intent intent = new Intent(this.getActivity(), NumPunc.class);
        intent.putExtra("display",display.getText());
        intent.putExtra("toSubSpeed",homeSpeed);
        intent.putExtra("toSubTheme", homeTheme);
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
    public void abbreviation(String displayText) {
        if(displayText.equals("TY")) {
            display.setText("Thank you");
        } else if(displayText.equals("YW")) {
            display.setText("You're welcome");
        } else if(displayText.equals("DK")) {
            display.setText("I don't know");
        } else if(displayText.equals("TL")) {
            display.setText("Talk to you later");
        } else if(displayText.equals("LOL")) {
            display.setText("Laughing out loud");
        } else if(displayText.equals("NP")) {
            display.setText("No problem");
        } else if(displayText.equals("HH")) {
            display.setText("Hello. How are?");
        } else if(displayText.equals("P")) {
            display.setText("I have pain. Ask me where");
        } else if(displayText.equals("IW")) {
            display.setText("I have an itch please verbally scan my body to find out where.");
        } else if(displayText.equals("SW")) {
            display.setText("Something is wrong.  Ask me questions");
        } else if(displayText.equals("T")) {
            display.setText("I am tired and want to rest");
        } else if(displayText.equals("S")) {
            display.setText("I want to stop now");
        } else if(displayText.equals("AP")) {
            display.setText("Please add a phrase to my dictionary");
        } else if(displayText.equals("H")) {
            display.setText("I need help");
        } else if(displayText.equals("GB")) {
            display.setText("Good bye! Nice to see you");
        } else if(displayText.equals("M")) {
            display.setText("Medication is not working");
        }
    }

    public void setNewTheme() {
        int textColor = Color.BLACK;
        int buttonColor = Color.parseColor("#D7D8D6");
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