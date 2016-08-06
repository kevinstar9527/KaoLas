package kaola.zhanchengguo.com.kaola.mine.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.ui.TypeFragment;
import kaola.zhanchengguo.com.kaola.other.adapter.CommFragmentPageAdapter;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.ui.LoginActivity;
import kaola.zhanchengguo.com.kaola.other.utils.Blur;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;

/**
 * Created by Administrator on 2016/6/7.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tabLayout;

    private Toolbar toolbar;

    private ViewPager viewPager;

    private TextView ivlogin,ivregister;

    private ImageView imguser,ivtopbg;




    @Override
    protected int getLayoutId() {

        //root.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.fragment_mine;
    }



    @Override
    protected void initViews() {

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);

        tabLayout = (TabLayout) root.findViewById(R.id.mine_tl);

        viewPager = (ViewPager) root.findViewById(R.id.mine_content_vp);

        ivlogin = (TextView) root.findViewById(R.id.mine_login_tv);
        ivregister = (TextView) root.findViewById(R.id.mine_registered_tv);
        imguser = (ImageView) root.findViewById(R.id.mine_camera_iv);

        ivtopbg = (ImageView) root.findViewById(R.id.mine_top_bg_iv);

        tabLayout.addTab(tabLayout.newTab().setText("收听历史"));
        tabLayout.addTab(tabLayout.newTab().setText("订阅"));
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));

        ivlogin.setOnClickListener(this);
        ivregister.setOnClickListener(this);

    }

    @Override
    protected void initEvents() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //切换Viewpager的时候让Tanlayout联动
                tabLayout.getTabAt(position).select();


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击Tablayout的时候，让Viewpaget联动
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    @Override
    protected void initDatas() {

        List<Fragment> list = new ArrayList<>();
        //添加fragment
        list.add(new ListenHistoryFragment());
        list.add(new TypeFragment());
        list.add(new CollectionFragment());

        CommFragmentPageAdapter adapter = new CommFragmentPageAdapter(getChildFragmentManager(),list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.mine_login_tv:
                Intent intent1 = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent1, 1);
                break;
            case R.id.mine_registered_tv:
                Intent intent2 = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent2,2);
                break;
        }
    }

    public void UpdateUi(final String str) {

        JSONObject object = null;
        try {
            object = new JSONObject(str.toString());
            ivlogin.setText(object.getString("nickname"));
            ivregister.setVisibility(View.INVISIBLE);
            ImageLoader.getInstance().displayImage(object .getString("figureurl_qq_2"),imguser, ImageUtil.getCircleOptions());

            showBluImag(object .getString("figureurl_qq_2"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showBluImag(final String url)
    {
        KaoLaTask.IDdownloadListener ddownloadListener = new KaoLaTask.IDdownloadListener() {
            @Override
            public void upgradeProgress(float progress) {

            }

            @Override
            public void Completed(File file) {

                Bitmap bitmap  = BitmapFactory.decodeFile(file.getAbsolutePath());

                final Bitmap blurbitmap = Blur.fastblur(getActivity(),bitmap,25);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ivtopbg.setImageBitmap(blurbitmap);
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
                return HttpUtil.downLoad(url, FileUtil.DIR_IMAGE,FileUtil.getFileNameByHashCode(url),listener);
            }
        },ddownloadListener).execute();
    }
}
