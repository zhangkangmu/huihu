package com.huihu.uilib.commentedit.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.uilib.R;
import com.huihu.uilib.commentedit.adapter.ImageAdapter;
import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditPresenter;
import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditView;
import com.huihu.uilib.commentedit.entity.ImagesBean;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;
import com.huihu.uilib.commentedit.presenter.ImpCommentEditPresenter;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangwensong on 2019/4/2.
 * description：
 */
public class CommentEditDialog extends AppCompatDialog implements ICommentEditView, View.OnClickListener {
    private static final String TAG = "CommentEditDialog";
    private static final int MAX_IAMGE = 9;

    ICommentEditPresenter iCommentEditPresenter = new ImpCommentEditPresenter(this);


    private LinearLayout mLlEditDialog;
    private RecyclerView mRvImages;
    private ImageAdapter mImageAdapter;
    private ImageView mIvEnlarge;
    private EditText mEtEdit;
    private ImageView mIvSelectPicture;
    private TextView mTvPublish;
    private TextView mTvReplyName;
    private TextView mTvEdit;

    private List<AlbumImageBean> mAlbumImageBeans;
    private PublishCommentBean mPublishCommentBean;
    private String mAtNickName;

    private boolean mIsEnlarge = false;

    private AlbumActivity.OnSelectEndListener mOnSelectEndListener;
    private PublishCommentCallBack mCallBack;


    public static CommentEditDialog newInstance(Context context, PublishCommentBean publishCommentBean,String atNickName,PublishCommentCallBack callBack) {
        CommentEditDialog fragment = new CommentEditDialog(context, publishCommentBean, atNickName, callBack);
        return fragment;
    }

