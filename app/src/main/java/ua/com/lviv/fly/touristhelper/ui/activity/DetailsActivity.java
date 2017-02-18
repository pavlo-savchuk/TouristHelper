package ua.com.lviv.fly.touristhelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.ls.util.L;

import java.net.URL;

import ua.com.lviv.fly.touristhelper.PlaceActivity;
import ua.com.lviv.fly.touristhelper.R;

public class DetailsActivity extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private GoogleApiClient mGoogleApiClient;
    private Uri webUrl;
    private String testID;

    public static void startThisActivity(Context context, String placeId){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("TestID", placeId);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        testID = extras.getString("TestID");
        L.e("testID= " + testID);
        initGoogleClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getPlaceById();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(PlaceActivity.class.getName(), "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(PlaceActivity.class.getName(), "onConnectionFailed");
    }

    private void initGoogleClient(){
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    private void getPlaceById(){
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, testID)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            final Place myPlace = places.get(0);
                            fillView(myPlace);
                            L.e("Place found: " + myPlace.getWebsiteUri());
                        } else {
                            L.e("Place not found");
                        }
                        places.release();
                    }
                });
    }

    private void fillView(final Place place){
        webUrl = place.getWebsiteUri();
        TextView site = (TextView)findViewById(R.id.webSite);
        TextView data = (TextView)findViewById(R.id.data);
        site.setText(place.getWebsiteUri().toString());
        data.setText(place.getAddress());

        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                L.e("testPlace.getWebsiteUri()" + testPlace.getWebsiteUri());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(webUrl);
                startActivity(i);
            }
        });
    }
}
