package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RadioAnchorItem extends LinearLayout {

    private ImageView imageView,ivgender;
    private TextView tvname,tvdesc;

    public RadioAnchorItem(Context context , R_Special rSpecials) {
        super(context);

        initViews(context);

        setRadioAnchors(rSpecials);
    }


    public RadioAnchorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context) {

        inflate(context, R.layout.radio_anchor_item,this);

        imageView = (ImageView) findViewById(R.id.radio_anchor_item_iv);
        ivgender  = (ImageView) findViewById(R.id.radio_anchor_item_gender_iv);
        tvname = (TextView) findViewById(R.id.radio_anchor_item_name_tv);
        tvdesc = (TextView) findViewById(R.id.radio_anchor_item_desc_tv);
    }

    /**
     * 设置显示内容
     * @param rSpecials
     */
    public void setRadioAnchors(R_Special rSpecials) {

        ImageLoader.getInstance().displayImage(rSpecials.getAvatar(),imageView,ImageUtil.getCircleOptions());

        tvname.setText(rSpecials.getNickName());
        tvdesc.setText(rSpecials.getDesc());

        switch (rSpecials.getGender()){
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


    }
}
