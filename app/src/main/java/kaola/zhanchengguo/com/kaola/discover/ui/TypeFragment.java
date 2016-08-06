package kaola.zhanchengguo.com.kaola.discover.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.adapter.TypeGridAdapter;
import kaola.zhanchengguo.com.kaola.discover.bean.Type;
import kaola.zhanchengguo.com.kaola.discover.bean.TypeTop;
import kaola.zhanchengguo.com.kaola.discover.util.DiscoverUtil;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.utils.Contants;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;
import kaola.zhanchengguo.com.kaola.other.widget.TypeTopLayout;

/**
 * Created by Administrator on 2016/6/7.
 */
public class TypeFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private TypeGridAdapter gridAdapter;

    private TextView tvname;

    private TypeTopLayout typeTopLayout;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_type;
    }

    @Override
    protected void initViews() {

        recyclerView = (RecyclerView) root.findViewById(R.id.type_bottom_rv);

        typeTopLayout = (TypeTopLayout) root.findViewById(R.id.type_ttl);

        tvname = (TextView) root.findViewById(R.id.type_bottom_title_tv);

        //使用表格布局
        GridLayoutManager manager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    protected void initEvents() {

        typeTopLayout.setOnItemClickListener(new TypeTopLayout.IOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtil.d("position = " + position);
            }
        });
    }

    @Override
    protected void initDatas() {

        //请求分类上方
        requestTop();

        //请求分类下方的
        requestBotton();


    }

    public void requestTop()
    {
        DiscoverUtil.getTypeTop(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                try {

                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString("message");

                    if ("success".equals(message))
                    {
                        JSONObject result = root.getJSONObject("result");

                        JSONArray datalist = result.getJSONArray("dataList");

                        List<TypeTop> typeTopList = TypeTop.arrayTypeTopFromData(datalist.toString());

                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < typeTopList.size(); i++)
                        {
                            list.add(typeTopList.get(i).getPic());
                        }

                        typeTopLayout.setImageUrlList(list);

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


    public void requestBotton() {
        final List<Type> list = new ArrayList<>();
        gridAdapter = new TypeGridAdapter(getActivity(),list);
        recyclerView.setAdapter(gridAdapter);

        DiscoverUtil.getTypeBotton(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {
                try {
                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString(Contants.FLAG_RESULT_MESSAGE);

                    if(Contants.FLAG_STATE_SUCCESS.equals(message))
                    {
                        JSONObject result = root.getJSONObject(Contants.FLAG_RESULT_RESULT);
                        JSONArray dataList = result.getJSONArray(Contants.FLAG_RESULT_DATALIST);

                        //获取第一条数据

                        JSONObject object1 = (JSONObject) dataList.get(0);

                        String name = object1.getString("name");
                        tvname.setText(name);

                        //用第四方法生成一个list
                        List<Type> typeList = Type.arrayTypeFromData(object1.toString(),Contants.FLAG_RESULT_DATALIST);

                        //更新数据
                        list.addAll(typeList);
                        gridAdapter.notifyDataSetChanged();



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
}
