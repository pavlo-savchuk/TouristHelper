package ua.com.lviv.fly.touristhelper.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.ls.util.L;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.manager.OptionManager;
import ua.com.lviv.fly.touristhelper.model.Model;

public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final OptionManager optionManager = Model.instance().getOptionManager();
        final Location myLocation = optionManager.getMyLocation();

        L.e("My location = " + myLocation.toString());


        CheckBox bank = (CheckBox) view.findViewById(R.id.bank);
        bank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setType("/nearbysearch/json?location=" + getLocation() + optionManager.getRadius() + "&types=bank&key=");
            }
        });

        CheckBox cafe = (CheckBox) view.findViewById(R.id.cafe);
        cafe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setType("/nearbysearch/json?location=" + getLocation() + optionManager.getRadius() + "&types=food|cafe&keyword=vegetarian&key=");
            }
        });

        CheckBox radius1 = (CheckBox) view.findViewById(R.id.radius1);
        radius1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setRadius("&radius=1000");
            }
        });

        CheckBox radius2 = (CheckBox) view.findViewById(R.id.radius2);
        radius2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                optionManager.setRadius("&radius=2000");
            }
        });

        return view;
    }

    private String getLocation() {
        Location location = Model.instance().getOptionManager().getMyLocation();
        return location.getLatitude() + "," + location.getLongitude();
    }

}
