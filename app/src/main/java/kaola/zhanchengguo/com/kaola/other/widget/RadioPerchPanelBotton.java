package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.adapter.RadioGridAdapter;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Deeper;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Radio;

/**
 * Created by Administrator on 2016/6/13.
 */
public class RadioPerchPanelBotton extends LinearLayout {

    private RecyclerView recyclerView;

    private RadioGridAdapter gridAdapter;

    public RadioPerchPanelBotton(Context context, Radio radio) {
        super(context);

        initViews(context);

        initDatas(radio);
    }

    public RadioPerchPanelBotton(Context context, AttributeSet attrs) {

        super(context, attrs);

        initViews(context);


    }

    public void initViews(Context context)
    {
        inflate(context,R.layout.radio_perch_panel_botton,this);
        recyclerView = (RecyclerView) findViewById(R.id.radio_perch_panel_botton_rl);

        //使用表格布局
        GridLayoutManager manager = new GridLayoutManager(context,4);
        recyclerView.setLayoutManager(manager);
    }

    public void initDatas(Radio radio)
    {
        List<R_Special> radioList = radio.getDataList();
        final List<R_Deeper> deeperList = radioList.get(1).getDataList();

        final List<R_Deeper> list = deeperList.subList(0,7);

        gridAdapter = new RadioGridAdapter(getContext(),list);
        recyclerView.setAdapter(gridAdapter);

        gridAdapter.setOnItemClickListener(new RadioGridAdapter.mItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                if(gridAdapter.getList().size() == postion)
                {
                    if(gridAdapter.getList().size() == deeperList.size())
                    {
                        gridAdapter.setList(list);
                    }else
                    {
                        gridAdapter.setList(deeperList);
                    }
                    gridAdapter.notifyDataSetChanged();
                }
            }
        });



    }


}
