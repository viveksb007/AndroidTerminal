package com.viveksb007.androidterminal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class about extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.about_msg)
                .setTitle("Developed by viveksb007")
                .setPositiveButton("Facebook Profile", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/viveksb007"));
                        startActivity(i);
                    }
                }).setNeutralButton("Source Code", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/viveksb007/AndroidTerminal"));
                startActivity(i);
            }
        });
        return builder.create();
    }
}
