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
public class RadioSelectionPanel extends LinearLayout{


    public RadioSelectionPanel(Context context, Radio radio ) {
        super(context);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);

        setRadioSelection(radio);
    }

    public RadioSelectionPanel(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        setPadding(30,15,30,15);
        initViews(context);
    }

    public void initViews(Context context )
    {
        inflate(context, R.layout.radio_selection_panel,this);

    }


    public void setRadioSelection(Radio radio)
    {
        List<R_Special> rSpecialList = radio.getDataList();

        for (int i = 0 ; i < rSpecialList.size() ; i ++)
        {
            R_Special rSpecial = new R_Special();
            rSpecial = rSpecialList.get(i);
            RadioSelectionItem selectionItem = new RadioSelectionItem(getContext(),rSpecial);
            addView(selectionItem);
            selectionItem.setPadding(0,10,0,0);
        }
        findViewById(R.id.item_line).setVisibility(GONE);
    }
}
