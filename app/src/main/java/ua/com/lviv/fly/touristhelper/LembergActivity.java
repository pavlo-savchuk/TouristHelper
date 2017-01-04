package ua.com.lviv.fly.touristhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ls.http.base.ResponseData;
import com.ls.util.L;

import java.util.List;

import ua.com.lviv.fly.touristhelper.data.BaseItemManager;
import ua.com.lviv.fly.touristhelper.data.ResponseVO;
import ua.com.lviv.fly.touristhelper.data.ResultsVO;
import ua.com.lviv.fly.touristhelper.data.dao.StubItemDAO;
import ua.com.lviv.fly.touristhelper.model.Model;

public class LembergActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lemberg);

        L.e("LembergActivity");
        Model.instance().getStubManager().addDataFetchCompleteListener(new BaseItemManager.OnDataFetchCompleteListener<List<ResultsVO>, String>() {
            @Override
            public void onDataFetchComplete(List<ResultsVO> result, ResponseData data, String requestTag) {
                L.e("onDataFetchComplete");
            }

            @Override
            public void onDataFetchFailed(List<ResultsVO> result, ResponseData data, String requestTag) {
                L.e("onDataFetchFailed");
            }
        });

//        Model.instance().getStubManager().addDataFetchCompleteListener(new ua.com.lviv.fly.touristhelper.data.manager.BaseItemManager.OnDataFetchCompleteListener<List<ResultsVO>, String>() {
//            @Override
//            public void onDataFetchComplete(List<ResultsVO> result, ResponseData data, String requestTag) {
//                L.e("onDataFetchComplete");
//            }
//
//            @Override
//            public void onDataFetchFailed(List<ResultsVO> result, ResponseData data, String requestTag) {
//                L.e("onDataFetchFailed");
//            }
//        });
        Model.instance().getStubManager().fetchData(null);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ResultsVO> allSafe = new StubItemDAO().getAllSafe();
                L.e("allSafe = " + allSafe.toString());
            }
        });


    }
}
