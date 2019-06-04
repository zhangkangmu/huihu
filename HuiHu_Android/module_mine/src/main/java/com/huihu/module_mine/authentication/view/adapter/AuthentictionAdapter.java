package com.huihu.module_mine.authentication.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.authentication.entity.Authentication;
import com.huihu.uilib.customize.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthentictionAdapter extends RecyclerView.Adapter<AuthentictionAdapter.ViewHolder> implements View.OnClickListener {

    private List<Authentication.UserAuthShowModelListBean> mList=new ArrayList<>();
    private Context mContext;

    public void setmList(List<Authentication.UserAuthShowModelListBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }
    public void setContext(Context mContext) {
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public AuthentictionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_authentiction_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthentictionAdapter.ViewHolder viewHolder, int i) {
        Authentication.UserAuthShowModelListBean bean = mList.get(i);
        viewHolder.tv_title.setText(bean.getAuthBewrite());
        viewHolder.tv_des.setText(bean.getAuthCondition());
        ImgTools.showImageView(mContext,bean.getIncMax(),viewHolder.rv_auth);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.rv_auth)
        RoundImageView rv_auth;
        @BindView(R2.id.tv_title)
        TextView tv_title;
        @BindView(R2.id.tv_des)
        TextView tv_des;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
