package com.huihu.module_circle.circlelist.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class CircleListAdapter extends RecyclerView.Adapter<CircleListAdapter.CircleListAdapterViewHold> {


    @NonNull
    @Override
    public CircleListAdapter.CircleListAdapterViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CircleListAdapter.CircleListAdapterViewHold circleListAdapterViewHold, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CircleListAdapterViewHold extends RecyclerView.ViewHolder{

        public CircleListAdapterViewHold(@NonNull View itemView) {
            super(itemView);
        }
    }
}
