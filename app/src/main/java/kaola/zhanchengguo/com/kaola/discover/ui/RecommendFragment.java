package kaola.zhanchengguo.com.kaola.discover.ui;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.adapter.EnterAdapter;
import kaola.zhanchengguo.com.kaola.discover.bean.Recommend;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.discover.util.DiscoverUtil;
import kaola.zhanchengguo.com.kaola.other.adapter.CommImagePageAdapter;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;
import kaola.zhanchengguo.com.kaola.other.widget.LineView;
import kaola.zhanchengguo.com.kaola.other.widget.RecomAnchorPanel;
import kaola.zhanchengguo.com.kaola.other.widget.LikePanel;
import kaola.zhanchengguo.com.kaola.other.widget.SASPanel;
import kaola.zhanchengguo.com.kaola.other.widget.SpecialPanel;
import kaola.zhanchengguo.com.kaola.other.widget.VerticalScrollTextView;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RecommendFragment extends BaseFragment {

    private LinearLayout llRoot;

    private SwipeRefreshLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initViews() {

        llRoot = (LinearLayout) root.findViewById(R.id.recomment_root_ll);

        refreshLayout = (SwipeRefreshLayout) root;
        //设置刷新转动的颜色，最多可以设置4种
        refreshLayout.setColorSchemeColors(new int[]{Color.RED , Color.GREEN,Color.BLUE});
    }

    @Override
    protected void initEvents() {

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //正在刷新
                requestData();
            }
        });
    }

    @Override
    protected void initDatas() {

        requestData();
    }

    private void requestData() {
        DiscoverUtil.getRecommend(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                LogUtil.e("object = " + object.toString());

                //开始解析数据

                try {

                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString("message");

                    if("success".equals(message))
                    {
                        //获取result里面的对象
                        JSONObject result = root.getJSONObject("result");

                        JSONArray datalist = result.getJSONArray("dataList");

                        //用Gson解析List
                        List<Recommend> recommendList = Recommend.arrayRecommentFromData(datalist.toString());

                        LogUtil.e("recommendList.size = " + recommendList.size());

                        //清空所有的子控件，防止下拉刷新的时候重复添加
                        llRoot.removeAllViews();

                        for(int i = 0 ; i<recommendList.size() ; i ++)
                        {
                            Recommend recommend = recommendList.get(i);

                            //根据ConPonentType的常量，选择要执行的操作
                            switch (recommend.getComponentType())
                            {
                                case Recommend.ComponentType.TYPE_BANNER:
                                    addBanner(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_ENTER:
                                    addEnter(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_SCROLL:
                                    addVerticalScrollText(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_PANEL:
                                    addSpecialPanel(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_IMAGE:
                                    addImageView(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_YOULIKE:
                                    addLikeView(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_MAJOR_ACTION_SHOP:
                                    addSASView(recommend);
                                    break;
                                case Recommend.ComponentType.TYPE_ANCHOR:
                                    addAnchorView(recommend);
                                    break;
                            }
                        }

                        //停止刷新
                        refreshLayout.setRefreshing(false);

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


    private void addBanner(Recommend recommend)
    {
        LogUtil.e("addBanner");

        LinearLayout llayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llparam = new  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        llayout.setLayoutParams(llparam);
        llayout.setOrientation(LinearLayout.VERTICAL);

        //设置ViewPager的宽高
        ViewPager viewPager = new ViewPager(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,450);
        viewPager.setLayoutParams(params);

        final LineView lineView  = new LineView(getActivity());
        LinearLayout.LayoutParams lineLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,20);
        lineView.setLayoutParams(lineLayout);

        List<ImageView> imageViews = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        List<Special> datalist  = recommend.getDataList();

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

    /**
     * 添加快捷入口
     * @param recommend
     */
    private void addEnter(Recommend recommend)
    {

        LogUtil.e("addEnter");

        RecyclerView recyclerView = new RecyclerView(getActivity());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,350);
        recyclerView.setLayoutParams(params);

        //设置布局管理器
        // GridLayouManger  表格效果
        // StaggeredGridLayoutManger 瀑布流效果
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        //显示方向为横向
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置管理器
        recyclerView.setLayoutManager(manager);


        EnterAdapter adapter = new EnterAdapter(getActivity(),recommend.getDataList());
        recyclerView.setAdapter(adapter);

        llRoot.addView(recyclerView);

    }

    private void addVerticalScrollText(Recommend recommend )
    {
        List<Special> dataList = recommend.getDataList();

        final List<String> nlist = new ArrayList<>();
        final List<String> dlist = new ArrayList<>();
        for( int i = 0 ;i <dataList.size() ; i ++)
        {
            dlist.add(dataList.get(i).getDes());
            nlist.add(dataList.get(i).getRname());
        }

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);

        //设置宽高
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,60);
        params.topMargin = 20;
        params.bottomMargin = 20;
        params.leftMargin = 25;
        ll.setLayoutParams(params);

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.kaola_headline_action);

        final VerticalScrollTextView view = new VerticalScrollTextView(getActivity(),nlist,dlist);

        //设置VerticalTextView的宽高
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        param.leftMargin = 30;
        view.setLayoutParams(param);

        ll.addView(imageView);
        ll.addView(view);
        llRoot.addView(ll);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currIndex = view.getCurrIndex();
                Toast.makeText(getActivity(),dlist.get(currIndex),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *  添加专辑面板
     *
     * @param recommend
     */
    public void addSpecialPanel(Recommend recommend)
    {
        SpecialPanel panel = new SpecialPanel(getActivity(), recommend);
        llRoot.addView(panel);

    }

    /**
     *  添加图片
     */
    public void addImageView(Recommend recommend)
    {
        ImageView imageView = new ImageView(getActivity());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,250);
        params.setMargins(30,20,30,20);
        imageView.setLayoutParams(params);

        List<Special> list = recommend.getDataList();
        ImageLoader.getInstance().displayImage(list.get(0).getPic(),imageView, ImageUtil.getDefalutOptions());

        llRoot.addView(imageView);
    }

    /**
     * 添加猜你喜欢的面板
     * @param recommend
     */
    public void addLikeView(Recommend recommend)
    {
        LikePanel panel = new LikePanel(getActivity(),recommend);
        llRoot.addView(panel);
    }

    /**
     * 添加专题/活动/积分商城面板
     */
    public void addSASView(Recommend recommend )
    {
        SASPanel sasPanel = new SASPanel(getActivity(),recommend);
        llRoot.addView(sasPanel);

    }

    /**
     * 添加热门主播专辑
     * @param recommend
     */
    public void addAnchorView(Recommend recommend)
    {
        RecomAnchorPanel recomAnchorPanel = new RecomAnchorPanel(getActivity(),recommend);
        llRoot.addView(recomAnchorPanel);
    }

}
