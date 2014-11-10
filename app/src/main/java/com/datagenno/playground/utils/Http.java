package com.datagenno.playground.utils;

/**
 * Created by juliobetta on 10/30/14.
 */

import android.app.Activity;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class Http {

    private final String USER_AGENT = "Mozilla/5.0";
    private Activity activity;

    public Http(Activity activity) {
        this.activity = activity;
    }

    // HTTP GET request
    public void sendGet(String url) throws Exception {
        AsyncTask httpTask = new HttpTask(new HttpGet(url), this.activity).execute();
    }

    // HTTP POST request
    public String sendPost(URL obj) throws Exception {
        return "";
    }


    class HttpTask extends AsyncTask<String, Void, String> {
        private HttpRequestBase action;
        private Activity activity;

        public HttpTask(HttpRequestBase action, Activity activity) {
            super();
            this.activity = activity;
            this.action   = action;
        }


        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;

            try {
                response = httpclient.execute(action);
                StatusLine statusLine = response.getStatusLine();

                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    responseString = out.toString();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            //Do anything with response..
        }
    }
}
