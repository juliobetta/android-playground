package com.datagenno.playground.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.datagenno.playground.R;
import com.datagenno.playground.controllers.DiseasesController;
import com.datagenno.playground.utils.Constants;


public class DiseaseActivity extends Activity implements FragmentManager.OnBackStackChangedListener {

    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        initializeFragments();

        getActionBar().setTitle(null);

        // Get the message from the intent
        Intent intent = getIntent();
        String path   = intent.getStringExtra(Constants.EXTRA_MESSAGE);

        DiseasesController diseasesController = new DiseasesController(DiseaseActivity.this);

        diseasesController.show(path);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onBackStackChanged() {
        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_disease, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id         = item.getItemId();
        int fragmentId = 0;

        switch(id) {
            case R.id.action_signs:
                fragmentId = R.id.fragment_sign;
            break;

            case R.id.action_test:
                fragmentId = R.id.fragment_test;
            break;
        }

        if(fragmentId != 0) {
            showFragment(fragmentId, getFragmentManager().beginTransaction());
            return true;
        }

        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
        //new Handler().post(new Runnable() {
        //    @Override
        //    public void run() {
        //        invalidateOptionsMenu();
        //    }
        //});

        return super.onOptionsItemSelected(item);
    }


    /**
     * Initialize fragment manager
     */
    private void initializeFragments() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // hide all fragments ...
        hideAllFragments(transaction);

        // ... and show SignFragment
        showFragment(R.id.fragment_sign, transaction);
    }


    /**
     * Hide all fragments
     * @param transaction current transaction
     */
    private void hideAllFragments(FragmentTransaction transaction) {
        Integer[] fragments = { R.id.fragment_sign, R.id.fragment_test };

        for(int fragment : fragments) {
            transaction.hide(getFragmentManager().findFragmentById(fragment));
        }
    }


    /**
     * Show fragment and then commit transaction
     * @param fragmentId
     * @param transaction
     */
    private void showFragment(int fragmentId, FragmentTransaction transaction) {
        showFragment(fragmentId, transaction, true);
    }


    /**
     * Show fragment
     * @param fragmentId  fragment ID, R.id.{fragmentId}
     * @param transaction current transaction
     * @param commit      transaction?
     */
    private void showFragment(int fragmentId, FragmentTransaction transaction, boolean commit) {
        // =========================================================================================
        // DON'T KNOW WHY THE ANIMATED TRANSITIONS ARE NOT WORKING... =/ ===========================
        // =========================================================================================

        // transaction.setCustomAnimations(
        //    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
        //    R.animator.card_flip_left_in, R.animator.card_flip_left_out
        // );

        // transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        // =========================================================================================
        // =========================================================================================


        if(currentFragment != null && currentFragment.isVisible()) {
            transaction.hide(currentFragment);
        }

        transaction.show(currentFragment = getFragmentManager().findFragmentById(fragmentId));

        if(commit) {
            transaction
               // Add this transaction to the back stack, allowing users to press Back
               // to get to the previous fragment.
               //.addToBackStack(null)
               .commit();
        }
    }
}
