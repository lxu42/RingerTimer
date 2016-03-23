package com.projects.lauraxu.ringertimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by lauraxu on 3/22/16.
 */
public class Util {
    public static void setAlarm(Context context, long rowIndex, int ringerMode, int hour, int minute) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RingerTimerReceiver.class);
        intent.putExtra(RingerTimerReceiver.RINGER_MODE_INTENT_EXTRA, ringerMode);
        intent.putExtra(RingerTimerReceiver.HOUR_INTENT_EXTRA, hour);
        intent.putExtra(RingerTimerReceiver.MINUTE_INTENT_EXTRA, minute);
        intent.putExtra(RingerTimerReceiver.ROW_INDEX_INTENT_EXTRA, rowIndex);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) rowIndex, intent, PendingIntent.FLAG_ONE_SHOT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public static void setNextAlarm(Context context, long rowIndex, int ringerMode, int hour, int minute) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, RingerTimerReceiver.class);
        intent.putExtra(RingerTimerReceiver.RINGER_MODE_INTENT_EXTRA, ringerMode);
        intent.putExtra(RingerTimerReceiver.HOUR_INTENT_EXTRA, hour);
        intent.putExtra(RingerTimerReceiver.MINUTE_INTENT_EXTRA, minute);
        intent.putExtra(RingerTimerReceiver.ROW_INDEX_INTENT_EXTRA, rowIndex);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) rowIndex, intent, PendingIntent.FLAG_ONE_SHOT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.add(Calendar.DATE, 1);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
