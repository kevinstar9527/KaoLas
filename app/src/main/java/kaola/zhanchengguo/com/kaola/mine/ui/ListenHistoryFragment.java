package kaola.zhanchengguo.com.kaola.mine.ui;

import android.widget.TextView;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.ui.BaseFragment;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/18.
 */
public class ListenHistoryFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_book;
    }

    @Override
    protected void initViews() {

        LogUtil.w(">>>>>>>>>>>>>>进来");
        textView = (TextView) root.findViewById(R.id.listen_tv);
        textView.setText("你的世界");

    }

    @Override
    protected void initEvents() {


    }

    @Override
    protected void initDatas() {

    }
}
