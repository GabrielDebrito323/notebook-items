package com.ed.globallogik.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.ed.globallogik.R;

public class CustomAlertDialog extends AlertDialog.Builder {

    Context context;

    public CustomAlertDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public AlertDialog create() {

        return super.create();
    }

    public CustomAlertDialog setType(Type type) {

        CustomAlertDialog dialog;

        switch (type) {
            case ERROR: {
                dialog = (CustomAlertDialog) setIcon(R.drawable.ic_error);
                dialog.setTitle(R.string.error);
            }
            break;
            default: throw new IllegalStateException("Unexpected value: " + type);
        };

        return dialog;
    }



    public enum Type {
        ERROR
    }
}