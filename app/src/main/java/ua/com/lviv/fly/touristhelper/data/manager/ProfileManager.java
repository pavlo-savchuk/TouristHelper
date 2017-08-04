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

    private List<Enum> data = new ArrayList<>();
    private List<Enum> delete = new ArrayList<>();
    private List<Integer> deleteTest = new ArrayList<>();
    private Map<Integer, Enum> mapData = new HashMap<>();

    public ProfileManager() {
        this.pref = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        this.context = TemplateApplication.getSharedContext();
    }


    public void calculateScore() {
        createTestData();
        calculate();

        mapData.keySet().removeAll(deleteTest);
        createReport();
        L.e("Data test 1= " + mapData.toString());

    }

    private void calculate() {
        for (int l = 1; l <= 20; l++) {
            getPrefPoint(l);
        }
        L.e("Data test weee= " + mapData.get(1));
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
        if (data.get(1) == Enum.yes && data.get(1) == Enum.yes) {
            delete.add(data.get(1));
        }
        if (data.get(7) == Enum.yes && data.get(13) == Enum.yes) {
            delete.add(data.get(7));
        }
        if (data.get(2) == Enum.yes && data.get(11) == Enum.yes) {
            delete.add(data.get(2));
        }
        if (data.get(2) == Enum.yes && data.get(11) == Enum.yes) {
            delete.add(data.get(2));
        }
        if (data.get(6) == Enum.yes && data.get(11) == Enum.yes) {
            delete.add(data.get(12));
        }

        if (data.get(4) == Enum.yes && data.get(12) == Enum.yes) {
            delete.add(data.get(12));
        }

        if (data.get(8) == Enum.yes && data.get(15) == Enum.yes) {
            delete.add(data.get(12));
        }

        if (data.get(9) == Enum.yes && data.get(18) == Enum.yes) {
            delete.add(data.get(12));
        }

        if (data.get(9) == Enum.yes && data.get(18) == Enum.yes) {
            delete.add(data.get(12));
        }

        if (data.get(9) == Enum.yes && data.get(18) == Enum.yes) {
            delete.add(data.get(12));
        }

    }

    private void createTestData() {
        deleteTest.add(1);
        deleteTest.add(2);
        deleteTest.add(3);
        deleteTest.add(4);
        deleteTest.add(5);
        deleteTest.add(6);
        deleteTest.add(7);
        deleteTest.add(8);
        deleteTest.add(9);

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
