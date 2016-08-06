package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/12.
 */
public class AnchorLayout extends LinearLayout {

    private AnchorItem ani1,ani2,ani3;

    public AnchorLayout(Context context, List<Special> list) {
        super(context);

        initViews(context);

        setAnchorList(list);
    }

    public AnchorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.anchor_layout,this);

        ani1 = (AnchorItem) findViewById(R.id.anchor_layout_ani1);
        ani2 = (AnchorItem) findViewById(R.id.anchor_layout_ani2);
        ani3 = (AnchorItem) findViewById(R.id.anchor_layout_ani3);

    }

    /**
     * 设置显示内容
     */
    public void setAnchorList(List<Special> list)
    {
        if(list.size() > 2)
        {
            ani1.setAnchors(list.get(0));
            ani2.setAnchors(list.get(1));
            ani3.setAnchors(list.get(2));
        }else
        {
            ani1.setAnchors(list.get(0));
            ani2.setAnchors(list.get(1));
        }
    }
}
