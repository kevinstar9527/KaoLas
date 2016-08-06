package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Radio;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/12.
 */
public class AnchorPanel extends LinearLayout {

    private TextView tvTitle;

    public AnchorPanel(Context context, Recommend anchor) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,30,30,30);

        initViews(context);

        setAnchors(anchor);
    }



    public AnchorPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,30,30,30);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.anchor_panel,this);

        tvTitle = (TextView) findViewById(R.id.anchor_panel_title_tv);
    }

    public void setAnchors(Recommend anchor)
    {
        tvTitle.setText(anchor.getName());

        List<Special> specialList = anchor.getDataList();

        AnchorLayout anchorLayout = new AnchorLayout(getContext(),specialList);
        anchorLayout.setPadding(0,30,0,0);
        addView(anchorLayout);

    }

}
