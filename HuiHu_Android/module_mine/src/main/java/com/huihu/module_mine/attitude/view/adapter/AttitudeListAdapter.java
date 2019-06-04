package com.huihu.module_mine.attitude.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.attitude.entity.AttitudeInfo;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.customize.source.SourceView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ouyangjianfeng
 * @time 2019/2/28  15:09
 * @desc 提问和回答的item, 首页，圈子，个人中心都可复用
 */
public class AttitudeListAdapter extends RecyclerView.Adapter<AttitudeListAdapter.AttitudeViewHolder> {
    public List<AttitudeInfo.PageDatasBean> getmList() {
        return mList;
    }

    private List<AttitudeInfo.PageDatasBean> mList= new ArrayList<>();
    private Context mContext;
    @NonNull
    @Override
    public AttitudeViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_mine_attitude_item, group, false);
        return new AttitudeViewHolder(view);
    }
    public void  setContext(Context mContext){
        this.mContext=mContext;
    }

     public void setDatas(List<AttitudeInfo.PageDatasBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
     }
    @Override
    public void onBindViewHolder(@NonNull AttitudeViewHolder holder, int i) {
        AttitudeInfo.PageDatasBean bean = mList.get(i);
        List<AttitudeInfo.PageDatasBean.ContentImagesBean> images = bean.getContentImages();
        if (bean.getUserInfo()!=null){
            holder.user_name.setText(bean.getUserInfo().getNickName());
            ImgTools.showImageView(mContext,bean.getUserInfo().getUserHeadImg_48(),holder.user_logo);
            holder.source_comment.setImageUrl(R.id.iv_source_article,bean.getUserInfo().getUserHeadImg_48() );
        }
        TextViewUtils.setTextFakeBold(holder.user_name);
        holder.tv_content.setText(bean.getContent());
        if (bean.getAgreeCount()==0){
            holder.iv_like.setText("赞");
        }else {
            holder.iv_like.setText(CountUtil.toHuihuCount(bean.getAgreeCount()));
        }
        GridViewImageAdapter gridAdapter =new GridViewImageAdapter(mContext,images);
        if (images.size()<=3) {
            holder.gridView.setNumColumns(images.size());
        }else {
            holder.gridView.setNumColumns(3);
        }
        holder.tv_submit_time.setText(TimeFormatUtils.toHuihuTime(bean.getCreateTime())+"回答");
        holder.source_comment.setText(R.id.tv_source_article, bean.getAboutContent());
        holder.gridView.setAdapter(gridAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show("position"+position+"-----parent"+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class AttitudeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_user_name)
        TextView user_name;
        @BindView(R2.id.rv_user_logo)
        RoundImageView user_logo;
        @BindView(R2.id.tv_content)
        TextView tv_content;
        @BindView(R2.id.gridview)
        GridView gridView;
        @BindView(R2.id.iv_like)
        TextView iv_like;
        @BindView(R2.id.source_comment)
        SourceView source_comment;
        @BindView(R2.id.tv_submit_time)
        TextView tv_submit_time;

        public AttitudeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
