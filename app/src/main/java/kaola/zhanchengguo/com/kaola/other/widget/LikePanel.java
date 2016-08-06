package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/12.
 */
public class LikePanel extends LinearLayout {


    public LikePanel(Context context , Recommend recommend ) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setRecommend(recommend);

    }


    public LikePanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPadding(30,15,30,15);
        initViews(context);
    }

    private void initViews(Context context)
    {
        inflate(context, R.layout.widget_like_panel,this);

    }

    /**
     * 显示数据
     * @param recommend
     */
    public void setRecommend(Recommend recommend)
    {

        List<Special> list = recommend.getDataList();
        for(int i = 0 ; i < list.size() ; i++)
        {
            Special speical = new Special();
            speical = list.get(i);
            LikeItem item = new LikeItem(getContext(),speical);
            addView(item);
            item.setPadding(0,10,0,10);
        }
        findViewById(R.id.item_line).setVisibility(GONE);

    }
}
