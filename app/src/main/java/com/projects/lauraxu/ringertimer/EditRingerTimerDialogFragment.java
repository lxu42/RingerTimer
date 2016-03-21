package com.projects.lauraxu.ringertimer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by lauraxu on 3/8/16.
 */
public class EditRingerTimerDialogFragment extends DialogFragment {
    public static final String TAG = "EditRingerTimerDialogFragmentTag";
    private DialogInterface.OnClickListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CharSequence[] items = new CharSequence[]{getResources().getString(R.string.edit_ringer_timer), getResources().getString(R.string.delete_ringer_timer)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(items, mListener);

        return builder.create();
    }

    public void setListener(DialogInterface.OnClickListener  listener) {
        mListener = listener;
    }
}
