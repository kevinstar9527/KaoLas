package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Radio;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RadioAnchorPanel extends LinearLayout {

    public RadioAnchorPanel(Context context, Radio radio) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setAnchors(radio);
    }



    public RadioAnchorPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.radio_anchor_panel,this);
    }

    public void setAnchors(Radio radio)
    {
        List<R_Special> rSpecialList = radio.getDataList();

        RadioAnchorLayout radioAnchorLayout = new RadioAnchorLayout(getContext(),rSpecialList);
        radioAnchorLayout.setPadding(0,30,0,0);
        addView(radioAnchorLayout);
    }

}
