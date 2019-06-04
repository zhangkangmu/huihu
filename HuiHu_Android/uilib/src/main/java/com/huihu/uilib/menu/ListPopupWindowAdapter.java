package com.huihu.uilib.menu;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.R;

/**
 * create by ouyangjianfeng on 2019/3/30
 * description:
 */
public class ListPopupWindowAdapter extends BaseAdapter {

    private Context mContext;

    private String[] sortStrings = new String[]{};

    private HuihuMenu.OnItemClickListener listener;

    private int selectPosition = -1;
    private ListPopupWindow popupWindow;


    public ListPopupWindowAdapter(Context context, HuihuMenu.OnItemClickListener listener, ListPopupWindow popupWindow) {
        mContext = context;
        this.listener = listener;
        this.popupWindow = popupWindow;
    }

    public void setData(String[] sortStrings) {
        this.sortStrings = sortStrings;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    @Override
    public int getCount() {
        return sortStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.module_uilib_item_listpopupwindow, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(sortStrings[position]);
        holder.clContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick(position, sortStrings[position]);
                selectPosition = position;
                notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });
        holder.imageView.setVisibility(selectPosition == position ? View.VISIBLE : View.INVISIBLE);
        return convertView;
    }

    private class ViewHolder {

        private TextView mTextView;
        private ImageView imageView;
        private ConstraintLayout clContent;
        private ViewHolder(View view) {
            mTextView = view.findViewById(R.id.tv_sort_name);
            imageView = view.findViewById(R.id.iv_select);
            clContent = view.findViewById(R.id.cl_content);
        }
    }
}
