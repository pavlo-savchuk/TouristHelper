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

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getIndex3() {
        return index3;
    }


    public String getPref1() {
        return pref.getString(context.getString(R.string.key_1), "Не знаю");
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
        return pref.getString(context.getString(R.string.key_2), "Не знаю");
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
        return pref.getString(context.getString(R.string.key_3), "Не знаю");
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

    public String getPref4() {
        return pref.getString(context.getString(R.string.key_4), "Не знаю");
    }

    public int getPref4Point() {
        String pref = getPref4();
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

    public String getPref5() {
        return pref.getString(context.getString(R.string.key_5), "Не знаю");
    }

    public int getPref5Point() {
        String pref = getPref5();
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

    public String getPref6() {
        return pref.getString(context.getString(R.string.key_6), "Не знаю");
    }

    public int getPref6Point() {
        String pref = getPref6();
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

    public String getPref7() {
        return pref.getString(context.getString(R.string.key_7), "Не знаю");
    }

    public int getPref7Point() {
        String pref = getPref7();
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

    public String getPref8() {
        return pref.getString(context.getString(R.string.key_8), "Не знаю");
    }

    public int getPref8Point() {
        String pref = getPref8();
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

    public String getPref9() {
        return pref.getString(context.getString(R.string.key_9), "Не знаю");
    }

    public int getPref9Point() {
        String pref = getPref9();
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

    public String getPref10() {
        return pref.getString(context.getString(R.string.key_10), "Не знаю");
    }

    public int getPref10Point() {
        String pref = getPref10();
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

    public String getPref11() {
        return pref.getString(context.getString(R.string.key_11), "Не знаю");
    }

    public int getPref11Point() {
        String pref = getPref11();
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

    public String getPref12() {
        return pref.getString(context.getString(R.string.key_12), "Не знаю");
    }

    public int getPref12Point() {
        String pref = getPref12();
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

    public String getPref13() {
        return pref.getString(context.getString(R.string.key_13), "Не знаю");
    }

    public int getPref13Point() {
        String pref = getPref13();
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

    public String getPref14() {
        return pref.getString(context.getString(R.string.key_14), "Не знаю");
    }

    public int getPref14Point() {
        String pref = getPref14();
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

    public String getPref15() {
        return pref.getString(context.getString(R.string.key_15), "Не знаю");
    }

    public int getPref15Point() {
        String pref = getPref15();
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

    public String getPref16() {
        return pref.getString(context.getString(R.string.key_16), "Не знаю");
    }

    public int getPref16Point() {
        String pref = getPref16();
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

    public String getPref17() {
        return pref.getString(context.getString(R.string.key_17), "Не знаю");
    }

    public int getPref17Point() {
        String pref = getPref17();
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

    public String getPref18() {
        return pref.getString(context.getString(R.string.key_18), "Не знаю");
    }

    public int getPref18Point() {
        String pref = getPref18();
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

    public String getPref19() {
        return pref.getString(context.getString(R.string.key_19), "Не знаю");
    }

    public int getPref19Point() {
        String pref = getPref19();
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

    public String getPref20() {
        return pref.getString(context.getString(R.string.key_20), "Не знаю");
    }

    public int getPref20Point() {
        String pref = getPref20();
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
