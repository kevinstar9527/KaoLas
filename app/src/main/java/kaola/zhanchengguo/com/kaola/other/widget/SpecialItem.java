package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.JumpManager;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpecialItem extends RelativeLayout {

    private ImageView ivContent,ivPlay;
    private TextView tvDes,tvRname;

    public SpecialItem(Context context , Special special) {
        super(context);


        initViews(context);
        setSpecial(special);

    }

    public SpecialItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);

    }

    /**
     * 绑定控件
     * @param context
     */
    private void initViews(Context context) {
        //把指定的布局文件作为当前控件的显示内容
        inflate(context, R.layout.widget_special_item,this);

        //初始化控件
        ivContent = (ImageView) findViewById(R.id.special_item_content_iv);
        ivPlay = (ImageView) findViewById( R.id.special_item_play_iv);

        tvDes = (TextView) findViewById(R.id.special_item_des_tv);
        tvRname = (TextView) findViewById(R.id.special_item_rname_tv);

    }

    /**
     *  设置内容
     * @param special
     */
    public void setSpecial(final Special special) {

        //设置显示内容
        tvRname.setText(special.getRname());

        ImageLoader.getInstance().displayImage(special.getPic(),ivContent, ImageUtil.getDefalutOptions());

        switch (special.getRtype())
        {
            //字在
            case 0:
                ivPlay.setVisibility(INVISIBLE);
                tvDes.setText(special.getAlbumName());
                break;
            //两个都在
            case 1:
                ivPlay.setVisibility(VISIBLE);
                tvDes.setText(special.getDes());
                break;
            //都不在
            case 2:
                tvDes.setVisibility(INVISIBLE);
                ivPlay.setVisibility(INVISIBLE);
                break;
            //display在
            case 11:
                tvDes.setVisibility(INVISIBLE);
                break;
        }


        //点击Item跳转处理
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!special.getMp3PlayUrl().isEmpty())
                {
                    JumpManager.jumpToPlayer1(getContext(),special);

                }else if(!special.getWeburl().isEmpty())
                {
                    JumpManager.jumpToWeb(getContext(),special.getWeburl());

                }else if(special.getWeburl().isEmpty() && special.getMp3PlayUrl().isEmpty())
                {
                    JumpManager.jumpToPlayer2(getContext());
                }

            }
        });

    }


}
