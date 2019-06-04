package com.huihu.module_home.answerdetail.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_home.R;
import com.huihu.module_home.answerdetail.entity.AnswerInfo;
import com.huihu.module_home.answerdetail.view.adapterInterface.OnAnswerDetailItemClickListener;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

/**
 * create by ouyangjianfeng on 2019/3/14
 * description:首页热榜adapter
 *
 */
public class AnswerDetailAdapter extends RecyclerView.Adapter<AnswerDetailAdapter.ViewHolder>implements View.OnClickListener {

    /**
     * 数据类型有三种
     * ideaType 1 2 3
     * */
    private int TYPE_HEADER=1;
    private int TYPE_BODY=2;
    private int TYPE_FOOTER=3;
    private Context mContext;
    private List<AnswerInfo> mList=new ArrayList<>();

    public List<AnswerInfo> getmList() {
        return mList;
    }

    public void setmList(List<AnswerInfo> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    private OnAnswerDetailItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnAnswerDetailItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setContext(Context mContext){
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View view = null;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_fragment_answer_detail_item, group, false);
        } else{
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_answer_content, group, false);
        }
        return new ViewHolder(view,viewType);
    }

    @Override
    public int getItemViewType(int position) {
     //   if(position == 0){
       //     return TYPE_HEADER;
//        } else if(position == getItemCount() + 1){
//            return TYPE_FOOTER;
//        } else{
          return TYPE_BODY;
//        }
       // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
    if (getItemViewType(i)==TYPE_HEADER){
        if (mList.size()!=0)
        holder.tv_question_title.setText(mList.get(0).getTitle());
    }else if (getItemViewType(i)==TYPE_BODY){
        AnswerInfo answerInfo = mList.get(i);
        ImgTools.showImageView(mContext,answerInfo.getUserInfo().getUserHeadImg_48(),holder.iv_avator);
        holder.richEditor.setHtml(answerInfo.getContent());
        holder.tv_user_name.setText(answerInfo.getUserInfo().getNickName());
        holder.download_count_item.setText(CountUtil.toHuihuCount(answerInfo.getOpposeCount()));
        holder.collect_count_item.setText(CountUtil.toHuihuCount(answerInfo.getCollection()));
        holder.agree_count_item.setText(CountUtil.toHuihuCount(answerInfo.getAgreeCount()));
        holder.commemnt_count_item.setText(CountUtil.toHuihuCount(answerInfo.getCommentCount()));
        holder.tv_answer_time.setText(TimeFormatUtils.toHuihuTime(answerInfo.getEditTime()));
        if (answerInfo.getUserInfo().getFollow()==0){
            holder.tv_answer_attention.setBackground(mContext.getDrawable(R.drawable.module_home_shape_blue));
            holder.tv_answer_attention.setText("关注TA");
            holder.tv_answer_attention.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.tv_answer_attention.setBackground(mContext.getDrawable(R.drawable.module_home_shape_litterwhite));
            holder.tv_answer_attention.setText("已关注");
            holder.tv_answer_attention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
        }
        holder.tv_answer_attention.setTag(i);
        holder.tv_answer_attention.setOnClickListener(this);
    }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onAnswerDetailItemClick(v,(int) v.getTag(),getItemViewType((int) v.getTag()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_question_title;
        private RoundImageView iv_avator;
        private RichEditor richEditor;
        private TextView tv_user_name;
        private TextView commemnt_count_item;
        private TextView collect_count_item;
        private TextView agree_count_item;
        private TextView download_count_item;
        private TextView tv_answer_time;
        private TextView tv_answer_attention;
        public ViewHolder(@NonNull View itemView,int viewType) {
            super(itemView);
            if(viewType==TYPE_HEADER){
                tv_question_title=itemView.findViewById(R.id.tv_question_title);
            }else if (viewType==TYPE_BODY){
                iv_avator=itemView.findViewById(R.id.iv_avator);
                richEditor=itemView.findViewById(R.id.richEditor);
                tv_user_name=itemView.findViewById(R.id.tv_answer_name);
                commemnt_count_item=itemView.findViewById(R.id.comment_count_item);
                collect_count_item=itemView.findViewById(R.id.collect_count_item);
                agree_count_item=itemView.findViewById(R.id.agree_count_item);
                download_count_item=itemView.findViewById(R.id.download_count_item);
                tv_answer_time=itemView.findViewById(R.id.tv_answer_time);
                tv_answer_attention=itemView.findViewById(R.id.tv_answer_attention);
            }

        }
    }
}
