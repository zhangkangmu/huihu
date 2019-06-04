package com.huihu.module_mine.selectcountry.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.module_mine.R;
import com.huihu.module_mine.selectcountry.entity.CountryModel;

import java.util.List;

/**
 * create by ouyangjianfeng on 2019/3/26
 * description:
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context mContext;

    private int mSelectedCode;
    private List<CountryModel> mCountryModelList;

    private OnMyItemClickListener mListener;

    public CountryAdapter(Context context, int selectedCode) {
        mContext = context;
        this.mSelectedCode = selectedCode;
    }

    public void setCountryModelList(List<CountryModel> countryModelList) {
        mCountryModelList = countryModelList;
    }

    public void setListener(OnMyItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_mine_item_country, group, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CountryViewHolder holder, final int i) {
        final CountryModel countryModel = mCountryModelList.get(i);
        holder.tv_country.setText(countryModel.getName());
        holder.tv_country.setTextColor(mContext.getResources().getColor(mSelectedCode == countryModel.getCode() ? R.color.global_blue : R.color.text_black));
        holder.v_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mSelectedCode = mCountryModelList.get(i).getCode();
                    String regionCode = mCountryModelList.get(i).getRegionCode();
                    notifyDataSetChanged();
                    mListener.onItemClick(mSelectedCode, regionCode);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCountryModelList == null ? 0 : mCountryModelList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_country;
        private ConstraintLayout v_root;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_country = itemView.findViewById(R.id.tv_country);
            v_root = itemView.findViewById(R.id.root);
        }
    }

    public interface OnMyItemClickListener {
        void onItemClick(int countryCode, String regionCode);
    }
}
