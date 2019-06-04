package com.huihu.module_home.addquestioncategory.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.addquestioncategory.entitiy.CategoryModel;
import com.huihu.uilib.customize.CornerImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by ouyangjianfeng on 2019/3/27
 * description:
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;

    private List<CategoryModel> mCategoryModelList;

    private OnMyItemClickListener mListener;

    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof CategoryModel){
                if (mListener != null) mListener.onItemClick((CategoryModel) v.getTag());
            }
        }
    };

    public CategoryAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<CategoryModel> categoryModelList) {
        this.mCategoryModelList = categoryModelList;
    }

    public void setListener(OnMyItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_category, group, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int i) {
        final CategoryModel categoryModel = mCategoryModelList.get(i);
        holder.mTvName.setText(categoryModel.getCategory());
        ImgTools.showImageView(mContext, categoryModel.getPicture(), holder.mIvAvatar);
        if (categoryModel.isSelected()) {
            holder.mTvAdd.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_add_selected_4));
            holder.mTvAdd.setTextColor(mContext.getResources().getColor(R.color.module_home_text_added));
            holder.mTvAdd.setText(mContext.getResources().getString(R.string.module_home_text_added));
            holder.mTvAdd.setEnabled(false);
        } else {
            holder.mTvAdd.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_add_bg_4));
            holder.mTvAdd.setTextColor(mContext.getResources().getColor(R.color.module_home_white));
            holder.mTvAdd.setText(mContext.getResources().getString(R.string.module_home_text_add));
            holder.mTvAdd.setEnabled(true);
        }
        holder.mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAddClick(categoryModel);
                }
            }
        });
        holder.itemView.setTag(categoryModel);
        holder.itemView.setOnClickListener(itemOnClick);
    }


    @Override
    public int getItemCount() {
        return mCategoryModelList == null ? 0 : mCategoryModelList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.iv_avatar)
        CornerImageView mIvAvatar;
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_add)
        TextView mTvAdd;

        CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnMyItemClickListener {
        void onItemClick(CategoryModel categoryModel);

        void onAddClick(CategoryModel categoryModel);//添加
    }
}
