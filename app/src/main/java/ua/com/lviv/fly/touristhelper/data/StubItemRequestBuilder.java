package ua.com.lviv.fly.touristhelper.data;

import com.google.gson.reflect.TypeToken;
import com.ls.http.base.BaseRequest;
import com.ls.http.base.BaseRequestBuilder;


import java.lang.reflect.Type;
import java.util.List;

/**
 * Created on 22.05.2015.
 */
public class StubItemRequestBuilder extends BaseRequestBuilder {

    private String pageId;

    public StubItemRequestBuilder(String pageId) {
        this.pageId = pageId;
        setRequestMethod(BaseRequest.RequestMethod.GET);
        setResponseFormat(BaseRequest.ResponseFormat.JSON);
        Type listType = new TypeToken<List<ResponseVO>>() {
        }.getType();
        setResponseClassSpecifier(listType);
        setRequestURL(getPath());
    }

    protected String getPath() {
        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=49.832,24.01678&radius=5000&types=food|cafe&keyword=vegetarian&key=AIzaSyDcjBH-d5PcKPAAzt683TFT6h30t6YwVNY";
    }
}
