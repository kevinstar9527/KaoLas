package kaola.zhanchengguo.com.kaola.other.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.ui.DiscoverdFragment;
import kaola.zhanchengguo.com.kaola.download.ui.DownloadFragment;
import kaola.zhanchengguo.com.kaola.mine.ui.MineFragment;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;
import kaola.zhanchengguo.com.kaola.other.widget.KaolaSlidePaneLayout;
import kaola.zhanchengguo.com.kaola.zxing.activity.CaptureActivity;


/**
 * 主页面
 * Created by Administrator on 2016/6/6.
 */
public class HomeActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    private Fragment[] fragments;

    private int discoverPagerIndex;

    private KaolaSlidePaneLayout slidePaneLayout;

    private NavigationView navigationView;

    private int lastIndex;

    private MineFragment   mine = new MineFragment();

    private KaolaSlidePaneLayout.IIntercept iIntercept = new KaolaSlidePaneLayout.IIntercept() {
        @Override
        public boolean needIntercept() {

            return discoverPagerIndex == 0;
        }
    };


    private  RadioGroup.OnCheckedChangeListener changeListener = new RadioGroup.OnCheckedChangeListener() {
        //用来记录选中的下标
        int index;
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId)
            {
                case R.id.home_rd_discover:
                    index =0;
                    break;
                case R.id.home_rd_mine:
                    index =1;
                    break;
                case R.id.home_rd_download:
                    index =2;
                    break;
                default:
                    index = 0 ;


            }
            //获取FragmetManager
            FragmentManager fragmentManager = getSupportFragmentManager();
            //开启事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //切换Fragment

            //隐藏上次显示的Fragment
            transaction.hide(fragments[lastIndex]);
            //显示当前的选择的fragment
            transaction.show(fragments[index]);

            //transaction.replace(R.id.home_content_fl,fragments[index]);

            //提交事务
            transaction.commit();

            lastIndex = index;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        radioGroup = (RadioGroup) findViewById(R.id.home_rg);

        radioGroup.setOnCheckedChangeListener(changeListener);

        slidePaneLayout = (KaolaSlidePaneLayout) findViewById(R.id.home_kspl);
        slidePaneLayout.setIIntercept(iIntercept);

        navigationView = (NavigationView) findViewById(R.id.home_nv);

        fragments = new Fragment[]{
                new DiscoverdFragment(),
                mine,
                new DownloadFragment()

        };

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = null;
                switch (item.getItemId())
                {
                    //摇一摇
                    case R.id.menu_shake:
                        intent = new Intent(HomeActivity.this,ShakeActivity.class);
                        break;
                    //照相机
                    case R.id.menu_camera:
                        intent = new Intent(HomeActivity.this,CameraActivity.class);
                        break;
                    //二维码
                    case R.id.menu_qr_code:
                        intent = new Intent(HomeActivity.this,CaptureActivity.class);
                        break;
                    //加密
                    case R.id.menu_secret:
                        intent = new Intent(HomeActivity.this,SecretActivity.class);
                        break;

                }

                startActivity(intent);

                //跳转的同时关闭侧边导航
                slidePaneLayout.closePane();

                return false;
            }
        });



        //获取FragmetManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for(int i = 0 ; i < fragments.length; i ++)
        {
            transaction.add(R.id.home_content_fl,fragments[i]);
            transaction.hide(fragments[i]);
        }

        //默认显示第一个页面
        transaction.show(fragments[0]);

        //transaction.add(R.id.home_content_fl,fragments[0]);

        //提交事务
        transaction.commit();


    }






    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 如果是返回键,直接返回到桌面
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            showExitGameAlert();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitGameAlert() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window window = dialog.getWindow();
        //  主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.show_exit_dialog);

        // 确认按钮添加事件,执行退出应用操作
        Button ok = (Button) window.findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });
        // 关闭alert对话框架
        Button cancel = (Button) window.findViewById(R.id.btn_cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //最小化
        Button min = (Button) window.findViewById(R.id.btn_min);
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
        });
    }

    public void setDiscoverPagerIndex(int index)
    {
        discoverPagerIndex = index;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 3:
                String result = data.getExtras().getString("result");
                LogUtil.e("result >>>>>> " + result);

                mine.UpdateUi(result);
                break;
            case 5:
                LogUtil.e("获取数据失败!");
                break;
        }


    }
}
