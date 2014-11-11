package in.srain.cube.sample.ui.activity;

import android.os.Bundle;
import in.srain.cube.app.XActivity;
import in.srain.cube.sample.R;
import in.srain.cube.sample.app.CubeDemoApplication;
import in.srain.cube.sample.ui.fragment.HomeFragment;

public class BannerActivity extends XActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        addFragment(HomeFragment.class, null);
        CubeDemoApplication.instance.test();
    }

    protected String getCloseWarning() {
        return "Tap back to exit";
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragment;
    }
}