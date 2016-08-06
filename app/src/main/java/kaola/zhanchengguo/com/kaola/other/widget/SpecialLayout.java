package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpecialLayout extends LinearLayout {

    private SpecialItem si1,si2,si3;

    public SpecialLayout(Context context ,List<Special> list) {
        super(context);

        initViews(context);

        setSpecialList(list);
    }
    public SpecialLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    private void initViews(Context context) {

        inflate(context, R.layout.widget_special_layout,this);

        si1 = (SpecialItem) findViewById(R.id.special_layout_si1);
        si2 = (SpecialItem) findViewById(R.id.special_layout_si2);
        si3 = (SpecialItem) findViewById(R.id.special_layout_si3);
    }


    /**
     * 设置显示内容
     * @param list
     */
    public void setSpecialList(List<Special> list)
    {
        si1.setSpecial(list.get(0));
        si2.setSpecial(list.get(1));
        si3.setSpecial(list.get(2));
    }


}
