package com.projects.lauraxu.ringertimer;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by lauraxu on 3/8/16.
 */
public class TimePickerDialogFragment extends DialogFragment {
    public static final String TAG = "timerPickerDialogFragmentTag";
    private static final String TIME_PICKER_HOUR_KEY = "timePickerHourKey";
    private static final String TIME_PICKER_MINUTE_KEY = "timePickerMinuteKey";

    private TimePickerDialog mTimePickerDialog;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private int mHour;
    private int mMinute;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mHour = savedInstanceState.getInt(TIME_PICKER_HOUR_KEY);
            mMinute = savedInstanceState.getInt(TIME_PICKER_MINUTE_KEY);
        }

        mTimePickerDialog = new TimePickerDialog(getActivity(), mTimeSetListener, mHour, mMinute, true);
        return mTimePickerDialog;
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        mTimeSetListener = listener;
    }

    public void updateTime(int hour, int minute) {
        mTimePickerDialog.updateTime(hour, minute);
    }
}
