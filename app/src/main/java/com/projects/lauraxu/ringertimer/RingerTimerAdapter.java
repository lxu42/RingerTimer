package com.projects.lauraxu.ringertimer;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by lauraxu on 3/9/16.
 */
public class RingerTimerAdapter extends CursorAdapter<RingerTimerViewHolder> {
    private Context mContext;

    private IItemClickListener mItemClickListener;

    public RingerTimerAdapter(Context context, Cursor cursor, IItemClickListener itemClickListener) {
        super(cursor);
        mContext = context;
        mItemClickListener = itemClickListener;
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
            final RingerTimerModel ringerTimer = RingerTimerModel.fromCursor(cursor);
            if (ringerTimer != null) {
                viewHolder.time.setText(mContext.getResources().getString(R.string.time_text, ringerTimer.getHour(), ringerTimer.getMinute()));
                viewHolder.ringerMode.setText(mContext.getResources().getString(R.string.ringer_mode_text, ringerTimer.getRingerModeName(mContext)));
                viewHolder.root.setTag(R.id.RINGER_TIMER_ID_TAG, ringerTimer.getRowIndex());
                viewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mItemClickListener.onLongClick(ringerTimer.getRowIndex());
                        return true;
                    }
                });
            }
        }
    }

    public interface IItemClickListener {
        void onLongClick(long rowIndex);
    }
}
