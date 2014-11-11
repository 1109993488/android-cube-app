package in.srain.cube.sample.ui.activity.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import in.srain.cube.sample.R;

public class HeaderBarBase extends RelativeLayout {

    private RelativeLayout mLeftViewContainer;
    private RelativeLayout mRightViewContainer;
    private RelativeLayout mCenterViewContainer;

    public HeaderBarBase(Context context) {
        this(context, null);
    }

    public HeaderBarBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderBarBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(getLayoutId(), this);
        mLeftViewContainer = (RelativeLayout) findViewById(R.id.ly_title_bar_left);
        mCenterViewContainer = (RelativeLayout) findViewById(R.id.ly_title_bar_center);
        mRightViewContainer = (RelativeLayout) findViewById(R.id.ly_title_bar_right);
    }

    protected int getLayoutId() {
        return R.layout.base_header_bar_base;
    }

    public RelativeLayout getLeftViewContainer() {
        return mLeftViewContainer;
    }

    public RelativeLayout getCenterViewContainer() {
        return mCenterViewContainer;
    }

    public RelativeLayout getRightViewContainer() {
        return mRightViewContainer;
    }

    public void setLeftOnClickListener(OnClickListener l) {
        mLeftViewContainer.setOnClickListener(l);
    }

    public void setCenterOnClickListener(OnClickListener l) {
        mCenterViewContainer.setOnClickListener(l);
    }

    public void setRightOnClickListener(OnClickListener l) {
        mRightViewContainer.setOnClickListener(l);
    }
}