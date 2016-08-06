package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 *
 * Created by Administrator on 2016/6/13.
 */
public class RadioSelectionItem extends RelativeLayout{

    private ImageView ivRadio,ivPlay;

    private TextView tvTitle,tvConten,tvListen,tvFollow;

    public RadioSelectionItem(Context context, R_Special rspecial) {
        super(context);

        initViews(context);

        setRspecial(rspecial);
    }

    public RadioSelectionItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context)
    {
        inflate(context, R.layout.radio_selection_item,this);

        ivRadio = (ImageView) findViewById(R.id.radio_item_tiv);
        ivPlay = (ImageView) findViewById(R.id.radio_item_play);

        tvTitle = (TextView) findViewById(R.id.radio_item_title_tv);
        tvConten = (TextView) findViewById(R.id.radio_item_content_tv);
        tvListen = (TextView) findViewById(R.id.radio_item_listen_tv);
        tvFollow = (TextView) findViewById(R.id.radio_item_follow_tv);
    }

    public void setRspecial(R_Special rspecial)
    {
        ImageLoader.getInstance().displayImage(rspecial.getPic(),ivRadio, ImageUtil.getDefalutOptions());

        tvTitle.setText(rspecial.getRname());
        tvConten.setText(rspecial.getDes());
        tvListen.setText(rspecial.getListenNum()+"");
        tvFollow.setText(rspecial.getFollowedNum()+"");

        switch (rspecial.getRtype())
        {
            case 1:
                ivPlay.setVisibility(INVISIBLE);
                break;
            case 3:

                break;
        }
    }
}
