package com.huihu.module_mine.answered.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.answered.entity.AnsweredInfo;
import com.huihu.module_mine.answered.entity.AnsweredInfo.PageDatasBean.ImagesBean;
import com.huihu.module_mine.answered.view.adapterInterface.AnswerItemClickListener;
import com.huihu.uilib.util.CountUtil;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ouyangjianfeng
 * @time 2019/2/28  15:09
 * @desc 提问和回答的item, 首页，圈子，个人中心都可复用
 */
public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerViewHolder> implements View.OnClickListener {

    private Context mContext;
    private AnswerItemClickListener mAnswerListAdapter;

    public void setAnswerListAdapter(AnswerItemClickListener mAnswerListAdapter){
        this.mAnswerListAdapter=mAnswerListAdapter;
    }
    public void setmContext(Context mContext){
        this.mContext=mContext;
    }
    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_mine_answer_item, group, false);
        return new AnswerViewHolder(view);
    }

    public List<AnsweredInfo.PageDatasBean> getmList() {
        return mList;
    }

    private List<AnsweredInfo.PageDatasBean> mList= new ArrayList<>();
     public void setDatas(List<AnsweredInfo.PageDatasBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
     }
    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int i) {
         holder.itemView.setTag(i);
         holder.itemView.setOnClickListener(this);
        holder.title.setText(mList.get(i).getTitle());
        holder.content.setText(mList.get(i).getBriefContent());
        holder.content.setTag(i);
        holder.content.setOnClickListener(this);
        holder.iv_answer_bottom_three.setTag(i);
        holder.iv_answer_bottom_three.setOnClickListener(this);
        holder.tv_answer_bottom_three.setTag(i);
        holder.tv_answer_bottom_three.setOnClickListener(this);
        holder.rl_mine_answer.setTag(i);
        holder.rl_mine_answer.setOnClickListener(this);
        AnsweredInfo.PageDatasBean bean = mList.get(i);
        int ideaType = bean.getIdeaType();
        int state = bean.getState();
        List<ImagesBean> images = bean.getImages();
        if (state==1){
            holder.iv_answer_bottom_three.setImageDrawable(mContext.getDrawable(R.drawable.module_mine_icon_share));
            holder.tv_answer_bottom_three.setText("");
            holder.tv_answer_bottom_three.setTextColor(mContext.getResources().getColor(R.color.module_mine_black));
        }else if(state==2){
            holder.iv_answer_bottom_three.setImageDrawable(mContext.getDrawable(R.drawable.module_mine_error_def));
            holder.tv_answer_bottom_three.setTextColor(mContext.getResources().getColor(R.color.commonlib_red_warning));
            holder.tv_answer_bottom_three.setText("违规下架");
        }else if(state==3){
            holder.tv_answer_bottom_three.setTextColor(mContext.getResources().getColor(R.color.module_mine_yellow));
            holder.iv_answer_bottom_three.setImageDrawable(mContext.getDrawable(R.drawable.module_mine_icon_review_32_def));
            holder.tv_answer_bottom_three.setText("重新审核中");
        }
        //ideaType 1问题2回答
        if (ideaType==1){
            holder.rl_mine_answer.setVisibility(View.VISIBLE);
            holder.tv_popular_bottom_one.setText(CountUtil.toHuihuCount(bean.getAnswerCount())+"回答");
            holder.tv_popular_bottom_two.setText(CountUtil.toHuihuCount(bean.getFollowCount())+"关注");
        }else {
            holder.rl_mine_answer.setVisibility(View.GONE);
            holder.tv_popular_bottom_one.setText(CountUtil.toHuihuCount(bean.getAgreeCount())+"赞同");
            holder.tv_popular_bottom_two.setText(CountUtil.toHuihuCount(bean.getCommentCount())+"评论");
        }
        if (bean.getPopular()==1){
            holder.isHot.setVisibility(View.VISIBLE);
        }else {
            holder.isHot.setVisibility(View.GONE);
        }
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext,images);
        if (images.size()<=3) {
            holder.gridView.setNumColumns(images.size());
        }else {
            holder.gridView.setNumColumns(3);
        }
        holder.gridView.setAdapter(gridAdapter);
        TextViewUtils.setTextFakeBold( holder.title);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mAnswerListAdapter.onAnswerItemClick(v,(int)v.getTag(),getItemViewType((int)v.getTag()));
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {

         @BindView(R2.id.tv_title)
         TextView title;
         @BindView(R2.id.tv_content)
         TextView content;
         @BindView(R2.id.iv_answer_bottom_three)
         ImageView iv_answer_bottom_three;
         @BindView(R2.id.tv_answer_bottom_three)
         TextView tv_answer_bottom_three;
         @BindView(R2.id.isHot)
         ImageView isHot;
         @BindView(R2.id.rl_mine_answer)
         View rl_mine_answer;
         @BindView(R2.id.tv_popular_bottom_one)
         TextView tv_popular_bottom_one;
         @BindView(R2.id.tv_popular_bottom_two)
         TextView tv_popular_bottom_two;
         @BindView(R2.id.gridview)
         GridView gridView;
        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
