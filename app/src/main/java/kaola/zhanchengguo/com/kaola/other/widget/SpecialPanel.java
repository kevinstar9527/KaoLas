package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpecialPanel extends LinearLayout {

    private TextView tvTitle,tvMore;

    public SpecialPanel(Context context, Recommend recommend) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setRecommend(recommend);

    }


    public SpecialPanel(Context context, AttributeSet attrs) {

        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);
    }

    private void initViews(Context context) {

        inflate(context, R.layout.widget_special_panel,this);
        tvTitle = (TextView) findViewById(R.id.special_panel_title_tv);
        tvMore = (TextView) findViewById(R.id.special_panel_more_tv);
    }

    /**
     * 显示数据
     * @param recommend
     */
    public void setRecommend(Recommend recommend)
    {
        tvTitle.setText(recommend.getName());

        int hasmore = recommend.getHasmore();
        if(hasmore == 0)
        {
            tvMore.setVisibility(GONE);
        }

        List<Special> dataList = recommend.getDataList();

        if(dataList.size() == 3)
        {
            SpecialLayout specialLayout = new SpecialLayout(getContext() , dataList);
            specialLayout.setPadding(0,40,0,0);
            addView(specialLayout);

        }else if(dataList.size() / 3 == 2)
        {
            List<Special> list1 = dataList.subList(0,3);
            SpecialLayout specialLayout1 = new SpecialLayout(getContext() , list1);
            specialLayout1.setPadding(0,40,0,15);

            List<Special> list2 = dataList.subList(3,6);
            SpecialLayout specialLayout2 = new SpecialLayout(getContext() , list2);
            specialLayout2.setPadding(0,15,0,0);

            addView(specialLayout1);
            addView(specialLayout2);
        }
    }
}
