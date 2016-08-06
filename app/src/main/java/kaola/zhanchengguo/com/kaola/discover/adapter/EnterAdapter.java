package kaola.zhanchengguo.com.kaola.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.Special;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;
import kaola.zhanchengguo.com.kaola.other.utils.JumpManager;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * RecycleViewAdpter的编写步骤：
 *
 * 1.命名一个类，先别忙着继承
 *
 * 2.写一个局部内部类继承RecycleView.ViewHolder，在构造方法里面给控件初始化
 *
 * 3.继承RecyclerView.Adapter<EnterAdapter.EnterViewHolder>
 *
 * 4.实现三个抽象方法
 *
 *
 * 快捷入口的Adapter
 * Created by Administrator on 2016/6/8.
 */
public class EnterAdapter extends RecyclerView.Adapter<EnterAdapter.EnterViewHolder>
{
    private List<Special> list;

    private LayoutInflater inflater;

    private Context context;

    public EnterAdapter(Context context,List<Special> list) {

        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public EnterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.adapter_enter_item,null);

        return new EnterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EnterViewHolder holder, final int position) {

        final Special special  = list.get(position);

        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.imageView, ImageUtil.getDefalutOptions());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weburl = special.getWeburl();
                String mp3url = special.getMp3PlayUrl();

                LogUtil.w("weburl = "  + weburl);

                if(!weburl.isEmpty())
                {
                    JumpManager.jumpToWeb(context, weburl);

                }else if(!mp3url.isEmpty())
                {
                    JumpManager.jumpToPlayer1(context,special);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class  EnterViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public EnterViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.adapter_enter_iv);
            //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        }
    }

}
