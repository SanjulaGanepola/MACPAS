package com.example.macpas;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class TutorialPage6 extends Fragment {

    private ViewGroup root;
    private Button b;
    private TextToSpeech mTTSTutorial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.tutorial_slide6, container, false);

        mTTSTutorial = ((MainActivity) getActivity()).mTTS;

        b = (Button) root.findViewById(R.id.button31);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mTTSTutorial.speak("Settings Screen, Speed and Theme. Adjust scanning speed using the slider. Select the desired color theme.", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return root;
    }
}