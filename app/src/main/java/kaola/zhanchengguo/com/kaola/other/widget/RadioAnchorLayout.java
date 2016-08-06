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
public class RadioAnchorLayout extends LinearLayout {

    private RadioAnchorItem rai1,rai2,rai3;

    public RadioAnchorLayout(Context context, List<R_Special> list) {
        super(context);

        initViews(context);

        setRadioAnchorList(list);
    }

    public RadioAnchorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.radio_anchor_layout,this);

        rai1 = (RadioAnchorItem) findViewById(R.id.radio_anchor_layout_rai1);
        rai2 = (RadioAnchorItem) findViewById(R.id.radio_anchor_layout_rai2);
        rai3 = (RadioAnchorItem) findViewById(R.id.radio_anchor_layout_rai3);

    }

    /**
     * 设置显示内容
     */
    public void setRadioAnchorList(List<R_Special> list)
    {

        rai1.setRadioAnchors(list.get(0));
        rai2.setRadioAnchors(list.get(1));
        rai3.setRadioAnchors(list.get(2));
    }
}
