package kaola.zhanchengguo.com.kaola.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.discover.bean.R_Deeper;
import kaola.zhanchengguo.com.kaola.discover.bean.Type;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * 分类页面下的adapter
 * Created by Administrator on 2016/6/12.
 */
public class RadioGridAdapter extends RecyclerView.Adapter<RadioGridAdapter.RadioGridHolder> {

    private List<R_Deeper> list;

    private LayoutInflater inflater;

    private mItemClickListener itemClickListener;

    public RadioGridAdapter(Context context, List<R_Deeper> list) {

        this.list = list;

        inflater = LayoutInflater.from(context);
        //填充器的第二种初始化

    }

    public List<R_Deeper> getList() {
        return list;
    }

    public void setList(List<R_Deeper> list) {
        this.list = list;
    }

    @Override
    public RadioGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview =  inflater.inflate(R.layout.adapter_radio_grid,null);
        return new RadioGridHolder(itemview);
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(mItemClickListener listener){
        this.itemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(RadioGridHolder holder, int position) {

        final int index = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(index);
            }
        });

        if (position == list.size())
        {
            holder.tvName.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            if(position > 7){
                holder.imageView.setImageResource(R.drawable.category_up);
            }else{
                holder.imageView.setImageResource(R.drawable.category_down);
            }
            return;
        }
        holder.tvName.setVisibility(View.VISIBLE);
        holder.imageView.setVisibility(View.GONE);
        holder.tvName.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size() + 1;
    }


    class RadioGridHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        ImageView imageView;

        public RadioGridHolder(View itemView )
        {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.type_grid_radio_iv);
            imageView = (ImageView) itemView.findViewById(R.id.type_grid_btn_iv);
        }
    }

    public interface mItemClickListener
    {
        void onItemClick(int postion);
    }
}





