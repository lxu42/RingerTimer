package com.projects.lauraxu.ringertimer;


import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;

/**
 * Created by lauraxu on 3/9/16.
 */
public class RingerTimerModel extends AbstractSqlModel{

    public void setRowIndex(long rowIndex) {
        set(RingerTimer.Timer._ID, (Long) rowIndex);
    }

    public long getRowIndex() {
        return (Long) get(RingerTimer.Timer._ID);
    }

    public void setHour(int hour) {
        set(RingerTimer.Timer.HOUR, (Integer) hour);
    }

    public int getHour() {
        return (int) get(RingerTimer.Timer.HOUR);
    }

    public void setMinute(int minute) {
        set(RingerTimer.Timer.MINUTE, (Integer) minute);
    }

    public int getMinute() {
        return (int) get(RingerTimer.Timer.MINUTE);
    }

    public void setRingerMode(int ringerMode) {
        set(RingerTimer.Timer.RINGER_MODE, (Integer) ringerMode);
    }

    public int getRingerMode() {
        return (int) get(RingerTimer.Timer.RINGER_MODE);
    }

    public String getRingerModeName(Context context) {
        switch(getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                return context.getResources().getString(R.string.mute);
            case AudioManager.RINGER_MODE_VIBRATE:
                return context.getResources().getString(R.string.vibrate);
            case AudioManager.RINGER_MODE_NORMAL:
                return context.getResources().getString(R.string.sound);
            default:
                return "";
        }
    }

    public static String getRingerModeName(Context context, int ringerMode) {
        switch(ringerMode) {
            case AudioManager.RINGER_MODE_SILENT:
                return context.getResources().getString(R.string.mute);
            case AudioManager.RINGER_MODE_VIBRATE:
                return context.getResources().getString(R.string.vibrate);
            case AudioManager.RINGER_MODE_NORMAL:
                return context.getResources().getString(R.string.sound);
            default:
                return "";
        }
    }

    public static RingerTimerModel fromCursor(Cursor cursor) {
        return (RingerTimerModel) fromCursor(cursor, new RingerTimerModel());
    }



}
