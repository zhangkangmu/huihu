package com.huihu.module_home.popularIdea.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_home.R;
import com.huihu.module_home.popularIdea.enity.ItemV0;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
    //动态数组
    private List<ItemV0> mList;
    public final static int TYPE_FOOTER = 0;
    public final static int TYPE_BODY = 1;
    private Context mContext;
    private View mfooterView;
    public void addFooterView(View footerView){
        mfooterView = footerView;
        notifyItemInserted(mList.size());
    }
    public void setContext(Context mConext){
        this.mContext=mConext;
    };
    @Override
    public int getItemViewType(int position) {
        if(mfooterView == null)
            return TYPE_BODY;
        if(position == mList.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_BODY;
    }
    //构造
    public HorizontalAdapter(List<ItemV0> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER && mfooterView != null){
            return new ViewHolder(mfooterView);
        }
        //绑定行布局
        View view = View.inflate(parent.getContext(), R.layout.module_home_item_recycle_h, null);
        //实例化
        HorizontalAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    } //设置数据

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position)!=TYPE_FOOTER)
        ImgTools.showImageView(mContext,"",holder.img);
    }


    //数量
    @Override
    public int getItemCount() {
        return mfooterView != null ? mList.size() + 1 : mList.size();

    }

    //内部类
    class ViewHolder extends RecyclerView.ViewHolder {
        //行布局中的控件
        ImageView img;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            //绑定控件
            img = (ImageView) itemView.findViewById(R.id.iv_recy_h);
//            text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}

