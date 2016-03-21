package com.projects.lauraxu.ringertimer;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by lauraxu on 3/12/16.
 */
public class AbstractSqlModel implements ISqlModel {
    private ContentValues mContentValues;

    public AbstractSqlModel() {
        mContentValues = new ContentValues();
    }

    public static AbstractSqlModel fromCursor(Cursor cursor, AbstractSqlModel model) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }

        if (cursor.getPosition() < 0) {
            cursor.moveToFirst();
        }

        ContentValues contentValues = model.getContentValues();

        for (int i = 0, columnCount = cursor.getColumnCount(); i < columnCount; i++) {
            String columnName = cursor.getColumnName(i);
            switch (cursor.getType(i)) {
                case Cursor.FIELD_TYPE_BLOB:
                    contentValues.put(columnName, cursor.getBlob(i));
                    break;
                case Cursor.FIELD_TYPE_FLOAT:
                    contentValues.put(columnName, cursor.getFloat(i));
                    break;
                case Cursor.FIELD_TYPE_INTEGER:
                    contentValues.put(columnName, cursor.getInt(i));
                    break;
                case Cursor.FIELD_TYPE_STRING:
                    contentValues.put(columnName, cursor.getString(i));
                    break;
                case Cursor.FIELD_TYPE_NULL:
                    contentValues.putNull(columnName);
                    break;
            }
        }

        return model;
    }

    @Override
    public ContentValues getContentValues() {
        return mContentValues;
    }

    @Override
    public void set(String key, Integer value) {
        mContentValues.put(key, value);
    }

    @Override
    public void set(String key, long value) {
        mContentValues.put(key, value);
    }

    @Override
    public Object get(String key) {
        return mContentValues.get(key);
    }
}
