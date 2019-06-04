package com.huihu.module_home.popularIdea.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.popularIdea.enity.ItemType;
import com.huihu.module_home.popularIdea.enity.PopularIdeaData;
import com.huihu.module_home.popularIdea.view.listener.PopularAdapterListener;
import com.huihu.module_home.popularIdea.view.listener.PopularItemClickListener;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * create by ouyangjianfeng on 2019/3/14
 * description:首页热榜adapter
 * 也有关于三个类型
 * 讨论，问，答
 */
public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.HotRankViewHolder> implements View.OnClickListener {
    private List<PopularIdeaData.PageDatasBean> mPopularDataBeans = new ArrayList<>();
    private Context mContext;
    private int TYPE_IDEA1 = 1;
    private int TYPE_IDEA2 = 2;
    private int TYPE_IDEA3 = 3;
    private int TYPE_IDEASCROLLED = 4;
    private PopularAdapterListener mListener;
    private PopularItemClickListener mpopularItemClickListener = null;

    public void setPopularItemClickListener(PopularItemClickListener mpopularItemClickListener) {
        this.mpopularItemClickListener = mpopularItemClickListener;
    }

    public void setPopularAdapterCallBack(PopularAdapterListener mListener) {
        this.mListener = mListener;
    }

    public List<PopularIdeaData.PageDatasBean> getmPopularDataBeans() {
        return mPopularDataBeans;
    }

    public void setmPopularDataBeans(Context mContext, List<PopularIdeaData.PageDatasBean> mPopularDataBeans) {
        this.mPopularDataBeans = mPopularDataBeans;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotRankViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        //three type about the item
        View view = null;
        if (viewType == TYPE_IDEA1) {
            //question
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_popular_question, group, false);
        } else if (viewType == TYPE_IDEA2) {
            //answer
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_popular_answer, group, false);
        } else if (viewType == TYPE_IDEA3) {
            //discution
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_popular_discution, group, false);
        } else {
            view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_popular_scrolled, group, false);
        }
        view.setOnClickListener(this);
        return new HotRankViewHolder(view, viewType);
    }


    @Override
    public int getItemViewType(int position) {
        //类型(1:问题 2:回答 3:讨论)
        int ideaType = mPopularDataBeans.get(position).getIdeaType();
//            if (position==1){
//                return TYPE_IDEASCROLLED;
//            } else
        if (ideaType == 1) {
            return ItemType.question;
        } else if (ideaType == 2) {
            return ItemType.answer;
        } else if (ideaType == 3) {
            return ItemType.discuss;
        }
        return super.getItemViewType(position);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull HotRankViewHolder holder, final int i) {
        //动态添加子布局
        holder.itemView.setTag(i);
        View childView = null;
        //不是滚动的发现人的时候
        PopularIdeaData.PageDatasBean dateBean = mPopularDataBeans.get(i);
        if (dateBean.getUserInfo()==null){
            return;
        }
        List<PopularIdeaData.PageDatasBean.ImagesBean> images = dateBean.getImages();
        if (mPopularDataBeans.get(i).getIdeaType() == TYPE_IDEA1) {
            holder.tv_one.setText(CountUtil.toHuihuCount(dateBean.getAnswerCount()) + "回答");
            holder.tv_two.setText(CountUtil.toHuihuCount(dateBean.getFollowCount()) + "关注");
            holder.tv_question.setText(dateBean.getUserInfo().getNickName() + " · " + TimeFormatUtils.toHuihuTime(dateBean.getPopularTime()) + "提问");
            if (dateBean.getAnswer() == 1) {
                holder.rl_home_answer.setVisibility(View.GONE);
            } else {
                holder.rl_home_answer.setVisibility(View.VISIBLE);
            }
            holder.rl_home_answer.setTag(i);
            holder.rl_home_answer.setOnClickListener(this);
            ImgTools.showImageView(mContext,dateBean.getUserInfo().getUserHeadImage(),holder.iv_user);
            holder.cons_question.setTag(i);
            holder.cons_question.setOnClickListener(this);
        } else if (mPopularDataBeans.get(i).getIdeaType() == TYPE_IDEA2) {
            holder.tv_one.setText(CountUtil.toHuihuCount(dateBean.getAgreeCount()) + mContext.getString(R.string.agree));
            holder.tv_two.setText(CountUtil.toHuihuCount(dateBean.getCommentCount()) + mContext.getString(R.string.comment));
            holder.tv_attention.setOnClickListener(this);
            holder.tv_attention.setTag(i);
            if (dateBean.getUserInfo().getFollow() == 0) {
                holder.tv_attention.setText("关注Ta");
                holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
                holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_litterwhite));
            } else {
                holder.tv_attention.setText("已关注");
                holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_blue));
                holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
            }
            holder.tv_question.setText(dateBean.getCategory());
            ImgTools.showImageView(mContext,dateBean.getUserInfo().getUserHeadImage(),holder.iv_user_answer);
            holder.tv_answer_name.setText(dateBean.getUserInfo().getNickName());
            holder.hot_time.setText(TimeFormatUtils.toHuihuTime(dateBean.getPopularTime()) + "回答");
            holder.cons_answer.setTag(i);
            holder.cons_answer.setOnClickListener(this);
        } else if (mPopularDataBeans.get(i).getIdeaType() == TYPE_IDEA3) {
            //关于朋友圈的
            holder.tv_one.setText(CountUtil.toHuihuCount(dateBean.getAgreeCount()) + "赞同");
            holder.tv_two.setText(CountUtil.toHuihuCount(dateBean.getFollowCount()) + "关注");
            ImgTools.showImageView(mContext,dateBean.getUserInfo().getUserHeadImage(),holder.iv_user_community);
            holder.tv_community.setText(dateBean.getUserInfo().getNickName() + " · "+ TimeFormatUtils.toHuihuTime(dateBean.getPopularTime()) + "发布");
            holder.tv_communityName.setText(dateBean.getCircleName());
            holder.cons_disscution.setTag(i);
            holder.cons_disscution.setOnClickListener(this);
        }
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext, images);
        if (images.size() <= 3) {
            holder.gridview.setNumColumns(images.size());
        } else {
            holder.gridview.setNumColumns(3);
        }
        holder.gridview.setAdapter(gridAdapter);
        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show("position" + position + "-----parent" + i);
            }
        });
        if (dateBean.getTitle()!=null)
        holder.tv_title.setText(dateBean.getTitle());
        TextViewUtils.setTextFakeBold(holder.tv_title);
        holder.iv_delete.setTag(i);
        holder.iv_delete.setOnClickListener(this);
        holder.iv_share.setTag(i);
        holder.iv_share.setOnClickListener(this);
