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

    public ProfileManager() {
        this.pref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        this.context = TemplateApplication.getSharedContext();
    }

    public String getPref1() {
        return  pref.getString(context.getString(R.string.key_1),  "");
    }

    public int getPref1Point(){
        int i = Arrays.asList(context.getResources().getStringArray(R.array.values_1)).indexOf(getPref1());
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
