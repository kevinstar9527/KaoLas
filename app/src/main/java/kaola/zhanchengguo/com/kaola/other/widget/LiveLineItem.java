package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/23.
 */
public class LiveLineItem extends RelativeLayout {

    private ImageView ivcontent,ivvanchor;
    private TextView tvlike,tvprogramName,tvcomperes;

    public LiveLineItem(Context context, LiveSpecial liveSpecial) {
        super(context);

        initViews(context);

        setLiveLineItem(liveSpecial);
    }

    public LiveLineItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    public  void initViews(Context context)
    {
        inflate(context, R.layout.live_line_item,this);

        ivcontent = (ImageView) findViewById(R.id.live_line_item_content_iv);
        ivvanchor = (ImageView) findViewById(R.id.live_line_item_vanchor_iv);

        tvlike = (TextView) findViewById(R.id.live_line_item_like_tv);
        tvprogramName = (TextView) findViewById(R.id.live_line_item_programName_tv);
        tvcomperes = (TextView) findViewById(R.id.live_line_item_comperes_tv);
    }

    public void setLiveLineItem(LiveSpecial liveSpecial)
    {
        ImageLoader.getInstance().displayImage(liveSpecial.getProgramPic(),ivcontent, ImageUtil.getDefalutOptions());

        switch (liveSpecial.getIsVanchor())
        {
            case 0:
                ivvanchor.setVisibility(INVISIBLE);
                break;
            case 1:
                ivvanchor.setImageResource(R.drawable.v_anchor);
                break;
        }

        tvlike.setText(liveSpecial.getProgramLikedNum()+"");

        tvcomperes.setText(liveSpecial.getComperes());

        String programName = "占 " + liveSpecial.getProgramName();

        LogUtil.w("liveSpecial.getProgramName() = " + programName);

        SpannableString spanString = new SpannableString(programName);
        Drawable d = getResources().getDrawable(R.drawable.live_replay);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvprogramName.append(spanString);
        tvprogramName.setEllipsize(TextUtils.TruncateAt.END);

    }
}