//        else {
//            //横向的recycleView
//            ArrayList<ItemV0> list= new ArrayList<>();
//            for (int j = 0; j <4 ; j++) {
//                ItemV0 itemV0 = new ItemV0();
//                itemV0.setNumber(j);
//                list.add(itemV0);
//            }
//            //设置布局管理器
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//布局横向
//            holder.recyclerView_h.setLayoutManager(linearLayoutManager);
//            HorizontalAdapter horizontalAdapter = new HorizontalAdapter(list);
//            horizontalAdapter.setContext(mContext);
//            View view = View.inflate(mContext, R.layout.module_home_item_footer, null);
//            holder.recyclerView_h.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
//                @Override
//                public void onLoadMore() {
//                    mListener.scrollCallBack();
//
//                }
//            });
//            holder.recyclerView_h.setAdapter(horizontalAdapter);
//            horizontalAdapter.addFooterView(view);
//        }
    }

    @Override
    public int getItemCount() {
        return mPopularDataBeans.size();
    }


    @Override
    public void onClick(View v) {
        if (mpopularItemClickListener != null)
            mpopularItemClickListener.popularItemClick(v, (int) v.getTag(), getItemViewType((int) v.getTag()));
    }

    public class HotRankViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_one;
        private TextView tv_two;
        private RoundImageView iv_user;
        private TextView tv_question;
        private TextView tv_title;
        private RoundImageView iv_user_answer;
        private TextView tv_answer_name;
        private RoundImageView iv_user_community;
        private TextView tv_community;
        private View rl_home_answer;
        private TextView tv_communityName;
        private RecyclerView recyclerView_h;
        private ImageView iv_share;
        private GridView gridview;
        private TextView hot_time;
        private TextView tv_attention;
        private ImageView iv_delete;
        private ConstraintLayout cons_question;
        private ConstraintLayout cons_answer;
        private ConstraintLayout cons_disscution;

        public HotRankViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == TYPE_IDEA1) {
                iv_user = itemView.findViewById(R.id.iv_popular);
                tv_question = itemView.findViewById(R.id.tv_question);
                rl_home_answer = itemView.findViewById(R.id.rl_home_answer);
                gridview = itemView.findViewById(R.id.gridview);
                tv_one = itemView.findViewById(R.id.tv_popular_bottom_one);
                tv_two = itemView.findViewById(R.id.tv_popular_bottom_two);
                tv_title = itemView.findViewById(R.id.tv_title);
                iv_share = itemView.findViewById(R.id.share);
                iv_delete=itemView.findViewById(R.id.iv_delete);
                cons_question=itemView.findViewById(R.id.cons_question);
            } else if (viewType == TYPE_IDEA2) {
                iv_user_answer = itemView.findViewById(R.id.roundImage);
                tv_answer_name = itemView.findViewById(R.id.tv_answer_name);
                gridview = itemView.findViewById(R.id.gridview);
                tv_one = itemView.findViewById(R.id.tv_popular_bottom_one);
                tv_two = itemView.findViewById(R.id.tv_popular_bottom_two);
                tv_title = itemView.findViewById(R.id.tv_title);
                iv_share = itemView.findViewById(R.id.share);
                hot_time = itemView.findViewById(R.id.hot_time);
                tv_attention = itemView.findViewById(R.id.tv_attention);
                iv_delete=itemView.findViewById(R.id.iv_delete);
                tv_question = itemView.findViewById(R.id.tv_question);
                cons_answer=itemView.findViewById(R.id.cons_answer);
            } else if (viewType == TYPE_IDEA3) {
                iv_user_community = itemView.findViewById(R.id.iv_user_community);
                tv_community = itemView.findViewById(R.id.tv_community);
                tv_communityName = itemView.findViewById(R.id.tv_communityName);
                gridview = itemView.findViewById(R.id.gridview);
                tv_one = itemView.findViewById(R.id.tv_popular_bottom_one);
                tv_two = itemView.findViewById(R.id.tv_popular_bottom_two);
                tv_title = itemView.findViewById(R.id.tv_title);
                iv_share = itemView.findViewById(R.id.share);
                iv_delete=itemView.findViewById(R.id.iv_delete);
                cons_disscution=itemView.findViewById(R.id.cons_disscution);
            } else {
                recyclerView_h = itemView.findViewById(R.id.recyclerView_h);
            }

        }
    }
}
