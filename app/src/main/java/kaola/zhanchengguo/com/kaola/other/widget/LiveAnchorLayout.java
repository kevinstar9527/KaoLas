package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;

/**
 * Created by Administrator on 2016/6/12.
 */
public class LiveAnchorLayout extends LinearLayout {

    private LiveAnchorItem lai1,lai2,lai3;

    public LiveAnchorLayout(Context context, List<LiveSpecial> list) {
        super(context);

        initViews(context);

        setLiveAnchorList(list);
    }

    public LiveAnchorLayout(Context context, AttributeSet attrs) {

        super(context, attrs);

        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context,R.layout.live_anchor_layout,this);
        lai1 = (LiveAnchorItem) findViewById(R.id.live_anchor_layout_lal1);
        lai2 = (LiveAnchorItem) findViewById(R.id.live_anchor_layout_lal2);
        lai3 = (LiveAnchorItem) findViewById(R.id.live_anchor_layout_lal3);

    }

    /**
     * 设置显示内容
     */
    public void setLiveAnchorList(List<LiveSpecial> list)
    {

        lai1.setAnchors(list.get(0));
        lai2.setAnchors(list.get(1));
        lai3.setAnchors(list.get(2));
    }
}
