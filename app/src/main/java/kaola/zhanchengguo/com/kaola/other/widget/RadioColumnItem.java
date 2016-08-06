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
 * Created by Administrator on 2016/6/13.
 */
public class RadioColumnItem extends RelativeLayout {

    private ImageView ivImag,ivPlay;
    private TextView tvBuname,tvRname;

    public RadioColumnItem(Context context, R_Special rspecial) {
        super(context);

        initViews(context);

        setRspecial(rspecial);
    }

    public RadioColumnItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        initViews(context);
    }

    public void initViews(Context context )
    {
        inflate(context , R.layout.radio_column_item,this);

        ivImag = (ImageView) findViewById(R.id.radio_item_content_iv);
        ivPlay = (ImageView) findViewById(R.id.radio_item_play_iv);

        tvBuname = (TextView) findViewById(R.id.radio_item_bunname_tv);
        tvRname = (TextView) findViewById(R.id.radio_item_rname_tv);
    }

    public void setRspecial(R_Special rspecial)
    {
        ImageLoader.getInstance().displayImage(rspecial.getPic(),ivImag, ImageUtil.getDefalutOptions());

        tvRname.setText(rspecial.getRname());
        tvBuname.setText(rspecial.getAlbumName());

        switch (rspecial.getRtype())
        {
            case 0:
                ivPlay.setVisibility(INVISIBLE);
                break;
            case 11:
                break;
        }

    }

}
