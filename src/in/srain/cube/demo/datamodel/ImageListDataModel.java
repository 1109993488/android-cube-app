package in.srain.cube.demo.datamodel;

import in.srain.cube.demo.data.ImageListData;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.event.EventBus;
import in.srain.cube.demo.request.DemoCacheRequest;
import in.srain.cube.request.*;
import in.srain.cube.views.list.ListPageInfo;
import in.srain.cube.views.list.PagedListDataModel;

import java.util.ArrayList;

public class ImageListDataModel extends PagedListDataModel<ImageListItem> {

    public ImageListDataModel() {
        mListPageInfo = new ListPageInfo<ImageListItem>(20);
    }

    @Override
    protected void doQueryData() {

        DemoCacheRequest<ImageListData> request = new DemoCacheRequest<ImageListData>(new CacheAbleRequestDefaultHandler<ImageListData>() {

            @Override
            public ImageListData processOriginData(JsonData jsonData) {
                JsonData data = jsonData.optJson("data");
                ArrayList<JsonData> rawList = data.optJson("list").toArrayList();

                ArrayList<ImageListItem> imageList = new ArrayList<ImageListItem>();
                for (int i = 0; i < rawList.size(); i++) {
                    ImageListItem item = new ImageListItem(rawList.get(i));
                    imageList.add(item);
                }

                ImageListData event = new ImageListData();
                event.success = true;
                event.imageList = imageList;
                event.hasMore = data.optBoolean("has_more");
                return event;
            }

            @Override
            public void onCacheAbleRequestFinish(ImageListData data, CacheAbleRequest.ResultType type, boolean outOfDate) {
                setRequestResult(data.imageList, data.hasMore);
                EventBus.getInstance().post(data);
            }

            @Override
            public void onRequestFail(FailData failData) {
                ImageListData event = new ImageListData();
                EventBus.getInstance().post(event);
            }
        });

        String cacheKey = "api/image-list-first-page";
        boolean disableCache = mListPageInfo.getStart() != 0;
        request.setCacheTime(3).setCacheKey(cacheKey).setDisableCache(disableCache);

        RequestData requestData = request.getRequestData();
        requestData.addQueryData("start", mListPageInfo.getStart());
        requestData.addQueryData("num", mListPageInfo.getNumPerPage());
        request.getRequestData().setRequestUrl(ConfigData.API_URL_PRE + "/image-list");
        request.send();
    }
}
