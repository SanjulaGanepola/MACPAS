package com.example.macpas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.macpas.R;

public class SettingsFragment extends Fragment {

    private SeekBar seekbar;
    private int settingSpeed;
    private TextView t;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

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