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
import kaola.zhanchengguo.com.kaola.discover.bean.Type;
import kaola.zhanchengguo.com.kaola.other.utils.ImageUtil;

/**
 * 分类页面下的adapter
 * Created by Administrator on 2016/6/12.
 */
public class TypeGridAdapter extends RecyclerView.Adapter<TypeGridAdapter.TypeGridHolder> {

    private List<Type> list;

    private LayoutInflater inflater;

    public TypeGridAdapter(Context context,List<Type> list) {

        this.list = list;

        inflater = LayoutInflater.from(context);
        //填充器的第二种初始化

    }

    @Override
    public TypeGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview =  inflater.inflate(R.layout.adapter_type_grid,null);
        return new TypeGridHolder(itemview);
    }

    @Override
    public void onBindViewHolder(TypeGridHolder holder, int position) {

        Type type = list.get(position);
        holder.tvType.setText(type.getTitle());
        ImageLoader.getInstance().displayImage(type.getIcon(),holder.ivIcon, ImageUtil.getDefalutOptions());

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    class TypeGridHolder extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        TextView tvType;

        public TypeGridHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.type_grid_icon_iv);
            tvType = (TextView) itemView.findViewById(R.id.type_grid_type_iv);
        }
    }
}
