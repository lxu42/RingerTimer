package com.projects.lauraxu.ringertimer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lauraxu on 3/12/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ringerTimer.db";
    private static final int DB_VERSION = 1;
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTimerTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void createTimerTable(SQLiteDatabase db) {
        if (db == null) {
            throw new IllegalArgumentException("Database cannot be null");
        }

        db.execSQL("DROP TABLE IF EXISTS " + RingerTimer.Timer.TABLE_NAME);

        String query = "CREATE TABLE " + RingerTimer.Timer.TABLE_NAME + " (";
        query += RingerTimer.Timer._ID          + " " + "INTEGER" + " PRIMARY KEY AUTOINCREMENT,";
        query += RingerTimer.Timer.HOUR         + " " + "INTEGER" + ",";
        query += RingerTimer.Timer.MINUTE       + " " + "INTEGER" + ",";
        query += RingerTimer.Timer.RINGER_MODE  + " " + "INTEGER" + "";
        query += ");";

        db.execSQL(query);
    }
}
