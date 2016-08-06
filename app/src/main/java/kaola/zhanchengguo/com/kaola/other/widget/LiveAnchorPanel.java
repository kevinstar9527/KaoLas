package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Live;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class LiveAnchorPanel extends LinearLayout {

    public LiveAnchorPanel(Context context, Live live) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setLive(live);
    }



    public LiveAnchorPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.live_anchor_panel,this);
    }

    public void setLive(Live live)
    {
        List<LiveSpecial> dataList = live.getDataList();

        LogUtil.w("dataList.seze = " + dataList.size() );

        LiveAnchorLayout al = new LiveAnchorLayout(getContext(),dataList);
        al.setPadding(0,30,0,0);
        addView(al);
    }

}
