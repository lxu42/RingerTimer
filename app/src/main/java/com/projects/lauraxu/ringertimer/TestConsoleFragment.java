package com.projects.lauraxu.ringertimer;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by lauraxu on 3/8/16.
 */
public class TestConsoleFragment extends Fragment {
    private static final String TAG = "TestConsoleFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.test_console_fragment, container, false);

        Button muteButton = (Button) root.findViewById(R.id.mute_button);
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        });
        Button vibrateButton = (Button) root.findViewById(R.id.vibrate_button);
        vibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
        });

        Button soundButton = (Button) root.findViewById(R.id.sound_button);
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        });

        return root;
    }

    private void setRingerMode(int mode) {
        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(mode);
    }
}
