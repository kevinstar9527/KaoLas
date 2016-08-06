package kaola.zhanchengguo.com.kaola.other.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 本应用所有的Fragment的父类
 * Created by Administrator on 2016/6/7.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 子类的根布局
     */
    protected  View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(getLayoutId(),null);

        return root;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //注意：调用顺序不能乱
        initViews();

        initEvents();

        initDatas();






    }

    /**
     * 从子类获取布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 视图初始化
     */
    protected abstract  void initViews();

    /**
     * 事件初始化
     */
    protected abstract  void initEvents();

    /**
     * 数据初始化
     */
    protected abstract  void initDatas();
}
