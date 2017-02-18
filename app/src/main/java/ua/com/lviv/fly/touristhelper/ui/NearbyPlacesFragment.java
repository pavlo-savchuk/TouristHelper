package ua.com.lviv.fly.touristhelper.ui;

;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ls.http.base.ResponseData;
import com.ls.util.L;

import java.util.ArrayList;
import java.util.List;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.BaseItemManager;
import ua.com.lviv.fly.touristhelper.data.ResultsVO;
import ua.com.lviv.fly.touristhelper.data.dao.ResultItemDAO;
import ua.com.lviv.fly.touristhelper.model.Model;
import ua.com.lviv.fly.touristhelper.ui.activity.DetailsActivity;
import ua.com.lviv.fly.touristhelper.ui.adapters.ResultAdapter;

public class NearbyPlacesFragment extends Fragment {
    ListView list;
    ResultAdapter adapter;
    List<ResultsVO> data = new ArrayList<>();

    public static NearbyPlacesFragment newInstance() {

        Bundle args = new Bundle();

        NearbyPlacesFragment fragment = new NearbyPlacesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    BaseItemManager.OnDataFetchCompleteListener<List<ResultsVO>, String> listener = new BaseItemManager.OnDataFetchCompleteListener<List<ResultsVO>, String>() {
        @Override
        public void onDataFetchComplete(List<ResultsVO> result, ResponseData data, String requestTag) {
            adapter.setData(result);
            L.e("onDataFetchComplete");
        }

        @Override
        public void onDataFetchFailed(List<ResultsVO> result, ResponseData data, String requestTag) {
            L.e("onDataFetchFailed");
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Model.instance().getStubManager().addDataFetchCompleteListener(listener);

        Model.instance().getStubManager().fetchData(null);
        adapter = new ResultAdapter(getContext());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nearby_places, container, false);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ResultsVO> allSafe = new ResultItemDAO().getAllSafe();
                L.e("allSafe = " + allSafe.toString());
            }
        });

        list = (ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ResultsVO item = (ResultsVO) adapter.getItem(i);
                L.e("item = " + item.toString());
                DetailsActivity.startThisActivity(getContext(), item.getPlaceId());
            }
        });
        return rootView;
    }

    @Override
    public void onDestroy() {
        Model.instance().getStubManager().removeDataFetchCompleteListener(listener);
        super.onDestroy();
    }


}