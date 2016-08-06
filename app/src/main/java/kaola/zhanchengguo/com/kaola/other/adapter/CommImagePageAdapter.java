package kaola.zhanchengguo.com.kaola.other.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CommImagePageAdapter extends PagerAdapter {

    private List<ImageView> viewList;

    //传入图片的地址
    private List<String> urlList;

    public CommImagePageAdapter(List<ImageView> viewList, List<String> urlList) {
        this.viewList = viewList;
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        return viewList == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = viewList.get(position%viewList.size());
        container.addView(imageView);

        ImageLoader.getInstance().displayImage(urlList.get(position%urlList.size()),imageView, ImageUtil.getDefalutOptions());

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ImageView imageView = viewList.get(position%viewList.size());
        container.removeView(imageView);
    }
}
