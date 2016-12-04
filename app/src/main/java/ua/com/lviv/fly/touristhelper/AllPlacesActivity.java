package ua.com.lviv.fly.touristhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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

public class AllPlacesActivity extends Activity {
    DefaultHttpClient client;
    HttpResponse res;
    HttpGet req;
    InputStream in;
    JSONObject jsonobj;
    JSONArray resarray;
    String requesturl;
    HttpEntity jsonentity;

    final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requesturl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=49.832,24.01678&radius=500&types=food&name=cruise&key=AIzaSyDcjBH-d5PcKPAAzt683TFT6h30t6YwVNY";

        System.out.println("Request " + requesturl);
        client = new DefaultHttpClient();
        System.out.println("hello");
//        49.832091, 24.016785

        System.out.println("hello");
        new DownloadFilesTask().execute();
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

    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            req = new HttpGet(requesturl);
            try {
                res = client.execute(req);
                StatusLine status = res.getStatusLine();
                int code = status.getStatusCode();
                System.out.println(code);
                if (code != 200) {
                    System.out.println("Request Has not succeeded");
                    finish();
                }

                jsonentity = res.getEntity();
                in = jsonentity.getContent();

                jsonobj = new JSONObject(convertStreamToString(in));


                resarray = jsonobj.getJSONArray("results");
                Log.e(TAG, resarray.toString());
//                if (resarray.length() == 0) {
//                } else {
//                    int len = resarray.length();
//                    for (int j = 0; j < len; j++) {
//                        Log.e(TAG, resarray.getJSONObject(j).getString("name"));
//                    }
//                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}