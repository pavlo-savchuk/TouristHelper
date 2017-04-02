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

        ListPreference prefTypes = (ListPreference) findPreference(getString(R.string.types_key));
        prefTypes.setTitle(preferences.getString(getString(R.string.types_key), getResources().getStringArray(R.array.types)[0]));

        ListPreference prefRadius = (ListPreference) findPreference(getString(R.string.radius_key));
        prefRadius.setTitle(preferences.getString(getString(R.string.radius_key), getResources().getStringArray(R.array.radius)[0]));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        L.e("onSharedPreferenceChanged = " + sharedPreferences.toString() + key);
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference etp = (EditTextPreference) pref;
//            pref.setSummary(etp.getText());
            pref.setTitle(etp.getText());
        } else if (pref instanceof ListPreference) {
            String prefTitle = pref.getTitle().toString();
            if (prefTitle.contains(getString(R.string.radius_pref_title))) {
                pref.setTitle(((ListPreference) pref).getValue());
            } else if (prefTitle.contains(getString(R.string.types_pref_title))) {
                pref.setTitle(((ListPreference) pref).getValue());
            }
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