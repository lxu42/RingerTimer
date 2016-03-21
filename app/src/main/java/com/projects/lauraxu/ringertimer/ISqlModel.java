package com.projects.lauraxu.ringertimer;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by lauraxu on 3/12/16.
 */
public interface ISqlModel {
    ContentValues getContentValues();
    void set(String key, Integer value);
    void set(String key, long value);
    Object get(String key);

}
