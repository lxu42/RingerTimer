package com.projects.lauraxu.ringertimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

/**
 * Created by lauraxu on 3/8/16.
 */
public class RingerTimerReceiver extends BroadcastReceiver {
    private static final String TAG = "RingerTimerReceiver";
    public static final String RINGER_MODE_INTENT_EXTRA = "ringer_mode_intent_extra";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "on receive");
        if (intent != null) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(intent.getIntExtra(RINGER_MODE_INTENT_EXTRA, AudioManager.RINGER_MODE_SILENT));
        }
    }
}
