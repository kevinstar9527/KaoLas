package kaola.zhanchengguo.com.kaola.other.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import kaola.zhanchengguo.com.kaola.R;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ImageUtil {


    /**
     *   默认图片配置
     * @return
     */
    public static DisplayImageOptions getDefalutOptions()
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565) //图片质量
                .showImageOnLoading(R.drawable.album_hidden)
                .cacheInMemory(true) //添加到内存缓存里面
                .cacheOnDisc(true)   //下载到sd卡
                .build();  //构建
        return options;
    }


    /**
     *   圆形图片配置
     * @return
     */
    public static DisplayImageOptions getCircleOptions()
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565) //图片质量
                .showImageOnLoading(R.drawable.album_hidden)
                .cacheInMemory(true) //添加到内存缓存里面
                .cacheOnDisc(true)   //下载到sd卡
                .displayer(new CircleBitmapDisplayer())
                .build();  //构建
        return options;
    }
}
