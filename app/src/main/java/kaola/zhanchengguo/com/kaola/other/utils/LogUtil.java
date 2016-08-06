package kaola.zhanchengguo.com.kaola.other.utils;

import android.util.Log;

/**
 * 日志工具类
 *
 * Created by Administrator on 2016/6/7.
 */
public class LogUtil
{
    public static final boolean isDebug = true;

    public static final String TAG = LogUtil.class.getSimpleName();

    /**
     * 错误级别日志
     * @param msg
     */
    public static void e(String msg){
        if(isDebug)
        {
            Log.e(TAG,msg);
        }
    }

    /**
     * 警告级别日志
     * @param msg
     */
    public static void w(String msg){
        if(isDebug)
        {
            Log.w(TAG,msg);
        }
    }

    /**
     * 调试级别日志
     * @param msg
     */
    public static void d(String msg){
        if(isDebug)
        {
            Log.d(TAG,msg);
        }
    }
}
