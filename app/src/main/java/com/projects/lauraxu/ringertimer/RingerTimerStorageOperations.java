package com.projects.lauraxu.ringertimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lauraxu on 3/12/16.
 */
public class RingerTimerStorageOperations {

    public static long insert(Context context, RingerTimerModel ringerTimer) {
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        long rowIndex;
        try {
            rowIndex = db.insertOrThrow(RingerTimer.Timer.TABLE_NAME, null, ringerTimer.getContentValues());
        } catch (SQLException e) {
            rowIndex = RingerTimer.Timer.INVALID_ROW_INDEX;
        }

        return rowIndex;
    }

    public static Cursor getAll(Context context) {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + RingerTimer.Timer.TABLE_NAME, null);
        } catch (SQLException e) {}

        finally {
            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
            }
        }
        return cursor;
    }
}
