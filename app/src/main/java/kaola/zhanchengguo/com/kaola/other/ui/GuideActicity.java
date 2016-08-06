package kaola.zhanchengguo.com.kaola.other.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.adapter.CommFragmentPageAdapter;
import kaola.zhanchengguo.com.kaola.other.utils.Contants;
import kaola.zhanchengguo.com.kaola.other.widget.IndexView;

/**
 * 引导页
 * Created by Administrator on 2016/6/6.
 */
public class GuideActicity extends AppCompatActivity {

    private ViewPager viewPager;
    private IndexView indexView;

    private int[] videoId = new int[]{
            R.raw.splash_1,
            R.raw.splash_2,
            R.raw.splash_4,
            R.raw.splash_5};

    private int[] ivLeftIds = new int[]{
            R.drawable.guide_anim_1_2,
            R.drawable.guide_anim_2_2,
            R.drawable.guide_anim_3_2,
            R.drawable.guide_anim_4_2};

    private int[] ivRightIds = new int[]{
            R.drawable.guide_anim_1_1,
            R.drawable.guide_anim_2_1,
            R.drawable.guide_anim_3_1,
            R.drawable.guide_anim_4_1
    };
    private   int lastPosotion;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //requestWindowFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager) findViewById(R.id.guide_vp);
        indexView = (IndexView) findViewById(R.id.guide_index_view);

        final List<Fragment> list = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++){
            GuideFragment fragment = new GuideFragment(videoId[i],ivLeftIds[i],ivRightIds[i],i);
            list.add(fragment);
        }
        CommFragmentPageAdapter adapter = new CommFragmentPageAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                GuideFragment lastfragments = (GuideFragment) list.get(lastPosotion);
                lastfragments.hideImages();

                GuideFragment fragment = (GuideFragment) list.get(position);
                fragment.showAnim();

                lastPosotion = position;

                indexView.setCurrIndex(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        findViewById(R.id.guide_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转主页面
                Intent intent = new Intent(GuideActicity.this,HomeActivity.class);
                startActivity(intent);
                //防止从Home回到引导页
                finish();
                //修改是否是第一次使用状态
                SharedPreferences preferences = getSharedPreferences(Contants.FLAG_FIRST_USED, Context.MODE_PRIVATE);
                //获取编辑器
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Contants.FLAG_FIRST_USED_VALUE,false);
                editor.commit();//最后别忘记提交


            }
        });

    }

}
