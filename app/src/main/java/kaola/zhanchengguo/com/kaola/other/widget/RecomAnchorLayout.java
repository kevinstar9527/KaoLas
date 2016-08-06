package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RecomAnchorLayout extends LinearLayout {

    private RecomAnchorItem ai1,ai2,ai3;

    public RecomAnchorLayout(Context context, List<Special> list) {
        super(context);

        initViews(context);

        setSpecialList(list);
    }

    public RecomAnchorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.widget_anchor_layout,this);

        ai1 = (RecomAnchorItem) findViewById(R.id.anchor_layout_ai1);
        ai2 = (RecomAnchorItem) findViewById(R.id.anchor_layout_ai2);
        ai3 = (RecomAnchorItem) findViewById(R.id.anchor_layout_ai3);

    }

    /**
     * 设置显示内容
     */
    public void setSpecialList(List<Special> list)
    {

        ai1.setAnchors(list.get(0));
        ai2.setAnchors(list.get(1));
        ai3.setAnchors(list.get(2));
    }
}
