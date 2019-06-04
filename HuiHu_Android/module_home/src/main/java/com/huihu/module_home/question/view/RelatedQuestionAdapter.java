package com.huihu.module_home.question.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.module_home.R;

/**
 * create by ouyangjianfeng on 2019/4/18
 * description:
 */
public class RelatedQuestionAdapter extends RecyclerView.Adapter<RelatedQuestionAdapter.RelatedQuestionViewHolder> {

    private Context mContext;

    public RelatedQuestionAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RelatedQuestionAdapter.RelatedQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.module_home_item_related_question, group, false);
        return new RelatedQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedQuestionAdapter.RelatedQuestionViewHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 13;
    }

    class RelatedQuestionViewHolder extends RecyclerView.ViewHolder {

        public RelatedQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
