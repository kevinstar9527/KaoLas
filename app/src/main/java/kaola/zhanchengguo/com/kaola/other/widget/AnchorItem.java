package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class AnchorItem extends LinearLayout {

    private ImageView imageView, ivgender, ivvanchor, ivLike;
    private TextView tvname, tvreasonordesc;

    public AnchorItem(Context context, Special special) {
        super(context);

        initViews(context);

        setAnchors(special);
    }


    public AnchorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context) {

        inflate(context, R.layout.anchor_item, this);

        imageView = (ImageView) findViewById(R.id.anchor_item_avatar_iv);
        ivgender = (ImageView) findViewById(R.id.anchor_item_gender_iv);
        ivvanchor = (ImageView) findViewById(R.id.anchor_item_vanchor_iv);
        ivLike = (ImageView) findViewById(R.id.anchor_item_like_iv);
        tvname = (TextView) findViewById(R.id.anchor_item_name_tv);
        tvreasonordesc = (TextView) findViewById(R.id.anchor_item_reason_tv);
    }

    /**
     * 设置显示内容
     *
     * @param special
     */
    public void setAnchors(Special special) {

        ImageLoader.getInstance().displayImage(special.getAvatar(), imageView, ImageUtil.getCircleOptions());

        switch (special.getIsVanchor()) {
            case 0:
                ivvanchor.setVisibility(INVISIBLE);
                break;
            case 1:
                ivvanchor.setImageResource(R.drawable.v_anchor);
                break;
        }
        switch (special.getGender()) {
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

        if (special.getRecommendReson().toString() == "") {

            ivLike.setImageResource(R.drawable.heart_grey);

            if (special.getLikedNum() > 990000) {
                tvreasonordesc.setText("99w+");
            } else {
                tvreasonordesc.setText(special.getLikedNum() + "");
            }
        } else
        {
            ivLike.setVisibility(INVISIBLE);
            tvreasonordesc.setText(special.getRecommendReson());
        }

        tvname.setText(special.getNickName());


    }
}
