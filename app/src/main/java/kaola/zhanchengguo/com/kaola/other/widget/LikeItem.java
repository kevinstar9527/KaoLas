package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class LikeItem extends RelativeLayout {

    private ImageView imageView;
    private TextView title,content,reason;

    public LikeItem(Context context ,Special special) {
        super(context);

        initView(context);
        setLike(special);
    }

    public LikeItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    private void initView(Context context)
    {
        inflate(context,R.layout.widget_like_item,this);

        imageView = (ImageView) findViewById(R.id.like_item_tiv);
        title = (TextView) findViewById(R.id.like_item_title_tv);
        content = (TextView) findViewById(R.id.like_item_content_tv);
        reason = (TextView) findViewById(R.id.like_item_reason_tv);
    }

    public void setLike(Special special)
    {
        ImageLoader.getInstance().displayImage(special.getPic(),imageView, ImageUtil.getDefalutOptions());

        title.setText(special.getRname());
        content.setText(special.getAlbumName());
        reason.setText(special.getDes());

    }
}
