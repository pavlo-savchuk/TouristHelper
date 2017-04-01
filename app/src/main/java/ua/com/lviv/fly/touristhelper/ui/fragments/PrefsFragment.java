package ua.com.lviv.fly.touristhelper.ui.fragments;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import ua.com.lviv.fly.touristhelper.R;

public class PrefsFragment extends PreferenceFragmentCompat {

    public static PrefsFragment newInstance() {
       return new PrefsFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.preferences);
    }

}