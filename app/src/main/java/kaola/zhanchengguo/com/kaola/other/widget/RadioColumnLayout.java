package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/8.
 */
public class RadioColumnLayout extends LinearLayout {

    private RadioColumnItem rci1,rci2,rci3;

    public RadioColumnLayout(Context context , List<R_Special> list) {
        super(context);

        initViews(context);

        setSpecialList(list);
    }
    public RadioColumnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    private void initViews(Context context) {

        inflate(context, R.layout.radio_column_layout,this);

        rci1 = (RadioColumnItem) findViewById(R.id.radio_layout_rci1);
        rci2 = (RadioColumnItem) findViewById(R.id.radio_layout_rci2);
        rci3 = (RadioColumnItem) findViewById(R.id.radio_layout_rci3);
    }


    /**
     * 设置显示内容
     * @param list
     */
    public void setSpecialList(List<R_Special> list)
    {
        rci1.setRspecial(list.get(0));
        rci2.setRspecial(list.get(1));
        rci3.setRspecial(list.get(2));
    }


}
