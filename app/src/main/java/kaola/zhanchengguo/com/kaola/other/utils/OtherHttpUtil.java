package kaola.zhanchengguo.com.kaola.other.utils;

/**
 * other模块所有网络请求接口和方法
 *
 * Created by Administrator on 2016/6/7.
 */
public class OtherHttpUtil {
    /**
     * 请求广告
     */
    public static final String URL_BANNER = "http://api.kaolafm.com/api/v4/splashscreen/list?timezone=28800&installid=10000&udid=df89950db6c8b27b94e3112145a9217b&sessionid=df89950db6c8b27b94e3112145a9217b1464231568066&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1464231568&sign=89f2b488d14f006f11f7fb61199b7cd5&resolution=768*1280&devicetype=0&channel=upgrade&version=4.8.1&appid=0&";

    /**
     * 请求版本更新
     */
    public static final String URL_UPGRADE = "http://api.kaolafm.com/api/v4/upgrade/check?timezone=28800&installid=00031PNx&udid=e71f486d3189806721fd311ec90c9788&sessionid=e71f486d3189806721fd311ec90c97881465792510416&imsi=460019275200774&operator=2&lon=0.0&lat=0.0&network=1&timestamp=1465800037&sign=a44eea2071be0c3ec74d2927389d05f6&resolution=720*1280&devicetype=0&channel=B-xiaomi&appid=0&version=";


    /**
     * 请求第二个播放页面
     */
    public static final String  URL_PLAYER2 = "http://api.kaolafm.com/api/v4/broadcast/list?&id=1600000000629&pagesize=20&type=1&timezone=-18000&installid=00033Yq8&udid=986d2d2a73d4831ae175789308cb0006&sessionid=986d2d2a73d4831ae175789308cb00061466041065367&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1466041101&playid=e173017770a6e5a063001b18943576cd&sign=3fdba3559103f1f917eb4aaadc8e1ffc&resolution=1440*2560&devicetype=0&channel=itingsH5&version=4.8.1&appid=0&";



    public static void getBanner(KaoLaTask.IRequestCallback callback) {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {

            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_BANNER);
            }
        };
        KaoLaTask task = new KaoLaTask(request, callback);
        task.execute();

    }

    /**
     * 下载更新版本
     *
     * @param callback
     * @param version
     */
    public static void getVersion(KaoLaTask.IRequestCallback callback, final String version) {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_UPGRADE + version);
            }
        };

        new KaoLaTask(request, callback).execute();
    }

    /**
     * 下载apk
     * @param downloadListener
     * @param url
     */
    public static void downloadApk(KaoLaTask.IDdownloadListener downloadListener, final String url)
    {
        KaoLaTask.IDownLoadRequest request = new KaoLaTask.IDownLoadRequest() {
            @Override
            public Object doRequest(KaoLaTask.IDdownloadListener listener) {
                return HttpUtil.downLoad(url, FileUtil.DIR_APK, "kaola.apk", listener);
            }
        };

        new KaoLaTask(request, downloadListener).execute();
    }

    /**
     * 请求第二个播放界面
     * @param callback
     */
    public static void  getPlayerList(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_PLAYER2);
            }
        };
        KaoLaTask task = new KaoLaTask(request, callback);
        task.execute();
    }


}
