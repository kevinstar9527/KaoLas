package kaola.zhanchengguo.com.kaola.other.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.bean.PlayEntity;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Player2PageAdapter extends PagerAdapter {

    private List<PlayEntity> entitylist;

    private LayoutInflater inflater;

    private List<View> viewList = new ArrayList<>();

    private List<String> radiourlList;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private String picurl;

    private Context context;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 1) {
                radiourlList = (List<String>) msg.obj;
            }
        }
    };

    private SeekBar seekBar;
    private TextView tvpretime,tvalltime,tvname;
    private ImageView imageView,ivplay;

    public Player2PageAdapter(Context context, List<PlayEntity> list) {
        this.context = context;
        this.entitylist = list;

        inflater=LayoutInflater.from(context);

        for(int i = 0 ; i < list.size() ; i ++)
        {
            View contentView = inflater.inflate(R.layout.layout_play,null);
            viewList.add(contentView);
        }
    }

    @Override
    public int getCount() {
        return entitylist == null ? 0 : entitylist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

        if(viewList.size() == 0)
        {
            return null;
        }
        View view = viewList.get(position);

        //初始化控件
        initViewS(view);

        //获取图片url
        picurl = entitylist.get(position).getPic();


        PlayurlList(entitylist.get(position).getPlayUrl());

       /* handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(msg.what == 1) {
                    radiourlList = msg.getData().getStringArrayList("List");
                }

            }
        };*/


        //LogUtil.w(".............radiourlList.size() = " + radiourlList.size());

        //playByUrl(radiourlList.get(0));

        ImageLoader.getInstance().displayImage(picurl,imageView, ImageUtil.getDefalutOptions());
        tvname.setText(entitylist.get(position).getName());

        container.addView(view);
        return view;
    }

    public  void initViewS(View view)
    {
        imageView = (ImageView) view.findViewById(R.id.player1_iv);
        tvname = (TextView) view.findViewById(R.id.player1_name_tv);
        seekBar = (SeekBar) view.findViewById(R.id.player1_sb);
        ivplay = (ImageView) view.findViewById(R.id.player1_play_btn);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if(viewList.size() == 0)
        {
            return;
        }
        container.removeView(viewList.get(position));
    }

    @Override
    public void notifyDataSetChanged() {

        if(viewList.size() == 0)
        {
            for (int i = 0 ; i < entitylist.size() ; i ++)
            {
                View contentView = inflater.inflate(R.layout.layout_play,null);
                viewList.add(contentView);
            }
        }
        super.notifyDataSetChanged();
    }

    private void playByUrl(final String radiourl)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.downLoad(radiourl, FileUtil.DIR_MP3, FileUtil.getFileNameByHashCode(radiourl), new KaoLaTask.IDdownloadListener() {
                    @Override
                    public void upgradeProgress(float progress) {

                    }

                    @Override
                    public void Completed(File file) {
                        play(file);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void start() {

                    }
                });
            }
        }).start();
    }

    /**
     * 播放
     * @param file
     */
    private void play(File file)
    {
        Uri uri = Uri.fromFile(file);
        //重置
        mediaPlayer.reset();

        try {
            //设置播放路径
            mediaPlayer.setDataSource(context,uri);
            //缓冲
            mediaPlayer.prepare();

            //mediaPlayer.prepareAsync();

           /* mediaPlayer.setDataSource(file.getAbsolutePath());
            FileInputStream fis = new FileInputStream(file);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void PlayurlList(final String playurl)
    {
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

                            downloadPlayMenuList(line);
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
                return HttpUtil.downLoad(playurl,FileUtil.DIR_PLAY_URL,FileUtil.getFileNameByHashCode(playurl),listener);
            }
        },downloadListener).execute();
    }

    public void downloadPlayMenuList(final String url)
    {
        final String headurl = cutUrl(url);

        radiourlList = new ArrayList<>();

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
                            //将两个字符串合成一个url
                            String itemurl =  headurl + playItem;

                            LogUtil.w("itemurl = " + itemurl);

                            //添加到集合中
                            radiourlList.add(itemurl);
                        }

                    }

                    Message message = new Message();
//                    Bundle budle = new Bundle();
//                    budle.putStringArrayList("List", (ArrayList<String>) radiourlList);
//                    message.setData(budle);
                    message.obj = radiourlList;
                    message.what=1;
                    handler.sendMessage(message);


                    LogUtil.w("radiourlList.size = " + radiourlList.size());

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
                return HttpUtil.downLoad(url,FileUtil.DIR_PLAY_URL,FileUtil.getFileNameByHashCode(url),listener);
            }
        },downloadListener).execute();
    }

    public  String cutUrl(String url)
    {
        for( int i = url.length()-1 ; i > 0 ; i --)
        {
            if(url.charAt(i) == '/'){

                return url.substring(0,i+1);
            }
        }
        return null;
    }

}
