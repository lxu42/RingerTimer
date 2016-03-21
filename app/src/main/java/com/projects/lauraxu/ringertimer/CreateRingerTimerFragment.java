package com.projects.lauraxu.ringertimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by lauraxu on 3/8/16.
 */
public class CreateRingerTimerFragment extends Fragment {
    private static final String TAG = "CreateRingerTimerFragment";

    private static final String RINGER_MODE_KEY = "soundPickerKey";

    private TimePickerDialogFragment mTimePickerDialogFragment;
    private RingerModeDialogFragment mRingerModeDialogFragment;
    private TextView mTimeTextView;
    private TextView mRingerModeTextView;

    private int mRingerMode = 0;
    private int mHour = 0;
    private int mMinute = 0;

    private IAddListener mAddListener;

    // Overrides

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.create_ringer_timer_fragment, container, false);
        if (savedInstanceState != null) {
            mRingerMode = savedInstanceState.getInt(RINGER_MODE_KEY);
        }

        mTimePickerDialogFragment = (TimePickerDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(TimePickerDialogFragment.TAG);
        if (mTimePickerDialogFragment != null) {
            mTimePickerDialogFragment.setListener(mTimeSetListener);
        }

        mRingerModeDialogFragment = (RingerModeDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(RingerModeDialogFragment.TAG);
        if (mRingerModeDialogFragment != null) {
            mRingerModeDialogFragment.setListener(mRingerModeListener);
        }

        mTimeTextView = (TextView) root.findViewById(R.id.time_text);
        mTimeTextView.setText(getResources().getString(R.string.time_text, mHour, mMinute));

        Button chooseTimeButton = (Button) root.findViewById(R.id.choose_time);
        chooseTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialogFragment = new TimePickerDialogFragment();
                mTimePickerDialogFragment.setListener(mTimeSetListener);
                mTimePickerDialogFragment.show(getActivity().getSupportFragmentManager(), TimePickerDialogFragment.TAG);
            }
        });

        mRingerModeTextView = (TextView) root.findViewById(R.id.ringer_mode_text);
        mRingerModeTextView.setText(getResources().getString(R.string.ringer_mode_text, mRingerMode));

        Button chooseSoundButton = (Button) root.findViewById(R.id.choose_sound);
        chooseSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: pass in current time in intent
                mRingerModeDialogFragment = new RingerModeDialogFragment();
                mRingerModeDialogFragment.setListener(mRingerModeListener);
                mRingerModeDialogFragment.show(getActivity().getSupportFragmentManager(), RingerModeDialogFragment.TAG);
            }
        });

        Button addRingerTimerButton = (Button) root.findViewById(R.id.add_ringer_timer);
        addRingerTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddListener != null) {
                    mAddListener.onAdd(mHour, mMinute, mRingerMode);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RINGER_MODE_KEY, mRingerMode);
    }

    // Public
    public void setAddListener(IAddListener listener) {
        mAddListener = listener;
    }

    // Private



    // Private declarations

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            mTimeTextView.setText(getResources().getString(R.string.time_text, mHour, mMinute));
        }
    };

    private DialogInterface.OnClickListener mRingerModeListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mRingerMode = which;
            mRingerModeTextView.setText(getResources().getString(R.string.ringer_mode_text, mRingerMode));
        }
    };

    // Inner classes
    public interface IAddListener {
        void onAdd(int hour, int minute, int ringerMode);
    }
}
