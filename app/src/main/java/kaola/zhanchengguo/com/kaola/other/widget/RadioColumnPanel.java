package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Radio;

/**
 * Created by Administrator on 2016/6/13.
 */
public class RadioColumnPanel extends LinearLayout {

    public RadioColumnPanel(Context context, Radio radio ) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);

        setRadioColumn(radio);
    }

    public RadioColumnPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);
    }

    public void initViews(Context context )
    {
        inflate(context, R.layout.radio_column_panel,this);
    }

    public void setRadioColumn(Radio radio)
    {
        List<R_Special> rSpecialList = radio.getDataList();

        RadioColumnLayout radioColumnLayout = new RadioColumnLayout(getContext(),rSpecialList);
        radioColumnLayout.setPadding(0,30,0,0);
        addView(radioColumnLayout);

    }

}
