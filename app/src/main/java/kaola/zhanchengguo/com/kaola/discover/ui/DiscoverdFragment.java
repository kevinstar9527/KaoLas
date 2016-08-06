package kaola.zhanchengguo.com.kaola.discover.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.adapter.CommFragmentPageAdapter;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.ui.HomeActivity;

/**
 * Created by Administrator on 2016/6/7.
 */
public class DiscoverdFragment extends BaseFragment {

    private  TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_discover;
    }

    @Override
    protected void initViews() {

        tabLayout = (TabLayout) root.findViewById(R.id.discover_tl);

        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("分类"));
        tabLayout.addTab(tabLayout.newTab().setText("电台"));
        tabLayout.addTab(tabLayout.newTab().setText("直播"));
        tabLayout.addTab(tabLayout.newTab().setText("主播"));

        viewPager = (ViewPager) root.findViewById(R.id.discover_content_vp);

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

                HomeActivity home = (HomeActivity) getActivity();
                home.setDiscoverPagerIndex(position);

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
        //
        list.add(new RecommendFragment());
        list.add(new TypeFragment());
        list.add(new RadioFragment());
        list.add(new LiveFragment());
        list.add(new AnchorFragment());

        CommFragmentPageAdapter fragmentPageAdapter = new CommFragmentPageAdapter(getChildFragmentManager(),list);
         viewPager.setAdapter(fragmentPageAdapter);

    }
}
