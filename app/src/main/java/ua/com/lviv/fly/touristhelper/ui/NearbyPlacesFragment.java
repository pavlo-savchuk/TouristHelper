package ua.com.lviv.fly.touristhelper.ui;
;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.http.base.ResponseData;
import com.ls.util.L;

import java.util.List;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.data.BaseItemManager;
import ua.com.lviv.fly.touristhelper.data.ResultsVO;
import ua.com.lviv.fly.touristhelper.data.dao.ResultItemDAO;
import ua.com.lviv.fly.touristhelper.model.Model;

public class NearbyPlacesFragment extends Fragment {


    public static NearbyPlacesFragment newInstance() {

        Bundle args = new Bundle();

        NearbyPlacesFragment fragment = new NearbyPlacesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    BaseItemManager.OnDataFetchCompleteListener<List<ResultsVO>, String> listener = new BaseItemManager.OnDataFetchCompleteListener<List<ResultsVO>, String>() {
        @Override
        public void onDataFetchComplete(List<ResultsVO> result, ResponseData data, String requestTag) {
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
        return rootView;
    }

    @Override
    public void onDestroy() {
        Model.instance().getStubManager().removeDataFetchCompleteListener(listener);
        super.onDestroy();
    }
}