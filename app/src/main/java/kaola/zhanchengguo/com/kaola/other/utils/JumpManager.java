package kaola.zhanchengguo.com.kaola.other.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.ui.LoginActivity;
import kaola.zhanchengguo.com.kaola.other.ui.Player1Activity;
import kaola.zhanchengguo.com.kaola.other.ui.Player2Activity;
import kaola.zhanchengguo.com.kaola.other.ui.WebActivity;

/**
 *
 * 跳转工具类
 * Created by Administrator on 2016/6/15.
 */
public class JumpManager {

    public static final String TAG_URL = "url";

    public static final String TAG_MP3_URL = "mp3_url";

    public static final String TAG_SPECIAL = "special";

    /**
     * 跳转到WebActivity
     *
     * @param context
     * @param url
     */
    public static void jumpToWeb(Context context, String url)
    {
        Intent intent  = new Intent(context, WebActivity.class);
        intent.putExtra(TAG_URL,url);
        context.startActivity(intent);
    }

    /**
     *  跳转到播放界面
     * @param context
     * @param special
     */
    public static void jumpToPlayer1(Context context, Special special)
    {
        Intent intent  = new Intent(context, Player1Activity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_SPECIAL,special);
        intent.putExtras(bundle);

        context.startActivity(intent);
    }

    /**
     *  跳转到播放界面
     * @param context
     */
    public static void jumpToPlayer2(Context context)
    {
        Intent intent  = new Intent(context, Player2Activity.class);
        context.startActivity(intent);
    }

    /**
     *  跳转到登录界面
     * @param context
     */
    public static void jumpToLogin(Context context)
    {
        Intent intent  = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
