package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;

/**
 * Created by Administrator on 2016/6/23.
 */
public class LiveLineLayout extends LinearLayout {

    private LiveLineItem lli1,lli2,lli3;

    public LiveLineLayout(Context context, List<LiveSpecial> liveSpecials) {
        super(context);

        initViews(context);

        setLiveLines(liveSpecials);
    }

    public LiveLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.live_line_layout,this);

        lli1 = (LiveLineItem) findViewById(R.id.live_line_layout_lli1);
        lli2 = (LiveLineItem) findViewById(R.id.live_line_layout_lli2);
        lli3 = (LiveLineItem) findViewById(R.id.live_line_layout_lli3);
    }

    public void setLiveLines(List<LiveSpecial> list)
    {
        lli1.setLiveLineItem(list.get(0));
        lli2.setLiveLineItem(list.get(1));
        lli3.setLiveLineItem(list.get(2));
    }
}
