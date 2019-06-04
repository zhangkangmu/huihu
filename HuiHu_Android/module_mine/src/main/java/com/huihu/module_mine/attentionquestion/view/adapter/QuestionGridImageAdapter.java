package com.huihu.module_mine.attentionquestion.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_mine.R;
import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;

import java.util.List;

import static com.huihu.uilib.util.DensityUtil.dip2px;

public class QuestionGridImageAdapter extends BaseAdapter {
    Context mContext;
    List<AttentionQuestionInfo.PageDatasBean.ImagesBean> imgList;

    public QuestionGridImageAdapter(Context mContext, List<AttentionQuestionInfo.PageDatasBean.ImagesBean> images) {
        this.mContext = mContext;
        this.imgList=images;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuestionGridImageAdapter.ViewHodler viewHodler;
        if (convertView == null) {
            viewHodler = new QuestionGridImageAdapter.ViewHodler();
            convertView = View.inflate(mContext, R.layout.module_mine_gridview_item, null);
            viewHodler.imageView = (ImageView) convertView.findViewById(R.id.img);
            //根据图片的数量设置图片的大小，这里的大小是写的具体数值，也可以获得手机屏幕的尺寸来设置图片的大小
            if (imgList.size()==1){
                ViewGroup.LayoutParams params = viewHodler.imageView.getLayoutParams();
                params.height =dip2px(mContext,188);
                params.width = dip2px(mContext,355);
                viewHodler.imageView.setLayoutParams(params);
            }else if (imgList.size()==2){
                ViewGroup.LayoutParams params = viewHodler.imageView.getLayoutParams();
                params.height = dip2px(mContext,124);
                params.width = dip2px(mContext,166);
                viewHodler.imageView.setLayoutParams(params);
            }else{ ViewGroup.LayoutParams params = viewHodler.imageView.getLayoutParams();
                params.height = dip2px(mContext,82);
                params.width = dip2px(mContext,109);
                viewHodler.imageView.setLayoutParams(params);
            } convertView.setTag(viewHodler);
        }else{
            viewHodler =(QuestionGridImageAdapter.ViewHodler) convertView.getTag();
        }
        ImgTools.showImageView(mContext,imgList.get(position).getImageUrl(),viewHodler.imageView);
        return convertView;
    }
    class ViewHodler{
        ImageView imageView;
    }
}
