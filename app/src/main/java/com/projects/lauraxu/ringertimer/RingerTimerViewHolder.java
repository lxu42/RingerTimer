package com.projects.lauraxu.ringertimer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lauraxu on 3/9/16.
 */
public class RingerTimerViewHolder extends RecyclerView.ViewHolder {
    public static final int RINGER_TIMER_ID_TAG = 79424;
    public View root;
    public TextView time;
    public TextView ringerMode;
    public RingerTimerViewHolder(View v) {
        super(v);
        root = v;
        time = (TextView) v.findViewById(R.id.ringer_timer_time);
        ringerMode = (TextView) v.findViewById(R.id.ringer_timer_ringer_mode);
    }
}
