package com.datagenno.playground.ui;

import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 * Created by juliobetta on 11/10/14.
 */
public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context) {
        super(context);
    }

    public void hideAndDismiss() {
        hide();
        dismiss();
    }
}
