package com.projects.lauraxu.ringertimer;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by lauraxu on 3/8/16.
 */
public class CreateRingerTimerFragment extends Fragment {
    private static final String TAG = "CreateRingerTimerFragment";
    private TimePickerDialog mTimePickerDialog;
    private int mRingerMode = 0;
    private int mHour = 0;
    private int mMinute = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.create_ringer_timer_fragment, container, false);

        mTimePickerDialog = new TimePickerDialog(getContext(), mTimePickerListener, 0, 0, true);

        CharSequence[] items = new CharSequence[]{getResources().getString(R.string.mute), getResources().getString(R.string.vibrate), getResources().getString(R.string.sound)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.choose_sound)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mRingerMode = which;
                    }
                });
        final Dialog dialog = builder.create();

        Button chooseTimeButton = (Button) root.findViewById(R.id.choose_time);
        chooseTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show();
            }
        });
        Button chooseSoundButton = (Button) root.findViewById(R.id.choose_sound);
        chooseSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Button addRingerTimerButton = (Button) root.findViewById(R.id.add_ringer_timer);
        addRingerTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });

        return root;
    }

    private void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), RingerTimerReceiver.class);
        intent.putExtra(RingerTimerReceiver.RINGER_MODE_INTENT_EXTRA, mRingerMode);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);

        Log.d(TAG, "ringerMode: " + mRingerMode + " hour: " + mHour + " min: " + mMinute);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }


    private TimePickerDialog.OnTimeSetListener mTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
        }
    };
}
