package com.datagenno.playground.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.datagenno.playground.R;
import com.datagenno.playground.utils.Constants;
import com.datagenno.playground.utils.RoundedImage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.datagenno.playground.MESSAGE";
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        loadRoundedImage();
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


    /**
     * Load a remote image and then make it rounded. =)
     */
    private void loadRoundedImage() {
        final ImageView imageView = (ImageView) findViewById(R.id.sample_image);
              String    imageURL  = Constants.BASE_PATH + "public/images/logo_square.png";

        ImageLoader.getInstance().displayImage(imageURL, imageView, Constants.IMAGE_OPTIONS,
            new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    final int shadowSize  = getResources().getDimensionPixelSize(R.dimen.shadow_size);
                    final int shadowColor = getResources().getColor(R.color.shadow_color);

                    RoundedImage roundedImage = new RoundedImage(loadedImage, shadowSize, shadowColor);

                    imageView.setImageDrawable(roundedImage);
                }
            }
        );
    }
}
