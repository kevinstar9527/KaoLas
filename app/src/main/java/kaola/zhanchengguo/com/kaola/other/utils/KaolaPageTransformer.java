package kaola.zhanchengguo.com.kaola.other.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2016/6/16.
 */
public class KaolaPageTransformer implements ViewPager.PageTransformer {


    /**
     * 最大放大倍数
     */
    private static final float MAX_SCALE = 1.1f;

    /**
     * 最小缩放倍数
     */
    private static final float MIN_SCALE = 0.9f;

    /**
     *
     * @param page
     * @param position   三个页面在停止状态下，position分别为-1,0,1，当position变大往右移，当position变小往左移
     */
    @Override
    public void transformPage(View page, float position) {

        if (position < -1)
        {
            position = -1;
        } else if (position > 1)
        {
            position = 1;
        }

        //临时的缩放比例
        float tempScal = position < 0 ? 1+ position:1-position;

        //缩放的变化系数
        float slope = (MAX_SCALE - MIN_SCALE) / 1 ;
        //真实的缩放比例 = 最小的比例 + 临时的缩放比例*缩放系数
        float scalValue = MIN_SCALE + tempScal * slope;

        LogUtil.d("scalValue = " + scalValue);

        page.setScaleX(scalValue);
        page.setScaleY(scalValue);
    }
}
