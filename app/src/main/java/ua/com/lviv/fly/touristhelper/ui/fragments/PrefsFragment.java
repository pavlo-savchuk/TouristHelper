package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.ls.util.L;

import ua.com.lviv.fly.touristhelper.R;

public class PrefsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static PrefsFragment newInstance() {
        return new PrefsFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();

        EditTextPreference editTextPref = (EditTextPreference) findPreference(getString(R.string.user_first_name_key));
        editTextPref.setTitle(preferences.getString(getString(R.string.user_first_name_key), getString(R.string.user_first_name_default_value)));

        EditTextPreference prefEmail = (EditTextPreference) findPreference(getString(R.string.user_email_key));
        prefEmail.setTitle(preferences.getString(getString(R.string.user_email_key), getString(R.string.user_email_default_value)));

        EditTextPreference prefAddress = (EditTextPreference) findPreference(getString(R.string.user_address_key));
        prefAddress.setTitle(preferences.getString(getString(R.string.user_address_key), getString(R.string.user_address_default_value)));

        EditTextPreference prefAge = (EditTextPreference) findPreference(getString(R.string.user_age_key));
        prefAge.setTitle(preferences.getString(getString(R.string.user_age_key), getString(R.string.user_age_value)));

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        L.e("onSharedPreferenceChanged = " + sharedPreferences.toString() + key);
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference etp = (EditTextPreference) pref;
            String key1 = etp.getKey();
            String title = etp.getTitle().toString();
            L.e("key1 = " + title);
            if (key1.equals(getString(R.string.user_first_name_key))) {
                pref.setTitle(etp.getText());
            } else if (key1.equals(getString(R.string.user_email_key))) {
                pref.setTitle(etp.getText());
            }else if (key1.equals(getString(R.string.user_address_key))) {
                pref.setTitle(etp.getText());
            }else if (key1.equals(getString(R.string.user_age_key))) {
                pref.setTitle(etp.getText());
            }

        }
//        else if (pref instanceof ListPreference) {
//            String prefTitle = pref.getTitle().toString();
//            pref.setTitle(((ListPreference) pref).getValue());
//            switch (key) {
//                case "radius_key":
//                    L.e("prefTitle = " + prefTitle);
//                    L.e("pref = " +((ListPreference) pref).getValue());
//                    pref.setTitle(((ListPreference) pref).getValue());
//                    break;
//
//            }
//            if (prefTitle.contains(getString(R.string.radius_pref_title))) {
//                pref.setTitle(((ListPreference) pref).getValue());
//            } else if (prefTitle.contains(getString(R.string.types_pref_title))) {
//                pref.setTitle(((ListPreference) pref).getValue());
//            }
//        }
//        String string = getString(R.string.types_key);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}