package kaola.zhanchengguo.com.kaola.other.utils;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 用HttpURLConnection封装的网络请求工具类
 * Created by Administrator on 2016/6/7.
 */
public class HttpUtil
{
    /**
     * GET请求
     * @return
     */
    public static Object doGet(String httpurl)
    {
        LogUtil.w("httpurl"+httpurl);

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            URL url =  new URL(httpurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //读取超时
            conn.setReadTimeout(5000);
            //连接超时
            conn.setConnectTimeout(5000);
            //建立连接
            conn.connect();
            //获取返回码
            int responseCode = conn.getResponseCode();
            //如果请求成功了
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //获取输入流
                is = conn.getInputStream();
                //用来存储读取的一行字符
                String read = null;
                //封装流操作
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                StringBuffer resultBuffer = new StringBuffer();

                while((read = br.readLine()) != null)
                {
                    //每读取一行把读取的内容拼接到结果
                    resultBuffer.append(read);
                }

                String result = resultBuffer.toString();

                LogUtil.w("请求成功了！result =  " + result);

                //返回结果
                return result;

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //如果is不等于null，那么其他两个也都不等于null
            if( is != null)
            {
                try {

                    is.close();
                    isr.close();
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.e("请求失败了~~");

        return null;

    }


    public static Object doPost(String httpurl, Map<String,String> params)
    {
        LogUtil.w("httpurl"+httpurl);
        //先来解析参数,拼接成 。。。=。。。&。。=。。。
        //先把Map转化为set
        Set<Map.Entry<String,String>> entrySet = params.entrySet();

        //获取迭代器
        Iterator<Map.Entry<String,String>> iterator = entrySet.iterator();

        StringBuffer paramsBuffer = new StringBuffer();
        //开始迭代
        while (iterator.hasNext())
        {
            Map.Entry<String,String> entry = iterator.next();

            //获取键名
            String key = entry.getKey();
            //拼接键名
            paramsBuffer.append(key);

            paramsBuffer.append("=");

            //获取值
            String value = entry.getValue();
            //拼接value
            paramsBuffer.append(value);

            paramsBuffer.append("&");
        }

        //去掉最后的“&”
        String paramsString =  paramsBuffer.toString();
        paramsString.substring(0,paramsString.length() -1 );

        LogUtil.w("请求成功了！paramsString =  " + paramsString);

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;

        try {

            URL url = new URL(httpurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);  //
            conn.setDoOutput(true);  // 允许输出
            conn.setUseCaches(true);  //允许使用缓存

            conn.connect();


            //先写入参数在获取响应吗

            //用输出流写入参数
            os = conn.getOutputStream();
            os.write(paramsString.getBytes());
            os.flush();

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {

                is = conn.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                StringBuffer resultBuffer = new StringBuffer();
                String read = null;

                while((read = br.readLine()) != null)
                {
                    resultBuffer.append(read);
                }

                String result = resultBuffer.toString();

                LogUtil.w("请求成功了！result =  " + result);

                return result;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if( is != null)
            {
                try {

                    is.close();
                    isr.close();
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(os != null)
            {
                try {

                    os.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.e("请求失败了~~");

        return null;
    }

    /**
     * 文件下载
     *
     * @param httpurl  下载地址
     * @param dir  存储目录
     * @param rename 重命名
     * @param listener 下载监听
     * @return
     */
    public static File downLoad(String httpurl , File dir , String rename, KaoLaTask.IDdownloadListener listener)
    {
        LogUtil.w("httpurl =" +
                ""+httpurl);

        //如果指定目录不存在，那么创建所以的层级目录
        if(!dir.exists()){
            dir.mkdirs();
        }

        File file  = new File(dir,rename);

        if(file.exists())
        {
            if( listener != null)
            {
                listener.Completed(file);
            }
            return file;
        }

        InputStream is = null;

        FileOutputStream fileOutputStream = null;


        try {
            URL url =  new URL(httpurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //读取超时
            conn.setReadTimeout(5000);
            //连接超时
            conn.setConnectTimeout(5000);
            //建立连接
            conn.connect();
            //获取返回码
            int responseCode = conn.getResponseCode();
            //如果请求成功了
            if(responseCode == HttpURLConnection.HTTP_OK)
            {

                //获取输入流
                is = conn.getInputStream();

                fileOutputStream = new FileOutputStream(file);

                byte[] buff = new byte[1024 * 4];

                int read = -1;

                //获取文件大小
                int length = conn.getContentLength();

                //已下载
                int download = 0;

                //开始下载
                if(listener != null)
                {
                    listener.start();
                }

                while((read = is.read(buff)) != -1)
                {
                    fileOutputStream.write(buff,0,read);

                    fileOutputStream.flush();

                    download +=read;
                    double d = download * 100.0 / length;

                    //取两位小数
                    BigDecimal bigDecimal = new BigDecimal(d).setScale(2,BigDecimal.ROUND_HALF_UP);//四舍五入保留小数点后两位

                    float progress = bigDecimal.floatValue();

                    LogUtil.w("progress = " + progress);

                    if(listener != null)
                    {
                        listener.upgradeProgress(progress);
                    }
                }

                LogUtil.w("下载成功~~");
                if(listener != null)
                {
                    listener.Completed(file);
                }

                return file;

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //如果is不等于null，那么其他两个也都不等于null
            if( is != null)
            {
                try {

                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.e("请求失败了~~");

        if(listener != null)
        {
            listener.error("下载失败！");
        }
        return null;

    }

}
