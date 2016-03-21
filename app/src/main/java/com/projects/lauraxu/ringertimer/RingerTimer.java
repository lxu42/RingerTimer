package com.projects.lauraxu.ringertimer;

import android.provider.BaseColumns;

/**
 * Created by lauraxu on 3/12/16.
 */
public final class RingerTimer {
    private RingerTimer() {}

    public static final class Timer implements BaseColumns {
        public static final long INVALID_ROW_INDEX = -1;
        public static final String TABLE_NAME = "timer";
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String RINGER_MODE = "ringer_mode";

    }
}
