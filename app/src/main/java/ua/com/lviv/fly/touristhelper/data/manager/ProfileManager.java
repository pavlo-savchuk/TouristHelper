package ua.com.lviv.fly.touristhelper.data.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ls.util.L;

import java.util.Arrays;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;

public class ProfileManager {
    private SharedPreferences pref;
    private Context context;
    private int index1 = 0;
    private int index2 = 0;
    private int index3 = 0;

    public ProfileManager() {
        this.pref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        this.context = TemplateApplication.getSharedContext();
    }

    public String getPref1() {
        return pref.getString(context.getString(R.string.key_1), "");
    }

    public int getPref1Point() {
        String pref = getPref1();
        int result = 0;
        switch (pref) {
            case "Не знаю":
                result = 1;
                index1 = index1 + result;
                break;
            case "Так":
                result = 2;
                index2 = index2 + result;
                break;
            case "Ні":
                result = 3;
                index3 = index3 + result;
                break;
        }

        return result;

    }


    public String getPref2() {
        return pref.getString(context.getString(R.string.key_2), "");
    }

    public int getPref2Point() {
        String pref = getPref2();
        int result = 0;
        switch (pref) {
            case "Не знаю":
                result = 1;
                index1 = index1 + result;
                break;
            case "Так":
                result = 2;
                index2 = index2 + result;
                break;
            case "Ні":
                result = 3;
                index3 = index3 + result;
                break;
        }

        return result;

    }

    public String getPref3() {
        return pref.getString(context.getString(R.string.key_3), "");
    }

    public int getPref3Point() {
        String pref = getPref3();
        int result = 0;
        switch (pref) {
            case "Не знаю":
                result = 1;
                index1 = index1 + result;
                break;
            case "Так":
                result = 2;
                index2 = index2 + result;
                break;
            case "Ні":
                result = 3;
                index3 = index3 + result;
                break;
        }

        return result;

    }


}
