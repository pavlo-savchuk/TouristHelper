package ua.com.lviv.fly.touristhelper.data;

import android.os.Bundle;

import com.ls.http.base.BaseRequest;
import com.ls.http.base.ResponseData;
import com.ls.http.base.client.LSClient;
import com.ls.util.L;

import java.util.ArrayList;
import java.util.List;

import ua.com.lviv.fly.touristhelper.data.dao.StubItemDAO;
import ua.com.lviv.fly.touristhelper.data.manager.SynchronizedDatabaseManager;

/**
 * Created on 22.05.2015.
 */
public class StubItemManager extends BaseItemManager<List<ResultsVO>, Bundle, String> {

    private final static String PAGE_ID_KEY = "page_ID";
    private final static String TAG_PREFIX = "stub_item_id:";
    private StubItemDAO dao;

    public StubItemManager(LSClient client) {
        super(client);
        dao = new StubItemDAO();
    }

    /**
     * @return tag, used to identify request
     */

    @Override
    protected BaseRequest getFetchRequest(LSClient client, Bundle requestParams) {

        StubItemRequestBuilder builder = new StubItemRequestBuilder();
        return builder.create();
    }

    @Override
    protected String getEntityRequestTag(Bundle params) {
        return "";
    }

    @Override
    protected List<ResultsVO> readResponseFromRequest(BaseRequest request, ResponseData data, String tag) {
        L.e("Test =" + data.toString());
        ResponseVO items = (ResponseVO) data.getData();
        L.e("Weeeeeee =" + items.getResults());

        return items.getResults();
    }

    @Override
    protected boolean storeResponse(List<ResultsVO> response, String tag) {
        L.e("Response =" + response);
        dao.deleteAllSafe();
        dao.saveDataSafe(response);
        return true;
    }

    @Override
    protected List<ResultsVO> restoreResponse(String tag) {
        return dao.getAllSafe();
    }



}
