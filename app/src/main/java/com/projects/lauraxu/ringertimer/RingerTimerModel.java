package com.projects.lauraxu.ringertimer;


import android.database.Cursor;

/**
 * Created by lauraxu on 3/9/16.
 */
public class RingerTimerModel extends AbstractSqlModel{

    public void setRowIndex(long rowIndex) {
        set(RingerTimer.Timer._ID, rowIndex);
    }

    public long getRowIndex() {
        return (long) get(RingerTimer.Timer._ID);
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

    public static RingerTimerModel fromCursor(Cursor cursor) {
        return (RingerTimerModel) fromCursor(cursor, new RingerTimerModel());
    }



}
