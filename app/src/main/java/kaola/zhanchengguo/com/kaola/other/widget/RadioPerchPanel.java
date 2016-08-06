package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Radio;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RadioPerchPanel extends LinearLayout {

    private TextView tv1,tv2,tv3,tv4;

    public RadioPerchPanel(Context context,Radio radio) {
        super(context);

        setPadding(30,20,30,20);
        initView(context);

        setRadio(radio);
    }

    public RadioPerchPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPadding(30,20,30,20);
        initView(context);
    }

    public void initView(Context context)
    {
        inflate(context, R.layout.radio_perch_panel,this);

        tv1 = (TextView) findViewById(R.id.radio_perch_item_name1);
        tv2 = (TextView) findViewById(R.id.radio_perch_item_name2);
        tv3 = (TextView) findViewById(R.id.radio_perch_item_name3);
        tv4 = (TextView) findViewById(R.id.radio_perch_item_name4);

    }

    public void setRadio(Radio radio)
    {

    }
}
