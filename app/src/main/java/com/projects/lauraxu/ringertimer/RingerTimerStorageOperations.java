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

    public static void delete(Context context, long rowIndex) {
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        try {
            db.delete(RingerTimer.Timer.TABLE_NAME, RingerTimer.Timer._ID + "=?", new String[]{Long.toString(rowIndex)});
        } catch (SQLException e) {

        }
    }

    public static void deleteAll(Context context) {
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        try {
            db.delete(RingerTimer.Timer.TABLE_NAME, null, null);
        } catch (SQLException e) {

        }
    }

    public static void getByRowIndex(Context context, long rowIndex) {
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        try {
            db.rawQuery("SELECT * FROM " + RingerTimer.Timer.TABLE_NAME + " WHERE " + RingerTimer.Timer._ID + "=" + Long.toString(rowIndex), null);
        } catch (SQLException e) {

        }
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
