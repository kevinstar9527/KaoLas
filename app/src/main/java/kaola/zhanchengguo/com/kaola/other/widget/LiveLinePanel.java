package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Live;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;

/**
 * Created by Administrator on 2016/6/8.
 */
public class LiveLinePanel extends LinearLayout {

    private TextView tvTitle;

    public LiveLinePanel(Context context, Live live) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setLiveLine(live);

    }


    public LiveLinePanel(Context context, AttributeSet attrs) {

        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);
    }

    private void initViews(Context context) {

        inflate(context, R.layout.live_line_panel,this);
        tvTitle = (TextView) findViewById(R.id.live_panel_title_tv);

    }

    /**
     * 显示数据
     * @param live
     */
    public void setLiveLine(Live live )
    {
        tvTitle.setText(live.getName());

        List<LiveSpecial> dataList = live.getDataList();

        if(dataList.size() / 3 == 2)
        {
            List<LiveSpecial> list1 = dataList.subList(0,3);
            LiveLineLayout liveLineLayout1  = new LiveLineLayout(getContext(),list1);
            liveLineLayout1.setPadding(0,30,0,30);

            List<LiveSpecial> list2 = dataList.subList(3,6);
            LiveLineLayout liveLineLayout2  = new LiveLineLayout(getContext(),list2);
            liveLineLayout2.setPadding(0,30,0,0);

            addView(liveLineLayout1);
            addView(liveLineLayout2);
        }
    }
}
