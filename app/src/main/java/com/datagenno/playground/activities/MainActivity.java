package com.datagenno.playground.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.datagenno.playground.AppController;
import com.datagenno.playground.R;


public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.datagenno.playground.MESSAGE";
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        AppController.getLanguage();
    }


    /**
     * Get data from URL
     */
    public void getDisease(View view) {
        EditText diseaseId  = (EditText) findViewById(R.id.diseaseId);
        Intent   intent     = new Intent(this, DiseaseActivity.class);
        String   path       = diseaseId.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, path);
        startActivity(intent);
    }


    /**
     * Show alert dialog
     */
    public void exit(View view) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

        alertBuilder.setTitle(R.string.exit)
                .setMessage(R.string.exit_message)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
