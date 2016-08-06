package kaola.zhanchengguo.com.kaola.other.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.adapter.Player2PageAdapter;
import kaola.zhanchengguo.com.kaola.other.bean.PlayEntity;
import kaola.zhanchengguo.com.kaola.other.utils.Blur;
import kaola.zhanchengguo.com.kaola.other.utils.Contants;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.KaolaPageTransformer;
import kaola.zhanchengguo.com.kaola.other.utils.OtherHttpUtil;

/**
 * 可以左右滑动的播放页面
 *
 * Created by Administrator on 2016/6/16.
 */
public class Player2Activity extends AppCompatActivity {

    private ViewPager viewPager;

    private Player2PageAdapter pageAdapter;

    private List<PlayEntity> list = new ArrayList<>();

    private ImageView ivbg,ivtoptitle,ivshare;
    private TextView tvtoptitle,tvtoptitype;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player2);

        initViews();

        setViewPager();

        initevent();

        requestList();

    }

    /**
     * 事件监听
     */
    private void initevent()
    {
        //设置ViewPa的监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

                showBlurBackground(position);
                showTopTitle(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ivshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    /**
     * 设置ViewPager的信息
     */
    public void setViewPager() {
        viewPager.measure(0,0);
        ViewGroup.LayoutParams  layoutParams = viewPager.getLayoutParams();
        //让ViewPager中间的page显示屏幕的3/5
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 2.0 / 3);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 3.0 /5);
        if(layoutParams == null)
        {
            layoutParams = new ViewGroup.LayoutParams(width,height);

        }else
        {
            layoutParams.width = width;
            layoutParams.height = height;
        }

        viewPager.setLayoutParams(layoutParams);

        //至少缓存2页
        viewPager.setOffscreenPageLimit(2);

        //设置页面修改方案
        viewPager.setPageTransformer(true,new KaolaPageTransformer());

        //间隔像素
        viewPager.setPageMargin(60);
    }


    /**
     * 初始化控件
     */
    public void initViews()
    {
        viewPager = (ViewPager) findViewById(R.id.palyer2_vp);
        ivbg = (ImageView) findViewById(R.id.player2_bg_iv);
        ivtoptitle = (ImageView) findViewById(R.id.player2_top_titleimg_iv);
        tvtoptitle = (TextView) findViewById(R.id.player2_top_titlename_tv);
        tvtoptitype = (TextView) findViewById(R.id.player2_top_titletype_tv);
        ivshare = (ImageView) findViewById(R.id.player2_botton_share_iv);
    }

    /**
     * 获取播放列表
     */
    private void requestList()
    {
        pageAdapter = new Player2PageAdapter(this,list);
        viewPager.setAdapter(pageAdapter);

        OtherHttpUtil.getPlayerList(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                try {
                    JSONObject root = new JSONObject(object.toString());
                    String message = root.getString(Contants.FLAG_RESULT_MESSAGE);

                    if( Contants.FLAG_STATE_SUCCESS.equals(message))
                    {
                        JSONObject result = root.getJSONObject(Contants.FLAG_RESULT_RESULT);

                        List<PlayEntity> playEntities = PlayEntity.arrayPlayEntityFromData(result.toString(),Contants.FLAG_RESULT_DATALIST);

                        list.addAll(playEntities);
                        pageAdapter.notifyDataSetChanged();

                        viewPager.setCurrentItem(1);
                        showBlurBackground(1);
                        showTopTitle(1);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {

            }
        });
    }

    /**
     * 显示模糊图片
     * @param position
     */
    private void showBlurBackground(int position)
    {
        PlayEntity playEntity  = list.get(position);

        //获取图片的Url
        final String pic = playEntity.getPic();

        final KaoLaTask.IDdownloadListener downloadListener = new KaoLaTask.IDdownloadListener() {
            @Override
            public void upgradeProgress(float progress) {

            }

            @Override
            public void Completed(File file) {

                //解析图片
                final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                //加工模糊图片
                final Bitmap blurBitmap = Blur.fastblur(Player2Activity.this,
                        bitmap, 25);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ivbg.setImageBitmap(blurBitmap);
                        ivtoptitle.setImageBitmap(bitmap);

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
                return HttpUtil.downLoad(pic, FileUtil.DIR_IMAGE,
                        FileUtil.getFileNameByHashCode(pic),downloadListener);
            }
        },downloadListener).execute();

    }

    public void showTopTitle(int position)
    {

        PlayEntity playEntity  = list.get(position);
        tvtoptitle.setText(playEntity.getName());
        tvtoptitype.setText("-电台-");
    }

    public void showShare()
    {
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

}

