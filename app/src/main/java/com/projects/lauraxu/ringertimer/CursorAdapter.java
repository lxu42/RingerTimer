package com.projects.lauraxu.ringertimer;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lauraxu on 3/9/16.
 */
public abstract class CursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private Cursor mCursor;

    public CursorAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    public void setCursor(@NonNull Cursor cursor) {
        mCursor = cursor;
    }

    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

}
