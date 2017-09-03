package ua.com.lviv.fly.touristhelper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.JsonVO;
import ua.com.lviv.fly.touristhelper.model.Model;

/**
 * Created by PASHA on 03.09.2017.
 */

public class TestActivity extends AppCompatActivity {

    public static void startThisActivity(Context context, String data) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_test);

    }

}
