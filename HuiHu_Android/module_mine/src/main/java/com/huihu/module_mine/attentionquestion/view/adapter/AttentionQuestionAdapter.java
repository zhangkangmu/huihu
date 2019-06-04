package com.huihu.module_mine.attentionquestion.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.module_mine.R;
import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;
import com.huihu.module_mine.attentionquestion.view.adapterInterface.OnDeletedItemClickListen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AttentionQuestionAdapter extends RecyclerView.Adapter<AttentionQuestionAdapter.AttentionQuestionViewHold> implements View.OnClickListener {
    private Context mContext;
    private List<AttentionQuestionInfo.PageDatasBean> mPageDatasList= new ArrayList<>();
    private OnDeletedItemClickListen mOnDeletedItemClickListen;

    public void setOnDeletedItemClickListen(OnDeletedItemClickListen mOnDeletedItemClickListen) {
        this.mOnDeletedItemClickListen = mOnDeletedItemClickListen;
    }

    @NonNull
    @Override
    public AttentionQuestionViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_item_attention_question, viewGroup, false);
        view.setOnClickListener(this);
        return new AttentionQuestionAdapter.AttentionQuestionViewHold(view);
    }

    public List<AttentionQuestionInfo.PageDatasBean> geItemDatasBean(){
        return mPageDatasList;
    };

    @Override
    public void onBindViewHolder(@NonNull AttentionQuestionViewHold viewHold, int i) {
        AttentionQuestionInfo.PageDatasBean bean =mPageDatasList.get(i);
        viewHold.itemView.setTag(i);
        viewHold.title.setText(bean.getTitle());
        viewHold.content.setText(bean.getBriefContent());

        //设置删除按钮
        viewHold.icon_delete.setTag(i);
        viewHold.icon_delete.setOnClickListener(this);
        //设置时间
        long createTime = bean.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date(createTime);
        viewHold.time.setText(simpleDateFormat.format(date));
        ////设置多张图片展示的方式
        List<AttentionQuestionInfo.PageDatasBean.ImagesBean> images = bean.getImages();
        QuestionGridImageAdapter gridAdapter = new QuestionGridImageAdapter(mContext,images);
        if (images.size()<=3){
            viewHold.gridView.setNumColumns(images.size());
        }else{
            viewHold.gridView.setNumColumns(3);
        }
        viewHold.gridView.setAdapter(gridAdapter);
    }

    public void setAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> mPageDatas) {
        this.mPageDatasList = mPageDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPageDatasList.size();
    }

    public AttentionQuestionAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
        if (mOnDeletedItemClickListen!=null){
        mOnDeletedItemClickListen.onItemClick(v, (int) v.getTag(),getItemViewType((int) v.getTag()));
        }
    }

    public void removeItem(int position) {
        mPageDatasList.remove(position);
        notifyDataSetChanged();
    }

    public List<AttentionQuestionInfo.PageDatasBean> getmList() {
        return mPageDatasList;
    }

    public class AttentionQuestionViewHold extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView time;
        GridView gridView;
        ImageView icon_delete;

        public AttentionQuestionViewHold(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.tv_time);
            gridView = itemView.findViewById(R.id.gridview);
            icon_delete = itemView.findViewById(R.id.icon_delete);
        }
    }
}
