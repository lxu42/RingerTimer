package com.projects.lauraxu.ringertimer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by lauraxu on 3/8/16.
 */
public class RingerModeDialogFragment extends DialogFragment {
    public static final String TAG = "ringerModeDialogFragmentTag";
    private DialogInterface.OnClickListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CharSequence[] items = new CharSequence[]{getResources().getString(R.string.mute), getResources().getString(R.string.vibrate), getResources().getString(R.string.sound)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.choose_sound)
                .setItems(items, mListener);

        return builder.create();
    }

    public void setListener(DialogInterface.OnClickListener  listener) {
        mListener = listener;
    }
}
