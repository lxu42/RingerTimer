package com.projects.lauraxu.ringertimer;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by lauraxu on 3/13/16.
 */
public class RingerTimerCursorLoader extends CursorLoader {
    public RingerTimerCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return RingerTimerStorageOperations.getAll(getContext());
    }
}
