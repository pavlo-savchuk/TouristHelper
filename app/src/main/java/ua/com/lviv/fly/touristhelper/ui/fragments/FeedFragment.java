package ua.com.lviv.fly.touristhelper.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ls.util.L;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.JsonVO;
import ua.com.lviv.fly.touristhelper.model.Model;
import ua.com.lviv.fly.touristhelper.ui.activity.DetailsActivity;
import ua.com.lviv.fly.touristhelper.ui.activity.TestActivity;
import ua.com.lviv.fly.touristhelper.ui.adapters.FeedAdapter;

public class FeedFragment extends Fragment {
    private FeedAdapter adapter;
    private ArrayList<JsonVO> feed1;
    private ArrayList<JsonVO> feed2;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feed1 = parseJson("dental_clinics.json");
        feed2 = parseJson("museums.json");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new FeedAdapter(getContext());
        adapter.setData(feed1);

        View inflate = inflater.inflate(R.layout.fragment_feed, container, false);

        ListView listView = (ListView) inflate.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JsonVO item = (JsonVO) adapter.getItem(i);
                Gson gson = Model.instance().getGson();
                String s = gson.toJson(item);
                L.e("S 1 = " + s);
                L.e("S 2= " + gson.fromJson(s, JsonVO.class));


                DetailsActivity.startThisActivity(getContext(), gson.toJson(item));
            }
        });
        return inflate;
    }


    public String loadJSONFromAsset(String jsonName) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(jsonName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private ArrayList<JsonVO> parseJson(String jsonName) {
        Gson gson = Model.instance().getGson();
        ArrayList<JsonVO> list = gson.fromJson(loadJSONFromAsset(jsonName), new TypeToken<ArrayList<JsonVO>>() {
        }.getType());
        L.e("Json  " + jsonName + " = " + list.toString());
        return list;
    }
}
