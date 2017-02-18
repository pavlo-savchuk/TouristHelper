package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.lviv.fly.touristhelper.R;

public class PrefHolder extends Fragment {

    public static PrefHolder newInstance() {
        PrefHolder fragment = new PrefHolder();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pref_holder, container, false);
        getChildFragmentManager().beginTransaction().add(R.id.container, new PrefsFragment()).commit();
        return view;
    }


}
