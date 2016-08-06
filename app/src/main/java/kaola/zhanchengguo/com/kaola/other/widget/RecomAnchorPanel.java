package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RecomAnchorPanel extends LinearLayout {

    public RecomAnchorPanel(Context context, Recommend recommend) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setRecommend(recommend);
    }



    public RecomAnchorPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.widget_anchor_panel,this);
    }

    public void setRecommend(Recommend recommend)
    {
        List<Special> dataList = recommend.getDataList();

        LogUtil.w("dataList.seze = " + dataList.size() );

        RecomAnchorLayout al = new RecomAnchorLayout(getContext(),dataList);
        al.setPadding(0,30,0,0);
        addView(al);
    }

}
