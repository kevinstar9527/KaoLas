package kaola.zhanchengguo.com.kaola.other.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 用OkHttp封装的网络请求工具
 * Created by Administrator on 2016/6/14.
 */
public class OkHttpUtil {

    private static OkHttpClient client;

    public static OkHttpClient getInstance()
    {
        if(client == null ) {
            synchronized (OkHttpUtil.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client ;
    }

    /**
     *  Get请求
     * @param url
     * @param callback
     */
    public static void doGet(String url,final KaoLaTask.IRequestCallback callback)
    {
        LogUtil.d("url = " + url);

        getInstance();

        Request  request = new Request.Builder()
                .get()  //get请求
                .url(url)
                .build();

        Call call = client.newCall(request);

        //不推荐使用这种方法
       /* try {

            call.execute();  //另起一个子线程执行

        } catch (IOException e) {
            e.printStackTrace();
        }*/


        //放在线程队列中执行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if(callback != null)
                {
                    callback.error(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();

                //获取字符流
                Reader reader  = body.charStream();

                BufferedReader bufferedReader = new BufferedReader(reader);

                String read = null;

                StringBuffer resultBuffer = new StringBuffer();

                while((read = bufferedReader.readLine()) != null)
                {
                    resultBuffer.append(read);
                }

                String result = resultBuffer.toString();
                LogUtil.w("请求成功rsult = " + result);

                if(callback !=null)
                {
                    callback.success(result);
                }
            }
        });
    }

    /**
     * Post请求
     * @param url
     * @param requestBody  参数
     * @param callback
     */
    public static void doPost(String url, RequestBody  requestBody , final KaoLaTask.IRequestCallback callback)
    {
        LogUtil.d("url = " + url);

        getInstance();

        Request request  = new Request.Builder()
                .post(requestBody)  //写入参数
                .url(url)
                .build();

        Call call  = client .newCall(request);

        //放在线程队列中执行
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if(callback != null)
                {
                    callback.error(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();

                //获取字符流
                Reader reader  = body.charStream();

                BufferedReader bufferedReader = new BufferedReader(reader);

                String read = null;

                StringBuffer resultBuffer = new StringBuffer();

                while((read = bufferedReader.readLine()) != null)
                {
                    resultBuffer.append(read);
                }

                String result = resultBuffer.toString();
                LogUtil.w("请求成功rsult = " + result);

                if(callback !=null)
                {
                    callback.success(result);
                }
            }
        });

    }
}
