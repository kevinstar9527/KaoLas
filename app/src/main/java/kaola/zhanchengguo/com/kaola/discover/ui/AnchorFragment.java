package kaola.zhanchengguo.com.kaola.discover.ui;

import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.discover.util.DiscoverUtil;
import kaola.zhanchengguo.com.kaola.other.adapter.CommImagePageAdapter;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.utils.Contants;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.widget.AnchorPanel;
import kaola.zhanchengguo.com.kaola.other.widget.LineView;
import kaola.zhanchengguo.com.kaola.other.widget.VerticalScrollTextView;

/**
 * Created by Administrator on 2016/6/7.
 */
public class AnchorFragment extends BaseFragment {

    private LinearLayout llRoot;

    private PullToRefreshScrollView scrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_anchor;
    }

    @Override
    protected void initViews() {

        llRoot = (LinearLayout) root.findViewById(R.id.anchor_root_ll);

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
                                //停止
                                scrollView.onRefreshComplete();
                            }
                        });
                    }
                }.start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //上拉
            }
        });

    }

    @Override
    protected void initDatas() {

        DiscoverUtil.getAnchor(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                try {

                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString("message");

                    if(Contants.FLAG_STATE_SUCCESS.equals(message))
                    {
                        JSONObject result = root.getJSONObject(Contants.FLAG_RESULT_RESULT);

                        JSONArray datalist = result.getJSONArray(Contants.FLAG_RESULT_DATALIST);

                        List<Recommend> anchorlist = Recommend.arrayRecommentFromData(datalist.toString());

                        for(int i = 0 ; i < anchorlist.size() ; i ++)
                        {
                            Recommend anchor = anchorlist.get(i);

                            switch (anchor.getComponentType())
                            {
                                case Recommend.ComponentType.TYPE_BANNER:
                                    addBanner(anchor);
                                    break;
                                case Recommend.ComponentType.TYPE_SCROLL:
                                    addVerticalScrollTextView(anchor);
                                    break;
                                case Recommend.ComponentType.TYPE_ANCHOR:
                                    addAnchorView(anchor);
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
     *  ViewPager滑动视图
     * @param anchor
     */
    public void addBanner(Recommend anchor)
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

        List<Special> anchorDataList = anchor.getDataList();
        for(int i = 0 ; i < anchorDataList.size() ; i ++)
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

            urlList.add(anchorDataList.get(i).getPic());

        }

        CommImagePageAdapter pageAdapter = new CommImagePageAdapter(imageViews,urlList);
        viewPager.setAdapter(pageAdapter);

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
     *  TextView 滚动视图
     * @param anchor
     */
    public void addVerticalScrollTextView(Recommend anchor)
    {
        List<Special> anchorDataList = anchor.getDataList();

        List<String> nList = new ArrayList<>();
        List<String> dList = new ArrayList<>();

        for(int i = 0 ; i < anchorDataList.size() ; i ++)
        {
            nList.add(anchorDataList.get(i).getRname());
            dList.add(anchorDataList.get(i).getDes());
        }

        LinearLayout llayout = new LinearLayout(getActivity());

        llayout.setOrientation(LinearLayout.HORIZONTAL);

        //设置布局的宽高
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,60);
        params.setMargins(25,30,30,25);
        llayout.setLayoutParams(params);

        //设置图片
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.anchor_head_icon_normal);

        VerticalScrollTextView scrollTextView = new VerticalScrollTextView(getActivity(),nList,dList);


        //设置scrollTextView的宽高
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        param.leftMargin = 30;
        scrollTextView.setLayoutParams(param);

        llayout.addView(imageView);
        llayout.addView(scrollTextView);

        llRoot.addView(llayout);
    }

    /**
     *  主播专辑
     * @param anchor
     */
    public void addAnchorView(Recommend anchor)
    {
        AnchorPanel anchorPanel = new AnchorPanel(getActivity(),anchor);
        llRoot.addView(anchorPanel);
    }
}



