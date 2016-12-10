package ua.com.lviv.fly.touristhelper.data;

import android.os.Bundle;

import com.ls.http.base.BaseRequest;
import com.ls.http.base.ResponseData;
import com.ls.http.base.client.LSClient;
import com.ls.util.L;

import java.util.List;

/**
 * Created on 22.05.2015.
 */
public class StubItemManager extends BaseItemManager<ResponseVO, Bundle, String> {

    private final static String PAGE_ID_KEY = "page_ID";
    private final static String TAG_PREFIX = "stub_item_id:";
    private ResponseVO dao;

    public StubItemManager(LSClient client) {
        super(client);
        dao = new ResponseVO();
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
    protected ResponseVO readResponseFromRequest(BaseRequest request, ResponseData data, String tag) {
        L.e("Test =" + data.toString());
        ResponseVO items = (ResponseVO) data.getData();
        L.e("Weeeeeee =" + items);
        return items;
    }

    @Override
    protected boolean storeResponse(ResponseVO response, String tag) {
        return false;
    }

    @Override
    protected ResponseVO restoreResponse(String tag) {
        return null;
    }

}
