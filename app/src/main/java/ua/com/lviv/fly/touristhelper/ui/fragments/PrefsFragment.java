package ua.com.lviv.fly.touristhelper.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import ua.com.lviv.fly.touristhelper.R;

public class PrefsFragment extends PreferenceFragmentCompat {

    public static PrefsFragment newInstance() {
       return new PrefsFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean checkbox_preference = sharedPref.getBoolean("checkbox_preference", true);
//        Toast.makeText(getActivity(),"checkbox_preference " + checkbox_preference, Toast.LENGTH_LONG).show();


        SharedPreferences list_preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String preference = list_preference.getString("list_preference", "Test");

        Toast.makeText(getActivity(),"preference " + preference, Toast.LENGTH_LONG).show();
    }

}