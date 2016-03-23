package com.projects.lauraxu.ringertimer;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by lauraxu on 3/8/16.
 */
public class CreateRingerTimerFragment extends Fragment {
    private static final String TAG = "CreateRingerTimerFragment";

    public static final String RINGER_MODE_KEY = "ringerModeKey";
    public static final String RINGER_HOUR_KEY = "ringerHourKey";
    public static final String RINGER_MINUTE_KEY = "ringerMinuteKey";
    public static final String RINGER_ROW_INDEX = "ringerRowIndex";

    private TimePickerDialogFragment mTimePickerDialogFragment;
    private RingerModeDialogFragment mRingerModeDialogFragment;
    private TextView mTimeTextView;
    private TextView mRingerModeTextView;

    private int mRingerMode = 0;
    private int mHour = 0;
    private int mMinute = 0;
    private long mRowIndex = RingerTimer.Timer.INVALID_ROW_INDEX;

    private ISaveListener mSaveListener;

    // Overrides

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.create_ringer_timer_fragment, container, false);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mRingerMode = bundle.getInt(RINGER_MODE_KEY);
            mHour = bundle.getInt(RINGER_HOUR_KEY);
            mMinute = bundle.getInt(RINGER_MINUTE_KEY);
            mRowIndex = bundle.getLong(RINGER_ROW_INDEX);
        }
        if (savedInstanceState != null) {
            mRingerMode = savedInstanceState.getInt(RINGER_MODE_KEY);
            mHour = savedInstanceState.getInt(RINGER_HOUR_KEY);
            mMinute = savedInstanceState.getInt(RINGER_MINUTE_KEY);
            mRowIndex = savedInstanceState.getLong(RINGER_ROW_INDEX);
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

        LinearLayout chooseTimeButton = (LinearLayout) root.findViewById(R.id.choose_time);
        chooseTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialogFragment = new TimePickerDialogFragment();
                mTimePickerDialogFragment.setListener(mTimeSetListener);
                mTimePickerDialogFragment.show(getActivity().getSupportFragmentManager(), TimePickerDialogFragment.TAG);
            }
        });

        mRingerModeTextView = (TextView) root.findViewById(R.id.ringer_mode_text);
        mRingerModeTextView.setText(getResources().getString(R.string.ringer_mode_text, RingerTimerModel.getRingerModeName(getContext(), mRingerMode)));

        LinearLayout chooseSoundButton = (LinearLayout) root.findViewById(R.id.choose_sound);
        chooseSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: pass in current time in intent
                mRingerModeDialogFragment = new RingerModeDialogFragment();
                mRingerModeDialogFragment.setListener(mRingerModeListener);
                mRingerModeDialogFragment.show(getActivity().getSupportFragmentManager(), RingerModeDialogFragment.TAG);
            }
        });

        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RINGER_MODE_KEY, mRingerMode);
        outState.putInt(RINGER_HOUR_KEY, mHour);
        outState.putInt(RINGER_MINUTE_KEY, mMinute);
        outState.putLong(RINGER_ROW_INDEX, mRowIndex);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==  R.id.save_menu_item) {
            if (mSaveListener != null) {
                if (mRowIndex == RingerTimer.Timer.INVALID_ROW_INDEX) {
                    mSaveListener.onAdd(mHour, mMinute, mRingerMode);
                } else {
                    mSaveListener.onEdit(mRowIndex, mHour, mMinute, mRingerMode);
                }
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }
        return true;
    }

    // Public
    public void setAddListener(ISaveListener listener) {
        mSaveListener = listener;
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
    public interface ISaveListener {
        void onAdd(int hour, int minute, int ringerMode);
        void onEdit(long rowIndex, int hour, int minute, int ringerMode);
    }
}
