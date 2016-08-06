package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class SASPanel extends LinearLayout {

    private ImageView major,action,shop;

    public SASPanel(Context context, Recommend recommend) {

        super(context);
        setPadding(30,40,30,40);

        initViews(context);
        setRecommend(recommend);
    }

    public SASPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(30,40,30,40);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context , R.layout.widget_sas_panel,this);

        major = (ImageView) findViewById(R.id.sas_special_iv);
        action = (ImageView) findViewById(R.id.sas_action_iv);
        shop = (ImageView) findViewById(R.id.sas_shop_iv);
    }

    /**
     *
     * 显示数据内容
     * @param recommend
     */
    public void setRecommend(Recommend recommend )
    {
        List<Special> dataList = recommend.getDataList();

        ImageLoader.getInstance().displayImage(dataList.get(0).getPic(), major, ImageUtil.getCircleOptions());
        ImageLoader.getInstance().displayImage(dataList.get(1).getPic(), action , ImageUtil.getCircleOptions());
        ImageLoader.getInstance().displayImage(dataList.get(2).getPic(), shop , ImageUtil.getCircleOptions());

    }
}
