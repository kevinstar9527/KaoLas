package kaola.zhanchengguo.com.kaola.other.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * 文件工具类
 * Created by Administrator on 2016/6/7.
 */
public class FileUtil
{
    /**
     * 应用目录下面的image目录
     *
     */
    public static final File DIR_IMAGE = getDir("image");

    /**
     * apk目录
     */
    public static final File DIR_APK = getDir("apk");

    /**
     * 缓存目录
     */
    public static final File DIR_CACHE = getDir("cache");


    public static final File DIR_MP3 = getDir("mp3");

    public static final File DIR_PLAY_URL = getDir("playurl");





    public static File getSDCardDir()
    {
        //获取SD卡的挂载状态
        String state = Environment.getExternalStorageState();

        //如果是已挂载
        if(state.equals(Environment.MEDIA_MOUNTED))
        {
            File directory = Environment.getExternalStorageDirectory();
            return directory;
        }else
        {
            LogUtil.e("没有sd卡");
        }

        return null;
    }

    /**
     * 获取应用目录
     *
     * @return
     */
    public static File getAppDir()
    {
        File dir = new File(getSDCardDir(),"kaola");

        if(!dir.exists())
        {
            dir.mkdirs();
        }

        return dir;
    }

    private  static File getDir(String dirName)
    {
        File dir = new File(getAppDir(), dirName);

        if(!dir.exists())
        {
            dir.mkdirs();
        }

        return dir;
    }

    /**
     * 用url转换成hashcode作为存储的文件名
     * @param url
     * @return
     */
    public static String getFileNameByHashCode(String url)
    {
        return ""+url.hashCode();
    }


    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }
}
