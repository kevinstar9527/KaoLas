package kaola.zhanchengguo.com.kaola.other;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import kaola.zhanchengguo.com.kaola.other.db.HistoryManager;

/**
 * Created by Administrator on 2016/6/8.
 */
public class KaoLaApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        //ImagLoader初始化
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

        /*Picasso picasso = new Picasso.Builder(this)
                .memoryCache(new LruCache(30<<20))  //30x1024x1024 内存缓存大小
                .downloader(new OkHttpDownloader(FileUtil.DIR_IMAGE)) // 磁盘缓存
                .defaultBitmapConfig(Bitmap.Config.RGB_565) //默认图片的质量
                .build();

        Picasso.setSingletonInstance(picasso); //设置成单例模式*/

        ShareSDK.initSDK(this);

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        //初始化数据库
        HistoryManager.init(this);
    }
}