    public CommentEditDialog(@NonNull Context context, PublishCommentBean publishCommentBean, String atNickName,PublishCommentCallBack callBack) {
        super(context);
        if (publishCommentBean != null){
            mPublishCommentBean = publishCommentBean;
        }
        mAtNickName = atNickName;
        mCallBack = callBack;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uilib_view_comment_edit);
        setLayout();
        initView();
    }

    private void initView(){
        mIvEnlarge = findViewById(R.id.iv_enlarge);
        mIvSelectPicture = findViewById(R.id.iv_select_picture);
        mEtEdit = findViewById(R.id.et_comment_reply);
        mLlEditDialog = findViewById(R.id.ll_edit_dialog);
        mRvImages = findViewById(R.id.rv_images);
        mTvPublish = findViewById(R.id.tv_publish);
        mTvReplyName = findViewById(R.id.tv_reply_name);
        mTvEdit = findViewById(R.id.tv_edit);

        mTvPublish.setOnClickListener(this);
        mIvSelectPicture.setOnClickListener(this);
        mIvEnlarge.setOnClickListener(this);

        mEtEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    mTvPublish.setTextColor(getContext().getResources().getColor(R.color.virtual_blue));
                }else if(s.length() > 0) {
                    mTvPublish.setClickable(true);
                    mTvPublish.setTextColor(getContext().getResources().getColor(R.color.global_blue));
                }else if (s.length() > 1000){
                    mTvPublish.setClickable(false);
                    mTvPublish.setTextColor(getContext().getResources().getColor(R.color.virtual_blue));
                }
            }
        });

        mOnSelectEndListener = new AlbumActivity.OnSelectEndListener() {
            @Override
            public void onSelectEnd(List<AlbumImageBean> beanList) {
                if (mImageAdapter == null && beanList.size() != 0){
                    mAlbumImageBeans = beanList;
                    mImageAdapter = new ImageAdapter(getContext(),mAlbumImageBeans);
                    mImageAdapter.setOnClickListener(CommentEditDialog.this);
                }else {
                    mAlbumImageBeans.addAll(beanList);
                }

                if (mAlbumImageBeans.size() != 0){
                    mRvImages.setVisibility(View.VISIBLE);
                    mRvImages.setAdapter(mImageAdapter);
                    mTvPublish.setTextColor(getContext().getResources().getColor(R.color.global_blue));
                }
            }
        };


        mTvEdit.setText("");
        //回复名
        if(mAtNickName != null){
            mTvReplyName.setVisibility(View.VISIBLE);
            mTvReplyName.setText(mAtNickName);
        }else {
            mTvReplyName.setVisibility(View.GONE);
        }
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getWindow().setBackgroundDrawableResource(android.R.color.white);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Log.d(TAG, "onclick " + viewId);
        if(viewId == R.id.iv_enlarge){
            if(!mIsEnlarge){
                ViewGroup.LayoutParams params = mLlEditDialog.getLayoutParams();
                int editDialogHeight = params.height;
                params.height = (int)(19.0/11 * editDialogHeight);
                mLlEditDialog.setLayoutParams(params);
                mIsEnlarge = true;
                mIvEnlarge.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getResources().getDrawable(R.drawable.module_home_icon_smaller)));
            }else if(mIsEnlarge){
                ViewGroup.LayoutParams params = mLlEditDialog.getLayoutParams();
                int editDialogHeight = params.height;
                params.height = (int)(11.0/19 * editDialogHeight);
                mLlEditDialog.setLayoutParams(params);
                mIsEnlarge = false;
                mIvEnlarge.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getResources().getDrawable(R.drawable.module_home_icon_enlarge)));
            }

        }else if (viewId == R.id.iv_select_picture){
            AlbumActivity.openSelf(getContext(),MAX_IAMGE, mOnSelectEndListener);
        }else if(viewId == R.id.tv_publish){
            //发表评论
            if (mAlbumImageBeans == null || mAlbumImageBeans.size() == 0){
                if(mEtEdit.getText().length() == 0){
                    ToastUtil.show(R.string.uilib_comment_write_in);
                    return;
                }
                mPublishCommentBean.setComment(mEtEdit.getText().toString());
                iCommentEditPresenter.v2pPublicComment(mPublishCommentBean);
                return;
            }
            if(mAlbumImageBeans != null && mAlbumImageBeans.size() > MAX_IAMGE){
                ToastUtil.show(R.string.uilib_comment_iamge_limite);
                return;
            }
            iCommentEditPresenter.v2pPostCommentImage(mAlbumImageBeans);

        }else if(viewId == R.id.iv_image_cancle){
            int position = (int) v.getTag();
            mAlbumImageBeans.remove(position);
            if (mAlbumImageBeans.size() == 0){
                mRvImages.setVisibility(View.GONE);
                mTvPublish.setTextColor(getContext().getResources().getColor(R.color.virtual_blue));
            }
            mImageAdapter.setDatas(mAlbumImageBeans);
        }
    }

    public  interface PublishCommentCallBack{
        void onPublishCommentCompleted(PublishCommentBean bean1,ReturnCommentBean bean2,String  atName);
    }


    @Override
    public void p2vPublicCommentSuccess(ReturnCommentBean bean) {

        ToastUtil.show("评论成功");
        if (mCallBack != null){
            mCallBack.onPublishCommentCompleted(mPublishCommentBean,bean, mAtNickName);
        }
        dismiss();
    }

    @Override
    public void p2vPublicCommentError(String error) {
        ToastUtil.show(error);
    }

    @Override
    public void p2vPublicCommentCompleted() {


    }

    @Override
    public void p2vPostCommentImageSuccess(List<AlbumImageBean> list) {
        if(list != null && list.size() > 0){
            List<ImagesBean> imagesBeans = new ArrayList<>();
            for(AlbumImageBean bean : list){
                ImagesBean imagesBean = new ImagesBean();
                imagesBean.setImageUrl(bean.getUrl());
                imagesBeans.add(imagesBean);
                //TODO 未设置宽高
            }
            mPublishCommentBean.setImages(imagesBeans);
        }
        mPublishCommentBean.setComment(mEtEdit.getText().toString());
        //其它值作为参数
        iCommentEditPresenter.v2pPublicComment(mPublishCommentBean);
    }

    @Override
    public void p2vPostCommentImageFail(String fail) {

    }
}
