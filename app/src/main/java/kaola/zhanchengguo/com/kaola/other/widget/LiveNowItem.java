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
 * Created by Administrator on 2016/6/22.
 */
public class LiveNowItem extends LinearLayout {

    private ImageView ivcontent,ivimag,ivgender,ivvanchor;
    private TextView tvprogramName,tvcomperes,tvonLineNum,tvprogramLikedNum;

    public LiveNowItem(Context context, LiveSpecial liveSpecial) {
        super(context);

        initViews(context);

        setLiveSpecial(liveSpecial);
    }

    public LiveNowItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context,R.layout.live_now_special,this);

        ivcontent = (ImageView) findViewById(R.id.live_now_conten_iv);
        ivimag = (ImageView) findViewById(R.id.live_img_iv);
        ivgender = (ImageView) findViewById(R.id.live_gender_iv);
        ivvanchor = (ImageView) findViewById(R.id.live_vanchor_iv);

        tvprogramName = (TextView) findViewById(R.id.live_programname_tv);
        tvcomperes = (TextView) findViewById(R.id.live_comperes_tv);
        tvonLineNum = (TextView) findViewById(R.id.live_onlinenum_tv);
        tvprogramLikedNum = (TextView) findViewById(R.id.live_likenum_tv);
    }

    public void setLiveSpecial(LiveSpecial liveSpecial)
    {
        ImageLoader.getInstance().displayImage(liveSpecial.getProgramPic(),ivcontent, ImageUtil.getDefalutOptions());

        ImageLoader.getInstance().displayImage(liveSpecial.getProgramPic(),ivimag, ImageUtil.getCircleOptions());

        switch (liveSpecial.getIsVanchor()) {
            case 0:
                ivvanchor.setVisibility(INVISIBLE);
                break;
            case 1:
                ivvanchor.setImageResource(R.drawable.v_anchor);
                break;
        }

        switch (liveSpecial.getGender()) {
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

        tvprogramName.setText(liveSpecial.getProgramName());

        tvcomperes.setText(liveSpecial.getComperes());

        tvonLineNum.setText(liveSpecial.getOnLineNum() + "");

        tvprogramLikedNum.setText(liveSpecial.getProgramLikedNum() + "");
    }
}
