package ua.com.lviv.fly.touristhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ls.http.base.ResponseData;
import com.ls.util.L;

import java.util.List;

import ua.com.lviv.fly.touristhelper.data.BaseItemManager;
import ua.com.lviv.fly.touristhelper.data.ResultsVO;
import ua.com.lviv.fly.touristhelper.data.dao.ResultItemDAO;
import ua.com.lviv.fly.touristhelper.model.Model;

public class NearbyPlacesActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nearby_places);
        Model.instance().getStubManager().addDataFetchCompleteListener(listener);

        Model.instance().getStubManager().fetchData(null);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ResultsVO> allSafe = new ResultItemDAO().getAllSafe();
                L.e("allSafe = " + allSafe.toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        Model.instance().getStubManager().removeDataFetchCompleteListener(listener);
        super.onDestroy();
    }
}
