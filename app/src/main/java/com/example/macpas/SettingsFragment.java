package com.example.macpas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Map;
import java.util.TreeMap;

public class SettingsFragment extends Fragment {

    private SeekBar seekbar;
    private int settingSpeed;
    private int settingTheme;
    private TextView t;
    private TextView n;

    private TextInputEditText acronym;
    private TextInputEditText phrase;
    private Button add;
    private Button remove;
    private TextInputLayout phraseLayout;
    private LinearLayout current;

    private View root;

    private RadioButton defaultColor;
    private RadioButton wb;
    private RadioButton by;
    private RadioButton yb;

    private ImageButton ham;

    private DrawerLayout d;

    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_settings, container, false);

        ham = (ImageButton) root.findViewById(R.id.ham_id);
        d = ((MainActivity) getActivity()).drawer;

        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.openDrawer(Gravity.LEFT);
            }
        });

        defaultColor = (RadioButton) root.findViewById(R.id.radioButton4);
        wb = (RadioButton) root.findViewById(R.id.radioButton3);
        by = (RadioButton) root.findViewById(R.id.radioButton2);
        yb = (RadioButton) root.findViewById(R.id.radioButton);

        current = (LinearLayout) root.findViewById(R.id.current_id);

        updateCurrentAbbreviations();

        acronym = (TextInputEditText) root.findViewById(R.id.acronym_id);
        phrase = (TextInputEditText) root.findViewById(R.id.phrase_id);
        add = (Button) root.findViewById(R.id.add_id);
        remove = (Button) root.findViewById(R.id.remove_id);
        phraseLayout = (TextInputLayout) root.findViewById(R.id.phraseLayout_id);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocusHideKeyboard();
                String acro = acronym.getText().toString();
                String phra = phrase.getText().toString();

                if((acro.equals(null) || acro.equals("")) && (phra.equals(null) || phra.equals(""))) {
                    Toast.makeText(getActivity(),"Enter Acronym and Phrase",Toast.LENGTH_SHORT).show();
                }else if(acro.equals(null) || acro.equals("")) {
                    Toast.makeText(getActivity(),"Enter Acronym",Toast.LENGTH_SHORT).show();
                } else if (phra.equals(null) || phra.equals("")) {
                    Toast.makeText(getActivity(),"Enter Phrase",Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                    if(sharedPreferences.contains(acro.toUpperCase())) {
                        //Override Abbreviation
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setCancelable(false);
                        builder.setTitle("Override Abbreviation: " + acro.toUpperCase());
                        builder.setMessage("Old Phrase: " + sharedPreferences.getString(acro.toUpperCase(), "") + "\nNew Phrase: " + phra.toUpperCase());

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String acro = acronym.getText().toString();
                                String phra = phrase.getText().toString();

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(acro.toUpperCase(), phra.toUpperCase());
                                editor.apply();

                                Toast.makeText(getActivity(),"Updated: " + acro.toUpperCase(),Toast.LENGTH_SHORT).show();
                                acronym.setText("");
                                phrase.setText("");
                                updateCurrentAbbreviations();

                            }
                        });
                        builder.show();
                    } else {
                        //New Abbreviation
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(acro.toUpperCase(), phra.toUpperCase());
                        editor.apply();

                        Toast.makeText(getActivity(),"Added: " + acro,Toast.LENGTH_SHORT).show();
                        acronym.setText("");
                        phrase.setText("");
                        updateCurrentAbbreviations();
                    }
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocusHideKeyboard();
                String acro = acronym.getText().toString();

                if(acro.equals(null) || acro.equals("")) {
                    Toast.makeText(getActivity(),"Enter Acronym",Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if(sharedPreferences.contains(acro.toUpperCase())){
                        Toast.makeText(getActivity(),"Removed: " + acro,Toast.LENGTH_SHORT).show();
                        editor.remove(acro.toUpperCase());
                        editor.apply();
                        acronym.setText("");
                        phrase.setText("");
                        updateCurrentAbbreviations();
                    } else {
                        Toast.makeText(getActivity(),acro + " Not Found",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        
        settingTheme = ((MainActivity) getActivity()).currentTheme;
        n = root.findViewById(R.id.textView5);
        switch(settingTheme) {
            case 1:
                defaultColor.setChecked(true);
                break;
            case 2:
                wb.setChecked(true);
                break;
            case 3:
                by.setChecked(true);
                break;
            case 4:
                yb.setChecked(true);
                break;
        }

        defaultColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocusHideKeyboard();

                ((MainActivity) getActivity()).currentTheme = 1;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocusHideKeyboard();

                ((MainActivity) getActivity()).currentTheme = 2;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocusHideKeyboard();

                ((MainActivity) getActivity()).currentTheme = 3;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        yb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocusHideKeyboard();

                ((MainActivity) getActivity()).currentTheme = 4;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        settingSpeed = ((MainActivity) getActivity()).speed;
        t = root.findViewById(R.id.textView3);

        seekbar = (SeekBar) root.findViewById(R.id.seekBar3);
        //add code to update slider
        switch(settingSpeed) {
            case 5000:
                seekbar.setProgress(0);
                break;
            case 4000:
                seekbar.setProgress(1);
                break;
            case 3000:
                seekbar.setProgress(2);
                break;
            case 2000:
                seekbar.setProgress(3);
                break;
            case 1000:
                seekbar.setProgress(4);
                break;
        }
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                clearFocusHideKeyboard();

                // TODO Auto-generated method stub
                switch(progress) {
                    case 0:
                        settingSpeed = 5000;
                        break;
                    case 1:
                        settingSpeed = 4000;
                        break;
                    case 2:
                        settingSpeed = 3000;
                        break;
                    case 3:
                        settingSpeed = 2000;
                        break;
                    case 4:
                        settingSpeed = 1000;
                        break;
                }

                ((MainActivity) getActivity()).speed = settingSpeed;
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("speed", ((MainActivity) getActivity()).speed);
                editor.apply();

                //t.setText(Integer.toString(((MainActivity) getActivity()).speed));
            }
        });
        return root;
    }

    public void updateCurrentAbbreviations() {
        current.removeAllViews();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
        TreeMap<String, ?> keys = new TreeMap<String, Object>(sharedPreferences.getAll());

        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            String abbreviations = entry.getKey();
            String phrases = entry.getValue().toString();


            LinearLayout row = new LinearLayout(getActivity());
            row.setOrientation(LinearLayout.HORIZONTAL);

            float scale = getResources().getDisplayMetrics().density;
            int dpAsPixels = (int) (10*scale + 0.5f);

            row.setPadding(dpAsPixels,0, dpAsPixels,0);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

            TextView a = new TextView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
            a.setText(abbreviations);
            a.setLayoutParams(lp);
            TextView p = new TextView(getActivity());
            lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2.5f);
            p.setText(phrases);
            p.setLayoutParams(lp);

            row.addView(a);
            row.addView(p);

            current.addView(row);
        }
    }

    public void clearFocusHideKeyboard() {
        acronym.clearFocus();
        phrase.clearFocus();
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}