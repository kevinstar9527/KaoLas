package kaola.zhanchengguo.com.kaola.discover.util;

import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;

/**
 * 发现模块的所有的网络请求url和方法
 * Created by Administrator on 2016/6/8.
 */
public class DiscoverUtil {

    /**
     * 推荐
     */
    public  static final String URL_RECOMMENT="http://api.kaolafm.com/api/v4/pagecontent/list?pageid=104&installid=10000&udid=03109aaa93c76cad9a0327e52eacc93a&imsi=460030238920982&operator=3&lon=113.945338&lat=22.534432&network=1&timestamp=1461832025&sign=3a9525cee60a25e487a099edbfcef6b1&resolution=1080*1812&devicetype=0&channel=A-360&version=4.7.1&appid=0";

    /**
     * 分类下部
     */
    public static final String URL_TYPE_Botton="http://api.kaolafm.com/api/v4/category/listofall?timezone=-18000&installid=00032gUz&udid=60a89eb3a1c25c1316f1dfe99a8226fd&sessionid=60a89eb3a1c25c1316f1dfe99a8226fd1465695158865&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1465695163&sign=9b7a01f73f15ab81f4909e1a289b2a41&resolution=768*1184&devicetype=0&channel=A-360&version=4.8.1&appid=0&";

    /**
     * 分类上部
     */
    public static final String URL_TYPE_TOP="http://api.kaolafm.com/api/v4/labelinfo/list?id=345&timezone=28800&installid=10000&udid=df89950db6c8b27b94e3112145a9217b&sessionid=df89950db6c8b27b94e3112145a9217b1464066900244&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1464066903&sign=89f2b488d14f006f11f7fb61199b7cd5&resolution=768*1280&devicetype=0&channel=upgrade&version=4.8.1&appid=0&";
    /**
     * 电台
     */
    public static final String URL_RADIO = "http://api.kaolafm.com/api/v4/pagecontent/list?pageid=107&timezone=28800&installid=0002wdkC&udid=df89950db6c8b27b94e3112145a9217b&sessionid=df89950db6c8b27b94e3112145a9217b1464067343770&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1464067459&sign=89f2b488d14f006f11f7fb61199b7cd5&resolution=768*1280&devicetype=0&channel=upgrade&version=4.8.1&appid=0&";

    /**
     * 主播
     */
    public static final String URL_ANCHOR = "http://api.kaolafm.com/api/v4/pagecontent/list?pageid=105&timezone=28800&installid=10000&udid=df89950db6c8b27b94e3112145a9217b&sessionid=df89950db6c8b27b94e3112145a9217b1464066900244&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1464067059&sign=89f2b488d14f006f11f7fb61199b7cd5&resolution=768*1280&devicetype=0&channel=upgrade&version=4.8.1&appid=0&";

    /**
     * 直播
     */
    public  static final String URL_LIVE = "http://api.kaolafm.com/api/v4/pagecontent/list?pageid=106&timezone=-18000&installid=10000&udid=dc598fec1cd33d1b26591b976b5165ee&sessionid=dc598fec1cd33d1b26591b976b5165ee1466509894710&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1466509916&sign=7d165576b87e0b032b333ff1945fd9a3&resolution=768*1184&devicetype=0&channel=upgrade&version=4.8.1&appid=0&";

    /**
     *  请求推荐数据
     *
     * @param callback
     */
    public static void getRecommend(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_RECOMMENT);
            }
        };

        KaoLaTask task = new KaoLaTask(request,callback);
        task.execute();
    }

    /**
     *  请求分类上面数据
     *
     * @param callback
     */
    public static void getTypeTop(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_TYPE_TOP);
            }
        };

        KaoLaTask task = new KaoLaTask(request,callback);
        task.execute();
    }

    /**
     *  请求分类下面数据
     *
     * @param callback
     */
    public static void getTypeBotton(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_TYPE_Botton);
            }
        };

        KaoLaTask task = new KaoLaTask(request,callback);
        task.execute();
    }

    /**
     *  请求电台数据
     *
     * @param callback
     */
    public static void getRadio(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_RADIO);
            }
        };

        KaoLaTask task = new KaoLaTask(request,callback);
        task.execute();
    }

    /**
     * 请求直播数据
     * @param callback
     */
    public static void getLive(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request  = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_LIVE);
            }
        };

        KaoLaTask task  = new KaoLaTask(request,callback);
        task.execute();
    }


    /**
     *  请求主播数据
     *
     * @param callback
     */
    public static void getAnchor(KaoLaTask.IRequestCallback callback)
    {
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtil.doGet(URL_ANCHOR);
            }
        };

        KaoLaTask task = new KaoLaTask(request,callback);
        task.execute();
    }


}
