package kaola.zhanchengguo.com.kaola.other.utils;


import com.nostra13.universalimageloader.utils.L;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.获取playUrl对应的url值，下载文件
 * 2.用字BufferReader读取文件，获取以http开头的一行（url）
 * 3.用第二步得到的url下载文件
 * 4.用字BufferReader读取文件，获取以.ts结尾的数据，添加到集合
 * 5.截取第二步获取的url去掉/后面的字符，加上第四步的。ts结尾的数据就是我们需要的音频文件
 *
 *  Created by Administrator on 2016/6/17.
 */
public class UrlUtil {

    public static void getPlayerUrl(final String url, final ResponseListener listener)
    {

        final List<String> playmenuurl = new ArrayList<>();

        KaoLaTask.IDdownloadListener downloadListener = new KaoLaTask.IDdownloadListener() {
            @Override
            public void upgradeProgress(float progress) {

            }

            @Override
            public void Completed(File file) {

                BufferedReader br = null;
                try {
                    br =  new BufferedReader(new FileReader(file));

                    String line = null;

                    while ((line = br.readLine()) != null)
                    {
                        if(line.startsWith("http://"))
                        {
                            LogUtil.w("line = " + line);
                            downloadPlayMenuList(line, new ResponseListener()
                            {
                                @Override
                                public void Response(List<String> list) {

                                    playmenuurl.addAll(list);
                                    listener.Response(playmenuurl);
                                }
                            });
                            return;
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(null != br)
                    {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void error(String msg) {

            }

            @Override
            public void start() {

            }
        };

        new KaoLaTask(new KaoLaTask.IDownLoadRequest() {
            @Override
            public Object doRequest(KaoLaTask.IDdownloadListener listener) {
                return HttpUtil.downLoad(url,FileUtil.DIR_PLAY_URL,FileUtil.getFileNameByHashCode(url),listener);
            }
        },downloadListener).execute();


    }

    /**
     * 下载url文件，并封装playurl到数组中
     * @param playurl
     */
    public static void downloadPlayMenuList(final String playurl, final ResponseListener listener)
    {
        final List<String> pmenulist = new ArrayList<>();

        final String headurl = cutUrl(playurl);

        KaoLaTask.IDdownloadListener downloadListener = new KaoLaTask.IDdownloadListener() {
            @Override
            public void upgradeProgress(float progress) {

            }

            @Override
            public void Completed(File file) {

                BufferedReader br =null;
                try {

                    br = new BufferedReader(new FileReader(file));
                    String playItem = null;

                    while ((playItem = br.readLine()) != null)
                    {
                        if(playItem.endsWith(".ts"))
                        {
                            LogUtil.w("playItem = " + playItem);

                            //将两个字符串合成一个url
                            String itemurl =  headurl + playItem;

                            LogUtil.w("itemurl = " + itemurl);

                            pmenulist.add(itemurl);
                        }

                    }
                    LogUtil.w("pmenulist.size() ="+pmenulist.size() );

                    if(pmenulist != null && pmenulist.size() != 0) {

                        listener.Response(pmenulist);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    if (null != br)
                    {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            @Override
            public void error(String msg) {

            }

            @Override
            public void start() {

            }

        };

        new KaoLaTask(new KaoLaTask.IDownLoadRequest() {
            @Override
            public Object doRequest(KaoLaTask.IDdownloadListener listener) {
                return HttpUtil.downLoad(playurl,FileUtil.DIR_PLAY_URL,FileUtil.getFileNameByHashCode(playurl),listener);
            }
        },downloadListener).execute();

    }


    private static String cutUrl(String url)
    {
        for( int i = url.length()-1 ; i > 0 ; i --)
        {
            if(url.charAt(i) == '/'){
                LogUtil.w("url.substring = " + url.substring(0,i+1));
                return url.substring(0,i+1);
            }
        }
        return null;
    }

    public interface ResponseListener
    {
        public void Response(List<String> list);
    }

}
