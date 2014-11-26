package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import in.srain.cube.demo.R;
import in.srain.cube.mints.base.TitleBaseFragment;

public class MoreActionViewFragment extends TitleBaseFragment {

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHeaderTitle("More Action View Demo");

        View view = inflater.inflate(R.layout.fragment_more_action_view, null);
        return view;
    }
}