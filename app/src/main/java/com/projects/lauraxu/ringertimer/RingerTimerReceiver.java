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
    public static final String HOUR_INTENT_EXTRA = "hour_intent_extra";
    public static final String MINUTE_INTENT_EXTRA = "minute_intent_extra";
    public static final String ROW_INDEX_INTENT_EXTRA = "row_index_intent_extra";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "on receive ");
        if (intent != null) {
            int ringerMode = intent.getIntExtra(RINGER_MODE_INTENT_EXTRA, AudioManager.RINGER_MODE_SILENT);
            int hour = intent.getIntExtra(HOUR_INTENT_EXTRA, 0);
            int minute = intent.getIntExtra(MINUTE_INTENT_EXTRA, 0);
            long rowIndex = intent.getLongExtra(ROW_INDEX_INTENT_EXTRA, RingerTimer.Timer.INVALID_ROW_INDEX);
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(ringerMode);
            Util.setNextAlarm(context, rowIndex, ringerMode, hour, minute);
        }
    }
}
