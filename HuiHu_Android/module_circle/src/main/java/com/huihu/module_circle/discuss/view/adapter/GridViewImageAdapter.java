package com.huihu.module_circle.discuss.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_circle.R;
import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;

import com.huihu.uilib.customize.CornerImageView;

import java.util.ArrayList;
import java.util.List;

import static com.huihu.uilib.util.DensityUtil.dip2px;

public class GridViewImageAdapter extends BaseAdapter{
    Context context;
    List<RecemendDiscussInfo.PageDatasBean.ImagesBean> imgList=new ArrayList<>();
    GridViewImageAdapter(Context context, List<RecemendDiscussInfo.PageDatasBean.ImagesBean> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.module_circle_gridview_item, null);
            viewHodler.imageView = (CornerImageView) convertView.findViewById(R.id.img);
            //根据图片的数量设置图片的大小，这里的大小是写的具体数值，也可以获得手机屏幕的尺寸来设置图片的大小
            if (imgList.size()==1){
                ViewGroup.LayoutParams params = viewHodler.imageView.getLayoutParams();
                params.height =dip2px(context,188);
                params.width = dip2px(context,335);
                viewHodler.imageView.setLayoutParams(params);
            }else if (imgList.size()==2){
                ViewGroup.LayoutParams params = viewHodler.imageView.getLayoutParams();
                params.height = dip2px(context,124);
                params.width = dip2px(context,165);
                viewHodler.imageView.setLayoutParams(params);
            }else{ ViewGroup.LayoutParams params = viewHodler.imageView.getLayoutParams();
                params.height = dip2px(context,82);
                params.width = dip2px(context,109);
                viewHodler.imageView.setLayoutParams(params);
            }
            convertView.setTag(viewHodler);
        }else{
            viewHodler =(ViewHodler) convertView.getTag();
        }
        viewHodler.imageView.setCornerRadius(2);
        ImgTools.showImageView(context,imgList.get(position).getImageUrl(),viewHodler.imageView);

        return convertView;
}




    class ViewHodler{
        CornerImageView imageView;
          }


}

