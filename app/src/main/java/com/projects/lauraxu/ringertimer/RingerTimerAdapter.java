package com.projects.lauraxu.ringertimer;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lauraxu on 3/9/16.
 */
public class RingerTimerAdapter extends CursorAdapter<RingerTimerViewHolder> {
    private Context mContext;

    public RingerTimerAdapter(Cursor cursor) {
        super(cursor);
    }

    public RingerTimerAdapter(Context context, Cursor cursor) {
        super(cursor);
        mContext = context;
    }

    @Override
    public RingerTimerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.ringer_timer_item, parent, false);
        return new RingerTimerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RingerTimerViewHolder viewHolder, int position) {
        Cursor cursor = getCursor();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToPosition(position);
            RingerTimerModel ringerTimer = RingerTimerModel.fromCursor(cursor);
            if (ringerTimer != null) {
                viewHolder.time.setText(mContext.getResources().getString(R.string.time_text, ringerTimer.getHour(), ringerTimer.getMinute()));
                viewHolder.ringerMode.setText(mContext.getResources().getString(R.string.ringer_mode_text, ringerTimer.getRingerMode()));
            }
        }
    }
}
