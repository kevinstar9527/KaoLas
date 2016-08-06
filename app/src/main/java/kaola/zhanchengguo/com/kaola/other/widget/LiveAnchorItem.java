package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class LiveAnchorItem extends LinearLayout {

    private ImageView imageView,ivgender;
    private TextView tvname,tvzan;

    public LiveAnchorItem(Context context , LiveSpecial liveSpecial) {
        super(context);

        initViews(context);

        setAnchors(liveSpecial);
    }


    public LiveAnchorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context) {

        inflate(context, R.layout.live_anchor_item,this);

        imageView = (ImageView) findViewById(R.id.live_item_iv);
        ivgender = (ImageView) findViewById(R.id.live_item_gender_iv);

        tvname = (TextView) findViewById(R.id.live_item_name_tv);
        tvzan = (TextView) findViewById(R.id.live_item_zan_tv);
    }

    /**
     * 设置显示内容
     * @param special
     */
    public void setAnchors(LiveSpecial special) {


        ImageLoader.getInstance().displayImage(special.getAvatar(),imageView, ImageUtil.getCircleOptions());

        tvname.setText(special.getNickName());

        switch (special.getGender())
        {
            case 0:
                ivgender.setImageResource(R.drawable.man);
                break;
            case 1:
                ivgender.setImageResource(R.drawable.woman);
                break;
            case 3:
                ivgender.setVisibility(INVISIBLE);
                break;
        }

        if(special.getLikedNum() > 99)
        {
            tvzan.setText("99w+");
        }else
        {
            tvzan.setText(special.getLikedNum());
        }


    }
}
