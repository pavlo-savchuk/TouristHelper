package ua.com.lviv.fly.touristhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ls.http.base.ResponseData;
import com.ls.util.L;

import ua.com.lviv.fly.touristhelper.data.BaseItemManager;
import ua.com.lviv.fly.touristhelper.data.ResponseVO;
import ua.com.lviv.fly.touristhelper.model.Model;

public class LembergActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lemberg);

        L.e("LembergActivity");
        Model.instance().getStubManager().addDataFetchCompleteListener(new BaseItemManager.OnDataFetchCompleteListener<ResponseVO, String>() {
            @Override
            public void onDataFetchComplete(ResponseVO result, ResponseData data, String requestTag) {
                L.e("onDataFetchComplete");
            }

            @Override
            public void onDataFetchFailed(ResponseVO result, ResponseData data, String requestTag) {
                L.e("onDataFetchFailed");
            }
        });

        Model.instance().getStubManager().fetchData(null);
    }
}
