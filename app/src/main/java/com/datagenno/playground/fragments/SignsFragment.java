package com.datagenno.playground.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datagenno.playground.R;
import com.google.gson.internal.LinkedTreeMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignsFragment extends Fragment {

    private LinkedTreeMap signs;

    public SignsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sign, container, false);
    }


}
