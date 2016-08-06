package kaola.zhanchengguo.com.kaola.discover.ui;

import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Live;
import kaola.zhanchengguo.com.kaola.discover.bean.LiveSpecial;
import kaola.zhanchengguo.com.kaola.discover.util.DiscoverUtil;
import kaola.zhanchengguo.com.kaola.other.adapter.CommImagePageAdapter;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.utils.Contants;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.widget.LineView;
import kaola.zhanchengguo.com.kaola.other.widget.LiveAnchorPanel;
import kaola.zhanchengguo.com.kaola.other.widget.LiveLinePanel;
import kaola.zhanchengguo.com.kaola.other.widget.LiveNowPanel;

/**
 * Created by Administrator on 2016/6/7.
 */
public class LiveFragment extends BaseFragment {

    private LinearLayout llRoot;

    private PullToRefreshScrollView scrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initViews() {

        llRoot = (LinearLayout) root.findViewById(R.id.live_root_ll);
        scrollView = (PullToRefreshScrollView) root;

    }

    @Override
    protected void initEvents() {

        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //下拉
                new Thread()
                {
                    @Override
                    public void run() {
                        super.run();

                        try {

                            sleep(3000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                scrollView.onRefreshComplete();
                            }
                        });
                    }
                };
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //上拉
            }
        });
    }

    @Override
    protected void initDatas() {

        DiscoverUtil.getLive(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                try {

                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString(Contants.FLAG_RESULT_MESSAGE);

                     if(Contants.FLAG_STATE_SUCCESS.equals(message))
                     {
                         JSONObject result = root.getJSONObject(Contants.FLAG_RESULT_RESULT);

                         JSONArray datalist = result.getJSONArray(Contants.FLAG_RESULT_DATALIST);

                         List<Live> liveList = Live.arrayLiveFromData(datalist.toString());

                         for(int i = 0 ; i < liveList.size() ; i ++)
                         {
                             Live live  = liveList .get(i);

                             switch (live.getComponentType())
                             {
                                 case Live.ComponentType.TYPE_BANNER:
                                     addBanner(live);
                                     break;
                                 case Live.ComponentType.TYPE_NOW_LIVE:
                                     addLiveNowView(live);
                                     break;
                                 case Live.ComponentType.TYPE_IMAG:
                                     addImagView(live);
                                     break;
                                 case Live.ComponentType.TYPE_LINE:
                                     addLineView(live);
                                     break;
                                 case Live.ComponentType.TYPE_HOT:
                                     addHotView(live);
                                     break;
                             }
                         }
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
     *  添加滑动视图
     * @param live
     */
    public void addBanner(Live live)
    {
        LinearLayout llayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        llayout.setLayoutParams(llparam);
        llayout.setOrientation(LinearLayout.VERTICAL);

        ViewPager viewPager = new ViewPager(getActivity());
        LinearLayout.LayoutParams params = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,450);
        viewPager.setLayoutParams(params);

        final LineView lineView  = new LineView(getActivity());
        LinearLayout.LayoutParams lineparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,20);
        lineView.setLayoutParams(lineparam);

        List<ImageView> imageViews = new ArrayList<>();
        List<String> urlList = new ArrayList<>();

        List<LiveSpecial> liveSpecials = live.getDataList();

        for(int i = 0 ; i < liveSpecials.size() ; i++)
        {
            //创建并设置ImagView
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //设置图片的宽高
            ViewPager.LayoutParams vparams = new ViewPager.LayoutParams();
            vparams.width = LinearLayout.LayoutParams.MATCH_PARENT;
            vparams.height = 450;
            imageView.setLayoutParams(vparams);

            imageViews.add(imageView);
            urlList.add(liveSpecials.get(i).getPic());
        }

        CommImagePageAdapter adapter = new CommImagePageAdapter(imageViews,urlList);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lineView.setCurrIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        llayout.addView(viewPager);
        llayout.addView(lineView);

        llRoot.addView(llayout);

    }

    /**
     * 添加正在直播视图
     * @param live
     */
    public void addLiveNowView(Live live)
    {
        LiveNowPanel liveNowPanel  = new LiveNowPanel(getActivity(),live);
        llRoot.addView(liveNowPanel);
    }

    /**
     * 添加图片
     * @param live
     */
    public void addImagView(Live live)
    {
        ImageView imageView  = new ImageView(getActivity());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,250);
        params.setMargins(30,20,30,20);
        imageView.setLayoutParams(params);

        List<LiveSpecial> liveSpecials = live.getDataList();
        ImageLoader.getInstance().displayImage(liveSpecials.get(0).getPic(),imageView, ImageUtil.getDefalutOptions());

        llRoot.addView(imageView);
    }

    /**
     * 添加沙龙，直播视图
     * @param live
     */
    public void addLineView(Live live)
    {
        LiveLinePanel liveLinePanel = new LiveLinePanel(getActivity(),live);
        llRoot.addView(liveLinePanel);
    }

    /**
     * 热门主播视图
     * @param live
     */
    public void addHotView(Live live )
    {
        LiveAnchorPanel liveAnchorPanel = new LiveAnchorPanel(getActivity(),live);
        llRoot.addView(liveAnchorPanel);
    }


}
