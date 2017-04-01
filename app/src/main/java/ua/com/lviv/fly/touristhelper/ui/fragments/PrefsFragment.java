package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
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
        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
        EditTextPreference editTextPref = (EditTextPreference) findPreference(getString(R.string.user_first_name_key));
        editTextPref.setTitle(sp.getString(getString(R.string.user_first_name_key), getString(R.string.user_first_name_default_value)));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        L.e("onSharedPreferenceChanged = " + sharedPreferences.toString() + key);
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference etp = (EditTextPreference) pref;
//            pref.setSummary(etp.getText());
            pref.setTitle(etp.getText());
        }
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