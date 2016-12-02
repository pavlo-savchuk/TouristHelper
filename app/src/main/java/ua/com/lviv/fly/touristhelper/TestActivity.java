package ua.com.lviv.fly.touristhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import ua.com.lviv.fly.touristhelper.R;

public class TestActivity extends Activity {
    private String googleAPIKey = "your google api key";


    final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        System.out.println("hello");

        new UpdateTask().execute();
    }

    private class UpdateTask extends AsyncTask<String, String, String> {
        DefaultHttpClient client;
        HttpResponse res;
        HttpGet req;
        InputStream in;
        JSONObject jsonobj;
        JSONArray resarray;
        String requesturl;
        HttpEntity jsonentity;

        protected String doInBackground(String... urls) {
            requesturl = "https://maps.googleapis.com/maps/api/place/search/json?radius=500&sensor=false&key=" + "AIzaSyDcjBH-d5PcKPAAzt683TFT6h30t6YwVNY" + "&location=13.01,74.79";

            System.out.println("Request " + requesturl);
            client = new DefaultHttpClient();

            req=new HttpGet(requesturl);
            try {
                res = client.execute(req);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StatusLine status = res.getStatusLine();
            int code = status.getStatusCode();
            System.out.println(code);
            if (code != 200) {
                System.out.println("Request Has not succeeded");
                finish();
            }

            jsonentity = res.getEntity();
            try {
                in = jsonentity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                jsonobj = new JSONObject(convertStreamToString(in));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                resarray = jsonobj.getJSONArray("results");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("Test", resarray.toString());
            return resarray.toString();
        }

    }

    private String convertStreamToString(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder jsonstr = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                String t = line + "\n";
                jsonstr.append(t);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonstr.toString();
    }

}