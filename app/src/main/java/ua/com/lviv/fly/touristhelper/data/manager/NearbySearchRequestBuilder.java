package ua.com.lviv.fly.touristhelper.data.manager;

import com.google.gson.reflect.TypeToken;
import com.ls.http.base.BaseRequest;
import com.ls.http.base.BaseRequestBuilder;
import com.ls.util.L;


import java.lang.reflect.Type;
import java.util.List;

import ua.com.lviv.fly.touristhelper.ProjectConfig;
import ua.com.lviv.fly.touristhelper.data.ResponseVO;
import ua.com.lviv.fly.touristhelper.model.Model;

/**
 * Created on 22.05.2015.
 */
public class NearbySearchRequestBuilder extends BaseRequestBuilder {

    private String pageId;

    public NearbySearchRequestBuilder() {
        setRequestMethod(BaseRequest.RequestMethod.GET);
        setResponseFormat(BaseRequest.ResponseFormat.JSON);
        Type listType = new TypeToken<ResponseVO>() {
        }.getType();
        setResponseClassSpecifier(listType);
        L.e("getPath() = " + getPath());
        setRequestUri(getPath());
    }

    private String getPath() {
//        return ProjectConfig.BASE_URL + "/nearbysearch/json?location=49.832,24.01678&radius=5000&types=food|cafe&keyword=vegetarian&key=" + ProjectConfig.API_KEY;
        return ProjectConfig.BASE_URL + Model.instance().getOptionManager().getUrl() + ProjectConfig.API_KEY;
    }

    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=49.8323,24.0120&radius=500&types=bank&key=AIzaSyDcjBH-d5PcKPAAzt683TFT6h30t6YwVNY
}
