package com.datagenno.playground.controllers;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.datagenno.playground.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by juliobetta on 10/31/14.
 */
public class DiseasesController extends AbstractController {


    /**
     * Constructor
     * @param activity
     */
    public DiseasesController(Activity activity) {
        super(activity);
    }


    /**
     * Show disease
     * @param path
     */
    public void show(String path) {
        progress.show();

        // Tag used to cancel the request
        String tag_json_obj = "disease_obj_req";
        String url          = BASEPATH + "/diseases/" + path + ".json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject signs = response.getJSONObject("signs");
                        listSigns(signs);
                        progress.hideAndDismiss();
                    } catch(JSONException e) {
                        Log.d(AppController.TAG, e.getMessage());
                    }
                }
            } , onResponseError()
        );

        AppController.getInstance().addToRequestQueue(request, tag_json_obj);
    }


    /**
     * List disease's signs
     * @param signs
     */
    private void listSigns(JSONObject signs) {
        TextView responseText = (TextView) activity.findViewById(R.id.signs);

        for (int i = 0; i < signs.length(); i++) {

            Log.d(AppController.TAG, signs.toString());
        }
//        for(Object group : signs) {
//            Map.Entry<String, ArrayList> groupSet = (Map.Entry) group;
//
//            // prints out the group name
//            responseText.append(groupSet.getKey() + "\n");
//
//            for(Object sign : groupSet.getValue()) {
//                LinkedTreeMap signObject = (LinkedTreeMap) sign;
//
//                // prints out the sign name
//                responseText.append("\t" + signObject.get("sinal").toString() + "\n");
//            }
//        }
    }
}
