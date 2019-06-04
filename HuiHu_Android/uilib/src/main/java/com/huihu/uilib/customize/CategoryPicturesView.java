package com.huihu.uilib.customize;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.uilib.R;
import com.huihu.uilib.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class CategoryPicturesView extends ConstraintLayout {

    @BindView(R2.id.iv_one_picture)
    ImageView ivOnePicture;
    @BindView(R2.id.iv_two_picture_one)
    ImageView ivTwoPictureOne;
    @BindView(R2.id.iv_two_picture_two)
    ImageView ivTwoPictureTwo;
    @BindView(R2.id.iv_more_picture_one)
    ImageView ivMorePictureOne;
    @BindView(R2.id.iv_more_picture_two)
    ImageView ivMorePictureTwo;
    @BindView(R2.id.iv_more_picture_three)
    ImageView ivMorePictureThree;
    @BindView(R2.id.tv_more_number)
    TextView tvMoreNumber;

    private List<String> mUrls;
    private OnImageClickListener listener;

    public CategoryPicturesView(Context context) {
        this(context, null);
    }

    public CategoryPicturesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryPicturesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * @param listener 设置内部点击监听器
     */
    public void setListner(OnImageClickListener listener) {
        this.listener = listener;
    }

    private void init(Context context){
        View v = LayoutInflater.from(context).inflate(R.layout.uilib_view_pictures_category, this, false);
        addView(v);
        ButterKnife.bind(this);
    }


    /**
     * 设置显示的图片数组的url数组
     *
     * @param urls url数组
     */
    public void setUrls(List<String> urls){
        mUrls = urls;
        if (mUrls == null || mUrls.size() == 0)  {
            showPictures(-1);
            return;
        }
        int count = mUrls.size();
        showPictures(count);
        loadImage(count);
    }


    /**
     * 根据图片数量改变显示状态
     *
     * @param count 图片数量
     */
    private void showPictures(int count){
        ivOnePicture.setVisibility(count == 1 ? VISIBLE : GONE);
        ivTwoPictureOne.setVisibility(count == 2 ? VISIBLE : GONE);
        ivTwoPictureTwo.setVisibility(count == 2 ? VISIBLE : GONE);
        ivMorePictureOne.setVisibility(count >= 3 ? VISIBLE :GONE);
        ivMorePictureTwo.setVisibility(count >= 3 ? VISIBLE :GONE);
        ivMorePictureThree.setVisibility(count >= 3 ? VISIBLE :GONE);
        tvMoreNumber.setVisibility(count > 3 ? VISIBLE :GONE);
    }

    /**
     * 根据图片数量显示相应的图片和样式
     *
     * @param count 图片数量
     */
    private void loadImage(int count){
        Context context = getContext();
        if (count == 1){
            ImgTools.showImageViewWithConner(context,mUrls.get(0),ivOnePicture,2);
        } else if (count == 2){
            ImgTools.showImageViewWithConner(context,mUrls.get(0),ivTwoPictureOne,2);
            ImgTools.showImageViewWithConner(context,mUrls.get(1),ivMorePictureTwo,2);
        } else {
            ImgTools.showImageViewWithConner(context,mUrls.get(0),ivMorePictureOne,2);
            ImgTools.showImageViewWithConner(context,mUrls.get(1),ivMorePictureTwo,2);
            ImgTools.showImageViewWithConner(context,mUrls.get(2),ivMorePictureThree,2);
            ivMorePictureThree.setColorFilter(Color.parseColor("#00000000"));
        }
        if (count > 3) {
            tvMoreNumber.setText("+" + (count - 3));
            ivMorePictureThree.setColorFilter(Color.parseColor("#4c000000"));
        }
    }


    /**
     * 点击事件用于回调
     *
     * @param view 点击的view
     */
    @OnClick({R2.id.iv_one_picture, R2.id.iv_two_picture_one, R2.id.iv_two_picture_two
            , R2.id.iv_more_picture_one, R2.id.iv_more_picture_two, R2.id.iv_more_picture_three})
    public void onClick(View view){
        int id = view.getId();
        int position = -1;
        if (id == R.id.iv_one_picture){
            position = 0;
        } else if (id == R.id.iv_two_picture_one){
            position = 0;
        } else if (id == R.id.iv_two_picture_two){
            position = 1;
        } else if (id == R.id.iv_more_picture_one){
            position = 0;
        } else if (id == R.id.iv_more_picture_two){
            position = 1;
        } else if (id == R.id.iv_more_picture_three){
            position = 2;
        }
        if (listener != null) listener.onImageClick(this, position, mUrls.get(position));
    }

    /**
     * 内部点击监听器
     */
    public interface OnImageClickListener{
        /**
         * @param v         点击的view
         * @param position  点击的位置
         * @param url       点击的图片的url
         */
        void onImageClick(View v, int position, String url);
    }

}
