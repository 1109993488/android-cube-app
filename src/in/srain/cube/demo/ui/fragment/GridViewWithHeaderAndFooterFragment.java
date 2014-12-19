package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import in.srain.cube.demo.R;
import in.srain.cube.demo.data.ImageListData;
import in.srain.cube.demo.data.ImageListItem;
import in.srain.cube.demo.datamodel.DemoRequestData;
import in.srain.cube.demo.ui.viewholders.ImageListItemMiddleImageViewHolder;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.request.CacheAbleRequest;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class GridViewWithHeaderAndFooterFragment extends TitleBaseFragment {

    private ImageLoader mImageLoader;
    private ListViewDataAdapter<ImageListItem> mAdapter;
    private PtrClassicFrameLayout mPtrFrame;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mImageLoader = ImageLoaderFactory.create(getContext()).tryToAttachToContainer(this);

        setHeaderTitle(R.string.cube_demo_grid_view_with_header_and_footer);
        View view = inflater.inflate(R.layout.fragment_grid_view_with_header_and_footer, null);

        final GridViewWithHeaderAndFooter gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.grid_view_with_header_and_footer);

        View headerView = inflater.inflate(R.layout.grid_view_header, null);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPtrFrame.autoRefresh();
            }
        });

        View footerView = inflater.inflate(R.layout.grid_view_footer, null);
        gridView.addHeaderView(headerView);
        gridView.addFooterView(footerView);

        mAdapter = new ListViewDataAdapter<ImageListItem>();
        mAdapter.setViewHolderClass(this, ImageListItemMiddleImageViewHolder.class, mImageLoader);
        gridView.setAdapter(mAdapter);
        gridView.setNumColumns(2);

        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.grid_view_with_header_and_footer_ptr_frame);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                requestData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }
        });
        return view;
    }

    private void requestData() {

        DemoRequestData.getImageList(new DemoRequestData.ImageListDataHandler() {

            @Override
            public void onData(ImageListData data, CacheAbleRequest.ResultType type, boolean outOfDate) {
                mAdapter.getDataList().clear();
                mAdapter.getDataList().addAll(data.imageList);
                mAdapter.notifyDataSetChanged();
                mPtrFrame.refreshComplete();
            }
        });
    }

}

