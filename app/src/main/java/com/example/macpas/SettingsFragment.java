package com.example.macpas;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.example.macpas.R.style.AppTheme;
import static com.example.macpas.R.style.DefaultColor;
import static com.example.macpas.R.style.WhiteOnBlackTheme;
import static com.example.macpas.R.style.BlueOnYellowTheme;
import static com.example.macpas.R.style.YellowOnBlueTheme;

import com.example.macpas.R;
import com.google.android.material.textfield.TextInputEditText;

public class SettingsFragment extends Fragment {

    private SeekBar seekbar;
    private int settingSpeed;
    private int settingTheme;
    private TextView t;
    private TextView n;

    private TextInputEditText acronym;
    private TextInputEditText phrase;
    private Button save;

    private View root;
    private int checkedButton = 0;

    private RadioButton defaultColor;
    private RadioButton wb;
    private RadioButton by;
    private RadioButton yb;
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_settings, container, false);

        defaultColor = (RadioButton) root.findViewById(R.id.radioButton4);
        wb = (RadioButton) root.findViewById(R.id.radioButton3);
        by = (RadioButton) root.findViewById(R.id.radioButton2);
        yb = (RadioButton) root.findViewById(R.id.radioButton);

        acronym = (TextInputEditText) root.findViewById(R.id.acronym_id);
        phrase = (TextInputEditText) root.findViewById(R.id.phrase_id);
        save = (Button) root.findViewById(R.id.button30);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(acro.toUpperCase(), phra.toUpperCase());
                    editor.apply();

                    Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                    acronym.setText("");
                    phrase.setText("");
                    acronym.clearFocus();
                    phrase.clearFocus();
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
                ((MainActivity) getActivity()).currentTheme = 1;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).currentTheme = 2;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).currentTheme = 3;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("theme", ((MainActivity) getActivity()).currentTheme);
                editor.apply();
            }
        });

        yb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).currentTheme = 4;

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
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
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPrefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("speed", ((MainActivity) getActivity()).speed);
                editor.apply();

                //t.setText(Integer.toString(((MainActivity) getActivity()).speed));
            }
        });
        return root;
    }
}