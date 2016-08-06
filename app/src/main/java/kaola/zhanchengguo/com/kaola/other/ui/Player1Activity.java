package kaola.zhanchengguo.com.kaola.other.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.onekeyshare.OnekeyShare;
import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.Blur;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.JumpManager;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;

/**
 * Created by Administrator on 2016/6/15.
 */
public class Player1Activity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener,View.OnClickListener {

    private SeekBar seekBar;
    private ImageView ivbg,ivtopimg,ivimg;
    private TextView tvtoptitle,tvprotime,tvalltime,tvname;
    private ImageView ivmode,ivlast,ivnext,ivplay,ivlist,ivload;
    private ImageView ivgood,ivdownload,ivcomment,ivshare,ivmore;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private String mp3url,picurl;

    private Special special;

    private Timer timer = new Timer();

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            //在缓冲好后去获取才能避免报错
            final int currTime = mediaPlayer.getCurrentPosition(); //当前时间
            final int allTime = mediaPlayer.getDuration();  //总时长
            final int progress = seekBar.getMax()*currTime / allTime ; //进度条显示的播放速度
            //在主线程中更新进度
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar.setProgress(progress);
                    tvprotime.setText(currTime / 60000 +  ":" + currTime / 1000 % 60);
                    tvalltime.setText(allTime / 60000 +  ":" + allTime / 1000 % 60 );
                }
            });
        }
    };

    /**
     * 默认是列表循环
     */
    private PlayMode mode = PlayMode.LOOP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player1);

        initViews();

        initDatas();

        initEvents();





    }

    private void initViews()
    {

        seekBar = (SeekBar) findViewById(R.id.player1_sb);

        //图片显示控件
        ivbg = (ImageView) findViewById(R.id.player1_bg_iv);
        ivtopimg = (ImageView) findViewById(R.id.player1_top_titleimg_iv);
        ivimg = (ImageView) findViewById(R.id.player1_iv);

        //文字显示控件
        tvtoptitle = (TextView) findViewById(R.id.player1_top_titlename_tv);
        tvprotime = (TextView) findViewById(R.id.player1_progress_time_tv);
        tvalltime = (TextView) findViewById(R.id.player1_all_time_tv);
        tvname = (TextView) findViewById(R.id.player1_name_tv);

        //播放按钮
        ivmode = (ImageView) findViewById(R.id.player1_mode_btn);
        ivlast = (ImageView) findViewById(R.id.player1_prev_btn);
        ivnext = (ImageView) findViewById(R.id.player1_next_btn);
        ivplay = (ImageView) findViewById(R.id.player1_play_btn);
        ivlist = (ImageView) findViewById(R.id.player1_playlist_btn);
        ivload = (ImageView) findViewById(R.id.player1_load_btn);

        //底部控件按钮
        ivgood = (ImageView) findViewById(R.id.player1_botton_good_iv);
        ivdownload = (ImageView) findViewById(R.id.player1_botton_download_iv);
        ivcomment = (ImageView) findViewById(R.id.player1_botton_comment_iv);
        ivshare = (ImageView) findViewById(R.id.player1_botton_share_iv);
        ivmore = (ImageView) findViewById(R.id.player1_botton_more_iv);
    }


    public void initEvents()
    {
        mediaPlayer.setOnPreparedListener(this);  //准备监听
        mediaPlayer.setOnCompletionListener(this);  // 播放完毕监听
        mediaPlayer.setOnBufferingUpdateListener(this); //下载进度监听

        //播放按钮
        ivplay.setOnClickListener(this);
        //分享按钮
        ivshare.setOnClickListener(this);

        /**拖动进度条的操作*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                int duration = mediaPlayer.getDuration();
                int position = progress * duration / 100;
                seekTo(position);
            }
        });
    }

    public void  initDatas()
    {
        special = (Special) getIntent().getSerializableExtra(JumpManager.TAG_SPECIAL);

        mp3url = special.getMp3PlayUrl();
        picurl = special.getPic();
        tvtoptitle.setText(special.getAlbumName());
        tvname.setText(special.getRname());

        //mp3url = getIntent().getStringExtra(JumpManager.TAG_MP3_URL);
        playByUrl(mp3url);
        showBlurBackground(picurl);
        imgByurl(picurl);
        
    }

    private void imgByurl(String picurl)
    {
        ImageLoader.getInstance().displayImage(picurl,ivtopimg, ImageUtil.getDefalutOptions());
        ImageLoader.getInstance().displayImage(picurl,ivimg, ImageUtil.getDefalutOptions());
    }

    private void showBlurBackground(final String picurl) {

        final KaoLaTask.IDdownloadListener downloadListener = new KaoLaTask.IDdownloadListener() {
            @Override
            public void upgradeProgress(float progress) {

            }

            @Override
            public void Completed(File file) {

                //解析图片
                final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                //加工模糊图片
                final Bitmap blurBitmap = Blur.fastblur(Player1Activity.this,
                        bitmap, 25);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ivbg.setImageBitmap(blurBitmap);
                    }
                });

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
                return HttpUtil.downLoad(picurl, FileUtil.DIR_IMAGE,
                        FileUtil.getFileNameByHashCode(picurl), downloadListener);
            }
        }, downloadListener).execute();

    }

    private void playByUrl(final String mp3url)
    {
        ivload.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.downLoad(mp3url, FileUtil.DIR_MP3, FileUtil.getFileNameByHashCode(mp3url), new KaoLaTask.IDdownloadListener() {
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
            mediaPlayer.setDataSource(this,uri);
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

    private void pause()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
        }
    }

    private void stop()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void seekTo(int position)
    {
        mediaPlayer.seekTo(position);
    }

    /**
     * 退出界面后需要做到的操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        task.cancel();
        timer.cancel();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

        seekBar.setSecondaryProgress(percent);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        if(mode == PlayMode.SIGN)
        {
            mp.start();
        }else if(mode == PlayMode.LOOP)
        {
            //获取下一曲的MP3路径
            //playByUrl(mp3url);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        ivload.setVisibility(View.INVISIBLE);
        ivplay.setImageResource(R.drawable.anchor_edit_play_pause);
        mp.start();
        seekBar.setProgress(0);
        timer.schedule(task , 0 ,1000); //每隔一秒钟更新一下进度

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.player1_play_btn:
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    ivplay.setImageResource(R.drawable.anchor_edit_play);

                }else
                {
                    mediaPlayer.start();
                    ivplay.setImageResource(R.drawable.anchor_edit_play_pause);
                }
                break;
            case R.id.player1_botton_share_iv:
                showShare();
                break;
        }


    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);

    }

    public enum PlayMode
    {
        //单曲循环
        SIGN,

        //列表循环
        LOOP,

        //随机
        RANDOM,

        //顺序
        ORDER

    }
}
