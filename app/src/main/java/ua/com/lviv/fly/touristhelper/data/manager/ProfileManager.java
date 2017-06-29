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


    public void calculateScore() {
        index1 = 0;
        index2 = 0;
        index3 = 0;
        getPref1Point();
        getPref2Point();
        getPref3Point();
        getPref4Point();
        getPref5Point();
        getPref6Point();
        getPref7Point();
        getPref8Point();
        getPref9Point();
        getPref10Point();
        getPref11Point();
        getPref12Point();

        getPref13Point();
        getPref14Point();
        getPref15Point();
        getPref16Point();
        getPref17Point();
        getPref18Point();
        getPref19Point();
        getPref20Point();

        L.e("index1 = " + getIndex1());
        L.e("index2 = " + getIndex2());
        L.e("index3 = " + getIndex3());
    }

    private String getPref1() {
        return pref.getString(context.getString(R.string.key_1), "Не знаю");
    }

    private int getPref1Point() {
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


    private String getPref2() {
        return pref.getString(context.getString(R.string.key_2), "Не знаю");
    }

    private int getPref2Point() {
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

    private String getPref3() {
        return pref.getString(context.getString(R.string.key_3), "Не знаю");
    }

    private int getPref3Point() {
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

    private String getPref4() {
        return pref.getString(context.getString(R.string.key_4), "Не знаю");
    }

    private int getPref4Point() {
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

    private String getPref5() {
        return pref.getString(context.getString(R.string.key_5), "Не знаю");
    }

    private int getPref5Point() {
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

    private String getPref6() {
        return pref.getString(context.getString(R.string.key_6), "Не знаю");
    }

    private int getPref6Point() {
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

    private String getPref7() {
        return pref.getString(context.getString(R.string.key_7), "Не знаю");
    }

    private int getPref7Point() {
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

    private String getPref8() {
        return pref.getString(context.getString(R.string.key_8), "Не знаю");
    }

    private int getPref8Point() {
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

    private String getPref9() {
        return pref.getString(context.getString(R.string.key_9), "Не знаю");
    }

    private int getPref9Point() {
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

    private String getPref10() {
        return pref.getString(context.getString(R.string.key_10), "Не знаю");
    }

    private int getPref10Point() {
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

    private String getPref11() {
        return pref.getString(context.getString(R.string.key_11), "Не знаю");
    }

    private int getPref11Point() {
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

    private String getPref12() {
        return pref.getString(context.getString(R.string.key_12), "Не знаю");
    }

    private int getPref12Point() {
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

    private String getPref13() {
        return pref.getString(context.getString(R.string.key_13), "Не знаю");
    }

    private int getPref13Point() {
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

    private String getPref14() {
        return pref.getString(context.getString(R.string.key_14), "Не знаю");
    }

    private int getPref14Point() {
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

    private String getPref15() {
        return pref.getString(context.getString(R.string.key_15), "Не знаю");
    }

    private int getPref15Point() {
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

    private String getPref16() {
        return pref.getString(context.getString(R.string.key_16), "Не знаю");
    }

    private int getPref16Point() {
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

    private String getPref17() {
        return pref.getString(context.getString(R.string.key_17), "Не знаю");
    }

    private int getPref17Point() {
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

    private String getPref18() {
        return pref.getString(context.getString(R.string.key_18), "Не знаю");
    }

    private int getPref18Point() {
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

    private String getPref19() {
        return pref.getString(context.getString(R.string.key_19), "Не знаю");
    }

    private int getPref19Point() {
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

    private String getPref20() {
        return pref.getString(context.getString(R.string.key_20), "Не знаю");
    }

    private int getPref20Point() {
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
