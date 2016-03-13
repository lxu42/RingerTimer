package com.projects.lauraxu.ringertimer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lauraxu on 3/9/16.
 */
public class RingerTimerViewHolder extends RecyclerView.ViewHolder {
    public TextView time;
    public TextView ringerMode;
    public RingerTimerViewHolder(View v) {
        super(v);
        time = (TextView) v.findViewById(R.id.ringer_timer_time);
        ringerMode = (TextView) v.findViewById(R.id.ringer_timer_ringer_mode);
    }
}
