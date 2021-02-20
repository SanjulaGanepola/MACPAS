package com.example.macpas;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TutorialPage1 extends Fragment {

    private ViewGroup root;
    private Button b;
    private TextToSpeech mTTSTutorial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.tutorial_slide1, container, false);

        mTTSTutorial = ((MainActivity) getActivity()).mTTS;

        b = (Button) root.findViewById(R.id.button31);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mTTSTutorial.speak("Home Screen Scanning. Tap the screen to start scanning. Observe scanning and listen to the audio cues. Tap the screen again to select the highlighted button.", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        return root;
    }
}