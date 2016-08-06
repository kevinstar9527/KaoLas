package kaola.zhanchengguo.com.kaola.discover.ui;

import android.support.v4.view.ViewPager;
import android.widget.GridLayout;
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
import kaola.zhanchengguo.com.kaola.discover.bean.R_Special;
import kaola.zhanchengguo.com.kaola.discover.bean.Radio;
import kaola.zhanchengguo.com.kaola.discover.util.DiscoverUtil;
import kaola.zhanchengguo.com.kaola.other.adapter.CommImagePageAdapter;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.widget.LineView;
import kaola.zhanchengguo.com.kaola.other.widget.RadioAnchorPanel;
import kaola.zhanchengguo.com.kaola.other.widget.RadioColumnPanel;
import kaola.zhanchengguo.com.kaola.other.widget.RadioPerchPanel;
import kaola.zhanchengguo.com.kaola.other.widget.RadioPerchPanelBotton;
import kaola.zhanchengguo.com.kaola.other.widget.RadioSelectionPanel;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RadioFragment extends BaseFragment {

    private LinearLayout llRoot;

    private PullToRefreshScrollView scrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_radio;
    }

    @Override
    protected void initViews() {

        llRoot = (LinearLayout) root.findViewById(R.id.radio_root_ll);

        scrollView = (PullToRefreshScrollView) root;

    }

    @Override
    protected void initEvents() {

        /*scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });*/

        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //下拉
                new Thread() {
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
                                //停止刷新
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

        DiscoverUtil.getRadio(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                try {

                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString("message");

                    if ("success".equals(message)) {
                        //获取result里面的对象
                        JSONObject result = root.getJSONObject("result");

                        JSONArray datalist = result.getJSONArray("dataList");

                        List<Radio> radiolist = Radio.arrayRadioFromData(datalist.toString());

                        for (int i = 0; i < radiolist.size(); i++) {
                            Radio radio = radiolist.get(i);

                            switch (radio.getComponentType()) {
                                case Radio.ComponentType.TYPE_BANNER:
                                    addBanner(radio);
                                    break;
                                case Radio.ComponentType.TYPE_PERCH:
                                    addPerchView(radio);
                                    break;
                                case Radio.ComponentType.TYPE_CHOOSE:
                                    addSelections(radio);
                                    break;
                                case Radio.ComponentType.TYPE_COLUMN:
                                    addColumns(radio);
                                    break;
                                case Radio.ComponentType.TYPE_RECOMMEND:
                                    addRecommends(radio);
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

    public void addBanner(Radio radio)
    {
        LinearLayout llayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,GridLayout.LayoutParams.WRAP_CONTENT);
        llayout.setLayoutParams(lparam);
        llayout.setOrientation(LinearLayout.VERTICAL);

        ViewPager viewPager = new ViewPager(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,450);
        viewPager.setLayoutParams(params);

        final LineView lineView  = new LineView(getActivity());
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,20);
        lineView.setLayoutParams(param);

        List<ImageView> imageViews = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        List<R_Special> datalist  = radio.getDataList();

        for(int i = 0 ; i < datalist.size() ; i ++)
        {
            //创建并添加ImagView
            ImageView imageView = new ImageView(getActivity());

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //设置图片的宽高
            ViewPager.LayoutParams iParams = new ViewPager.LayoutParams();
            iParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
            iParams.height = 450;
            imageView.setLayoutParams(iParams);


            imageViews.add(imageView);

            //添加图片地址
            urls.add(datalist.get(i).getPic());

        }

        CommImagePageAdapter pageAdapter = new CommImagePageAdapter(imageViews,urls);
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

    public void addPerchView(Radio radio)
    {
        RadioPerchPanel radioPerchPanel = new RadioPerchPanel(getActivity(),radio);
        llRoot.addView(radioPerchPanel);

        RadioPerchPanelBotton panelBotton = new RadioPerchPanelBotton(getActivity(),radio);
        llRoot.addView(panelBotton);


    }

    public void addSelections(Radio radio)
    {
        RadioSelectionPanel selectionPanel = new RadioSelectionPanel(getActivity(),radio);
        llRoot.addView(selectionPanel);
    }

    public void addColumns(Radio radio)
    {

        RadioColumnPanel radioColumnPanel = new RadioColumnPanel(getActivity(),radio);
        llRoot.addView(radioColumnPanel);
    }

    public void addRecommends(Radio radio)
    {
        RadioAnchorPanel radioAnchorPanel = new RadioAnchorPanel(getActivity(),radio);
        llRoot.addView(radioAnchorPanel);
    }


}
