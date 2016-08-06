package kaola.zhanchengguo.com.kaola.other.utils;

import android.os.AsyncTask;

import java.io.File;
import java.util.List;

/**
 * 请求任务信息
 * Created by Administrator on 2016/6/7.
 */
public class KaoLaTask extends AsyncTask<Void,Void,Object> {

    private IRequest request;

    private IRequestCallback iRequestCallback;

    private IDownLoadRequest downLoadRequest;

    private IDdownloadListener listener;

    /**
     * 执行非下载的网络请求，使用这个构造方法
     * @param request
     * @param iRequestCallback
     */
    public KaoLaTask(IRequest request, IRequestCallback iRequestCallback) {

        if(request == null && iRequestCallback == null)
        {
            throw new NullPointerException("IRequest or IRequestCallback can not be null !");
        }
        this.request = request;
        this.iRequestCallback = iRequestCallback;
    }

    /**
     * 执行下载操作，使用这个构造方法
     * @param request
     * @param listener
     */
    public KaoLaTask(IDownLoadRequest request, IDdownloadListener listener) {

        if (request == null || listener == null)
        {
            throw new NullPointerException("IDownLoadRequest or IDownloadListener can not be null !!!");
        }
        this.downLoadRequest = request;
        this.listener = listener;
    }


    @Override
    protected Object doInBackground(Void... params) {

        //如果request = null ，表示使用第二个构造方法，那么我们执行下载文件
        if (request == null)
        {
            return downLoadRequest.doRequest(listener);
        }

        return request.doRequest();
    }

    @Override
    protected void onPostExecute(Object result) {

        if (iRequestCallback == null)
        {
            return;
        }


        if(result == null)
        {
            iRequestCallback.error("请求失败~~~");
        }else
        {
            iRequestCallback.success(result);
        }
    }


    /**
     * 请求接口（商品信息：你要买什么东西？）
     */
    public interface IRequest
    {
        /**
         * 执行网络请求
         */
        Object doRequest();
    }

    /**
     * 文件下载请求接口
      */
    public interface IDownLoadRequest
    {
        /**
         * 执行网络请求
         */
        Object doRequest(IDdownloadListener listener);
    }

    /**
     * 请求结果回调（收货地址）
     */
    public interface IRequestCallback
    {
        /**
         * 成功回调
         * @param object 请求结果
         */
        void success(Object object);

        /**
         * 错误回调
         * @param msg 错误信息
         */
        void error(String msg);
    }

    public interface IDdownloadListener
    {
        //更新进度
        void upgradeProgress(float progress);

        //下载完成
        void Completed(File file);

        //下载失败
        void error(String msg);

        //开始下载
        void start();
    }




}
