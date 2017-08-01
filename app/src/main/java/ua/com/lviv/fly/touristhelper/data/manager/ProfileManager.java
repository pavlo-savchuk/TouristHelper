package ua.com.lviv.fly.touristhelper.data.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ls.util.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;

public class ProfileManager {
    private SharedPreferences pref;
    private Context context;
    private int index1 = 0;
    private int index2 = 0;
    private int index3 = 0;
    private List<Enum> data = new ArrayList<>(21);

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
//        getPref1Point();
//        getPref2Point();
//        getPref3Point();
//        getPref4Point();
//        getPref5Point();
//        getPref6Point();
//        getPref7Point();
//        getPref8Point();
//        getPref9Point();
//        getPref10Point();
//        getPref11Point();
//        getPref12Point();
//        getPref13Point();
//        getPref14Point();
//        getPref15Point();
//        getPref16Point();
//        getPref17Point();
//        getPref18Point();
//        getPref19Point();
//        getPref20Point();

        L.e("Data test = " + data.toString());

//        L.e("index1 = " + getIndex1());
//        L.e("index2 = " + getIndex2());
//        L.e("index3 = " + getIndex3());
        calculate();
    }

    private void calculate() {
        for (int l = 1; l <= 20; l++) {
            getPrefPoint(l);
        }
        L.e("Data test = " + data.toString());
    }
    private String getPref(int i) {
        return pref.getString("Key "+ i, "Не знаю");
    }

    private Enum getPrefPoint(int i) {
        String pref = getPref(i);
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add( result);

        return result;

    }

    private String getPref1() {
        return pref.getString(context.getString(R.string.key_1), "Не знаю");
    }

    private Enum getPref1Point() {
        String pref = getPref1();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        L.e("Data size  = " + data.size());
        data.add(1, result);

        return result;

    }


    private String getPref2() {
        return pref.getString(context.getString(R.string.key_2), "Не знаю");
    }

    private Enum getPref2Point() {
        String pref = getPref2();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(2, result);

        return result;

    }

    private String getPref3() {
        return pref.getString(context.getString(R.string.key_3), "Не знаю");
    }

    private Enum getPref3Point() {
        String pref = getPref3();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(3, result);

        return result;

    }

    private String getPref4() {
        return pref.getString(context.getString(R.string.key_4), "Не знаю");
    }

    private Enum getPref4Point() {
        String pref = getPref4();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(4, result);

        return result;

    }

    private String getPref5() {
        return pref.getString(context.getString(R.string.key_5), "Не знаю");
    }

    private Enum getPref5Point() {
        String pref = getPref5();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(5, result);

        return result;

    }

    private String getPref6() {
        return pref.getString(context.getString(R.string.key_6), "Не знаю");
    }

    private Enum getPref6Point() {
        String pref = getPref6();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(6, result);

        return result;

    }

    private String getPref7() {
        return pref.getString(context.getString(R.string.key_7), "Не знаю");
    }

    private Enum getPref7Point() {
        String pref = getPref7();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(7, result);

        return result;

    }

    private String getPref8() {
        return pref.getString(context.getString(R.string.key_8), "Не знаю");
    }

    private Enum getPref8Point() {
        String pref = getPref8();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(8, result);

        return result;

    }

    private String getPref9() {
        return pref.getString(context.getString(R.string.key_9), "Не знаю");
    }

    private Enum getPref9Point() {
        String pref = getPref9();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(9, result);

        return result;

    }

    private String getPref10() {
        return pref.getString(context.getString(R.string.key_10), "Не знаю");
    }

    private Enum getPref10Point() {
        String pref = getPref10();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(10, result);

        return result;

    }

    private String getPref11() {
        return pref.getString(context.getString(R.string.key_11), "Не знаю");
    }

    private Enum getPref11Point() {
        String pref = getPref11();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(11, result);

        return result;

    }

    private String getPref12() {
        return pref.getString(context.getString(R.string.key_12), "Не знаю");
    }

    private Enum getPref12Point() {
        String pref = getPref12();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(12, result);

        return result;

    }

    private String getPref13() {
        return pref.getString(context.getString(R.string.key_13), "Не знаю");
    }

    private Enum getPref13Point() {
        String pref = getPref13();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(13, result);

        return result;

    }

    private String getPref14() {
        return pref.getString(context.getString(R.string.key_14), "Не знаю");
    }

    private Enum getPref14Point() {
        String pref = getPref14();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(14, result);
        return result;

    }

    private String getPref15() {
        return pref.getString(context.getString(R.string.key_15), "Не знаю");
    }

    private Enum getPref15Point() {
        String pref = getPref15();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(15, result);

        return result;

    }

    private String getPref16() {
        return pref.getString(context.getString(R.string.key_16), "Не знаю");
    }

    private Enum getPref16Point() {
        String pref = getPref16();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(16, result);

        return result;

    }

    private String getPref17() {
        return pref.getString(context.getString(R.string.key_17), "Не знаю");
    }

    private Enum getPref17Point() {
        String pref = getPref17();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(17, result);

        return result;

    }

    private String getPref18() {
        return pref.getString(context.getString(R.string.key_18), "Не знаю");
    }

    private Enum getPref18Point() {
        String pref = getPref18();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(18, result);
        return result;

    }

    private String getPref19() {
        return pref.getString(context.getString(R.string.key_19), "Не знаю");
    }

    private Enum getPref19Point() {
        String pref = getPref19();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(19, result);

        return result;

    }

    private String getPref20() {
        return pref.getString(context.getString(R.string.key_20), "Не знаю");
    }

    private Enum getPref20Point() {
        String pref = getPref20();
        Enum result = null;
        switch (pref) {
            case "Не знаю":
                result = Enum.hz;
                break;
            case "Так":
                result = Enum.yes;
                break;
            case "Ні":
                result = Enum.no;
                break;
        }
        data.add(20, result);

        return result;

    }


}
