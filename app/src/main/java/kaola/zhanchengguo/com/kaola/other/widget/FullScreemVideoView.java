package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 全屏播放的VideoView
 *
 * Created by Administrator on 2016/6/6.
 */
public class FullScreemVideoView extends VideoView{

    public FullScreemVideoView(Context context) {
        super(context);
    }

    public FullScreemVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //重新设置一下宽高
        setMeasuredDimension(width,height);
    }
}
