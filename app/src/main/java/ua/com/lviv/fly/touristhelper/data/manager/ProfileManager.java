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
    private int index4 = 0;

    public ProfileManager() {
        this.pref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        this.context = TemplateApplication.getSharedContext();
    }

    public String getPref1() {
        L.e("Pref1 = " + pref.getString(context.getString(R.string.key_1), ""));
        return pref.getString(context.getString(R.string.key_1), "");
    }

    public int getPref1Point() {
        String pref1 = getPref1();
        int result = 0;
        switch (pref1) {
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
        int i = Arrays.asList(context.getResources().getStringArray(R.array.values_1)).indexOf(getPref2());
//        L.e("Point 1 = " + i);
//        int[] intArray = context.getResources().getIntArray(R.array.options_1);
//        if(i == -1){
//            return intArray[1];
//        }else {
//            return intArray[i];
//        }

        return i;

    }
}
