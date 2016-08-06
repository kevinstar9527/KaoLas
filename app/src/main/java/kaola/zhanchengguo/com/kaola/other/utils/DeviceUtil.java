package kaola.zhanchengguo.com.kaola.other.utils;

import android.content.Context;

/**
 * Created by Liu Jianping
 *
 * @date : 16/6/14.
 */
public class DeviceUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;//屏幕的密度,1dp里面有多少个像素
        return (int) (dpValue * scale + 0.5f);//有0.5f 的误差
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
