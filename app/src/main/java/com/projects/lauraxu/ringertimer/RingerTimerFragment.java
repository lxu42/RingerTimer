package com.projects.lauraxu.ringertimer;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class RingerTimerFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RingerTimerAdapter mRingerTimerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor mCursor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.ringer_timer_fragment, container, false);
        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateRingerTimerFragment createRingerTimerFragment = new CreateRingerTimerFragment();
                createRingerTimerFragment.setAddListener(mAddListener);
                FragmentTransaction fragmentTransaction = RingerTimerFragment.this.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, createRingerTimerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Button testConsoleButton = (Button) root.findViewById(R.id.test_console_button);
        testConsoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = RingerTimerFragment.this.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new TestConsoleFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mRecyclerView = (RecyclerView) root.findViewById(R.id.ringer_timer_recyclerview);
        if (mCursor == null) {
            mCursor = RingerTimerStorageOperations.getAll(getContext());
        }
        if (mRingerTimerAdapter == null) {
            mRingerTimerAdapter = new RingerTimerAdapter(getContext(), mCursor);
        }

        mRecyclerView.setAdapter(mRingerTimerAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return root;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private CreateRingerTimerFragment.IAddListener mAddListener = new CreateRingerTimerFragment.IAddListener() {
        @Override
        public void onAdd(int hour, int minute, int ringerMode) {
            RingerTimerModel ringerTimerModel = new RingerTimerModel();
            ringerTimerModel.setHour(hour);
            ringerTimerModel.setMinute(minute);
            ringerTimerModel.setRingerMode(ringerMode);

            RingerTimerStorageOperations.insert(getContext(), ringerTimerModel);
            mCursor = RingerTimerStorageOperations.getAll(getContext());
            mRingerTimerAdapter.setCursor(mCursor);
            mRingerTimerAdapter.notifyDataSetChanged();
        }
    };
}
