package com.projects.lauraxu.ringertimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class RingerTimerFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int RINGER_TIMER_CURSOR_LOADER_ID = 94114;
    private static final String EDIT_RINGER_TIMER_KEY = "editRingerTimerKey";
    private RecyclerView mRecyclerView;
    private RingerTimerAdapter mRingerTimerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor mCursor;

    private long mEditRowIndex = RingerTimer.Timer.INVALID_ROW_INDEX;

    private EditRingerTimerDialogFragment mEditRingerTimerDialogFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.ringer_timer_fragment, container, false);
        if (savedInstanceState != null) {
            mEditRowIndex = savedInstanceState.getLong(EDIT_RINGER_TIMER_KEY);
        }
        setHasOptionsMenu(true);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.ringer_timer_recyclerview);

        getLoaderManager().initLoader(RINGER_TIMER_CURSOR_LOADER_ID, null, this);

        mEditRingerTimerDialogFragment = (EditRingerTimerDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(EditRingerTimerDialogFragment.TAG);
        if (mEditRingerTimerDialogFragment != null) {
            mEditRingerTimerDialogFragment.setListener(mEditRingerTimerListener);
        }

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = RingerTimerFragment.this.getFragmentManager().beginTransaction();
        switch(item.getItemId()) {
            case R.id.add_menu_item:
                CreateRingerTimerFragment createRingerTimerFragment = new CreateRingerTimerFragment();
                createRingerTimerFragment.setAddListener(mAddListener);
                fragmentTransaction.replace(R.id.fragment_container, createRingerTimerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.delete_all_item:
                RingerTimerStorageOperations.deleteAll(getContext());
                break;
            case R.id.test_console_item:
                fragmentTransaction.replace(R.id.fragment_container, new TestConsoleFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(EDIT_RINGER_TIMER_KEY, mEditRowIndex);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new RingerTimerCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursor = data;
        if (mRingerTimerAdapter == null) {
            mRingerTimerAdapter = new RingerTimerAdapter(getContext(), mCursor, mItemClickListener);
        }

        mRecyclerView.setAdapter(mRingerTimerAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mRingerTimerAdapter != null) {
            mRingerTimerAdapter.setCursor(null);
        }
    }

    private CreateRingerTimerFragment.ISaveListener mAddListener = new CreateRingerTimerFragment.ISaveListener() {
        @Override
        public void onAdd(int hour, int minute, int ringerMode) {
            RingerTimerModel ringerTimerModel = new RingerTimerModel();
            ringerTimerModel.setHour(hour);
            ringerTimerModel.setMinute(minute);
            ringerTimerModel.setRingerMode(ringerMode);
            long rowIndex = RingerTimerStorageOperations.insert(getContext(), ringerTimerModel);
            ringerTimerModel.setRowIndex(rowIndex);

            Util.setAlarm(getContext(), rowIndex, ringerMode, hour, minute);

            mCursor = RingerTimerStorageOperations.getAll(getContext());
            mRingerTimerAdapter.setCursor(mCursor);
            mRingerTimerAdapter.notifyDataSetChanged();
        }

        @Override
        public void onEdit(long rowIndex, int hour, int minute, int ringerMode) {
            RingerTimerModel ringerTimerModel = RingerTimerStorageOperations.getByRowIndex(getContext(), rowIndex);
            ringerTimerModel.setHour(hour);
            ringerTimerModel.setMinute(minute);
            ringerTimerModel.setRingerMode(ringerMode);
            RingerTimerStorageOperations.update(getContext(), rowIndex, ringerTimerModel);

            Util.setAlarm(getContext(), rowIndex, ringerMode, hour, minute);

            mCursor = RingerTimerStorageOperations.getAll(getContext());
            mRingerTimerAdapter.setCursor(mCursor);
            mRingerTimerAdapter.notifyDataSetChanged();
        }
    };

    private DialogInterface.OnClickListener mEditRingerTimerListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0) { // EDIT
                Bundle bundle = new Bundle();
                RingerTimerModel ringerTimer = RingerTimerStorageOperations.getByRowIndex(getContext(), mEditRowIndex);
                if (ringerTimer != null) {
                    bundle.putInt(CreateRingerTimerFragment.RINGER_HOUR_KEY, ringerTimer.getHour());
                    bundle.putInt(CreateRingerTimerFragment.RINGER_MINUTE_KEY, ringerTimer.getMinute());
                    bundle.putInt(CreateRingerTimerFragment.RINGER_MODE_KEY, ringerTimer.getRingerMode());
                    bundle.putLong(CreateRingerTimerFragment.RINGER_ROW_INDEX, ringerTimer.getRowIndex());
                }

                CreateRingerTimerFragment createRingerTimerFragment = new CreateRingerTimerFragment();
                createRingerTimerFragment.setAddListener(mAddListener);
                createRingerTimerFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = RingerTimerFragment.this.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, createRingerTimerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else { // DELETE
                RingerTimerStorageOperations.getByRowIndex(getContext(), mEditRowIndex);
                RingerTimerStorageOperations.delete(getContext(), mEditRowIndex);
            }
        }
    };

    private RingerTimerAdapter.IItemClickListener mItemClickListener = new RingerTimerAdapter.IItemClickListener() {
        @Override
        public void onLongClick(long rowIndex) {
            mEditRingerTimerDialogFragment = new EditRingerTimerDialogFragment();
            mEditRingerTimerDialogFragment.setListener(mEditRingerTimerListener);
            mEditRingerTimerDialogFragment.show(getActivity().getSupportFragmentManager(), EditRingerTimerDialogFragment.TAG);

            mEditRowIndex = rowIndex;
        }
    };
}
