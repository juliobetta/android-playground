package com.datagenno.playground;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.datagenno.playground.controllers.Controller;
import com.datagenno.playground.controllers.Diseases;


public class MainActivity extends Activity {
    private final Context context = this;
    private Button exitButton;
    private Button httpGetButton;
    private EditText responseText;
    private EditText diseaseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ActiveAndroid.initialize(this);

        exitButton    = (Button)   findViewById(R.id.exitButton);
        httpGetButton = (Button)   findViewById(R.id.httpGet);
        responseText  = (EditText) findViewById(R.id.reponseText);
        diseaseId     = (EditText) findViewById(R.id.diseaseId);

        exitButton.setOnClickListener(exit());
        httpGetButton.setOnClickListener(getDisease());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Settings selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Get data from URL
     * @return View.OnClickListener
     */
    private View.OnClickListener getDisease() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String     path       = diseaseId.getText().toString();
                Controller controller = new Diseases(MainActivity.this);

                controller.show(path);
            }
        };
    }


    /**
     * Show alert dialog
     */
    private View.OnClickListener exit() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        };
    }
}
