package com.example.macpas;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

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

public class SettingsFragment extends Fragment {

    private SeekBar seekbar;
    private int settingSpeed;
    private int settingTheme;
    private TextView t;
    private TextView n;

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

        settingTheme = ((MainActivity) getActivity()).currentTheme;
        n = root.findViewById(R.id.textView5);
        n.setText(Integer.toString(settingTheme));
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
                n.setText(Integer.toString(((MainActivity) getActivity()).currentTheme));
                //getActivity().setTheme(DefaultColor);
            }
        });

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).currentTheme = 2;
                n.setText(Integer.toString(((MainActivity) getActivity()).currentTheme));
                //getActivity().setTheme(WhiteOnBlackTheme);
            }
        });

        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).currentTheme = 3;
                n.setText(Integer.toString(((MainActivity) getActivity()).currentTheme));
                //getActivity().setTheme(BlueOnYellowTheme);
            }
        });

        yb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).currentTheme = 4;
                n.setText(Integer.toString(((MainActivity) getActivity()).currentTheme));
                //getActivity().setTheme(YellowOnBlueTheme);
            }
        });

        settingSpeed = ((MainActivity) getActivity()).speed;
        t = root.findViewById(R.id.textView3);
        t.setText(Integer.toString(settingSpeed));

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
                t.setText(Integer.toString(((MainActivity) getActivity()).speed));
            }
        });
        return root;
    }
}