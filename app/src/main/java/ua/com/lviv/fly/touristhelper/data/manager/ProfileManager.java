package ua.com.lviv.fly.touristhelper.data.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ls.util.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;

public class ProfileManager {
    private SharedPreferences pref;
    private Context context;

    private List<Integer> delete = new ArrayList<>();
    private Map<Integer, Enum> mapData = new HashMap<>();

    public ProfileManager() {
        this.pref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        this.context = TemplateApplication.getSharedContext();
    }


    public void calculateScore() {
//        createTestData();
        calculate();

        filterData();
        createReport();
        L.e("Data test 1= " + mapData.toString());

    }

    private void calculate() {
        for (int l = 1; l <= 20; l++) {
            getPrefPoint(l);
        }
        L.e("Data test = " + mapData.toString());
    }

    private String getPref(int i) {
        return pref.getString("Key " + i, "Не знаю");
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
        mapData.put(i, result);
        return result;

    }

    private void filterData() {
        delete.clear();
        if (mapData.get(2) == Enum.yes && mapData.get(8) == Enum.yes) {
            delete.add(2);
        }
        if (mapData.get(2) == Enum.yes && mapData.get(14) == Enum.yes) {
            delete.add(2);
            delete.add(14);
        }
        if (mapData.get(8) == Enum.yes && mapData.get(14) == Enum.yes) {
            delete.add(14);
        }
        if (mapData.get(3) == Enum.yes && mapData.get(7) == Enum.yes) {
            delete.add(3);
            delete.add(7);
        }
        if (mapData.get(3) == Enum.yes && mapData.get(12) == Enum.yes) {
            delete.add(12);
        }
//        if (mapData.get(6) == Enum.yes && mapData.get(11) == Enum.yes) {
//            delete.add(mapData.get(12));
//        }
//
//        if (mapData.get(4) == Enum.yes && mapData.get(12) == Enum.yes) {
//            delete.add(mapData.get(12));
//        }
//
//        if (mapData.get(8) == Enum.yes && mapData.get(15) == Enum.yes) {
//            delete.add(mapData.get(12));
//        }
//
//        if (mapData.get(9) == Enum.yes && mapData.get(18) == Enum.yes) {
//            delete.add(mapData.get(12));
//        }
//
//        if (mapData.get(9) == Enum.yes && mapData.get(18) == Enum.yes) {
//            delete.add(mapData.get(12));
//        }

//        if (mapData.get(9) == Enum.yes && mapData.get(18) == Enum.yes) {
//            delete.add(mapData.get(12));
//        }
        L.e("deleteTest = " + delete.toString());
        mapData.keySet().removeAll(delete);

    }


    private void createReport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, Enum> entry : mapData.entrySet()) {
            Integer key = entry.getKey();
            if (entry.getValue() == Enum.yes) {
                stringBuilder.append(createTextFeed(key) + "|");
            }
        }
        L.e("Result = " + stringBuilder.toString());
    }

    private String createTextFeed(int i) {
        String[] stringArray = context.getResources().getStringArray(R.array.text_array);
        return stringArray[i];
    }


}
