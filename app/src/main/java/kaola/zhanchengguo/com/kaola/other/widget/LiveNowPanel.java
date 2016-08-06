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
 * Created by Administrator on 2016/6/12.
 */
public class LiveNowPanel extends LinearLayout {

    private TextView tvcount;

    public LiveNowPanel(Context context , Live live ) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);

        initViews(context);

        setLive(live);

    }


    public LiveNowPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPadding(30,15,30,15);
        initViews(context);
    }

    private void initViews(Context context)
    {
        inflate(context, R.layout.live_now_panel,this);

        tvcount = (TextView) findViewById(R.id.live_panel_count_tv);

    }

    /**
     * 显示数据
     * @param live
     */
    public void setLive(Live live)
    {
        tvcount.setText("(" + live.getCount() + ")" );

        List<LiveSpecial> liveSpecials = live.getDataList();

        for(int i = 0 ; i < liveSpecials.size() ; i++)
        {
            LiveSpecial liveSpecial = new LiveSpecial();
            liveSpecial = liveSpecials.get(i);
            LiveNowItem liveNowItem  = new LiveNowItem(getContext(),liveSpecial);
            addView(liveNowItem);
            liveNowItem.setPadding(0,10,0,10);
        }
        findViewById(R.id.item_line).setVisibility(GONE);

    }
}
