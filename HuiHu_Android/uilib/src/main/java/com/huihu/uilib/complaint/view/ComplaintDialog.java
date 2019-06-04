package com.huihu.uilib.complaint.view;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailun.wangjing.permissionlibrary.annotation.RequestPermission;
import com.google.android.flexbox.FlexboxLayout;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.uilib.R;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;
import com.huihu.uilib.complaint.adapter.ImageAdapter;
import com.huihu.uilib.complaint.complaintinterface.IComplaintPresenter;
import com.huihu.uilib.complaint.complaintinterface.IComplaintView;
import com.huihu.uilib.complaint.entity.ComplaintBean;
import com.huihu.uilib.complaint.entity.LabelBean;
import com.huihu.uilib.complaint.entity.ReportTag;
import com.huihu.uilib.complaint.presenter.ImpComplaintPresenter;

import java.util.ArrayList;
import java.util.List;

public class ComplaintDialog extends Dialog implements IComplaintView, View.OnClickListener {
    private static final String TAG = "ComplaintDialog";
    private final IComplaintPresenter iComplaintPresenter = new ImpComplaintPresenter(this);

    ImageView mIvCancel;
    TextView mTvSubmit;
    EditText mEtComplain;
    TextView mTvWordCount;
    RecyclerView mRvImages;
    TextView mTvTitle;
    FlexboxLayout mFblLabels;

    private ImageAdapter mImageAdapter;
    private View.OnClickListener mAdapterListener;
    private AlbumActivity.OnSelectEndListener mSelectEndListener;
    private List<AlbumImageBean> mImageList;

    private static final int EDIT_SIZE = 200;
    private static final int MAX_IMAGE = 9;

    private Context mContext;
    private long mId;
    //1:用户 2:提问 3:回答 4:讨论 5:评论回复 6：评论
    private int mType;
    private long mReportUid;
    private List<ReportTag> mTagList;


    public ComplaintDialog(Context context, int type, long reportUid) {
        super(context);
        mContext = context;
        mType = type;
        mReportUid = reportUid;
    }

    public static ComplaintDialog newInstance(Context context, int type, long reportUid) {
        ComplaintDialog dialog = new ComplaintDialog(context, type, reportUid);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uilib_fragment_complaint);
        initLayout();
        intiView();
        initData();
    }

    private void initLayout() {
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.horizontalMargin = 0;
        window.setAttributes(params);
        window.getDecorView().setBackgroundColor(Color.WHITE);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.getDecorView().setMinimumWidth(getContext().getResources().getDisplayMetrics().widthPixels);
    }

    private void intiView() {
        mIvCancel = findViewById(R.id.iv_cancel);
        mTvSubmit = findViewById(R.id.tv_submit);
        mEtComplain = findViewById(R.id.et_complain);
        mTvWordCount = findViewById(R.id.tv_word_count);
        mRvImages = findViewById(R.id.rv_images);
        mTvTitle = findViewById(R.id.tv_title);
        mFblLabels = findViewById(R.id.fbl_labels);

        mIvCancel.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);

        mImageList = new ArrayList<>();
        mImageAdapter = new ImageAdapter(getContext(), mImageList);
        mSelectEndListener = new AlbumActivity.OnSelectEndListener() {
            @Override
            public void onSelectEnd(List<AlbumImageBean> beanList) {
                mImageList.addAll(beanList);
                if (mImageList.size() > 9) {
                    ToastUtil.show(String.format(getContext().getResources().getString(R.string.uilib_comment_iamge_limite)), MAX_IMAGE);
                }
                mImageAdapter.setDatas(mImageList);
            }
        };
        mAdapterListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                Log.d(TAG, "adapter click " + viewId);
                if (viewId == R.id.iv_image_cancle) {
                    int position = (int) v.getTag();
                    mImageList.remove(position);
                    mImageAdapter.setDatas(mImageList);
                } else if (viewId == R.id.iv_image) {
                    //TODO 图片预览
                } else if (viewId == R.id.iv_icon) {
                    AlbumActivity.openSelf(getContext(), 9, mSelectEndListener);
                }

            }
        };
        mImageAdapter.setOnClickListener(mAdapterListener);
        mRvImages.setAdapter(mImageAdapter);


        mEtComplain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvWordCount.setText(s.length() + "/" + EDIT_SIZE);
                if (s.length() > EDIT_SIZE) {
                    mTvWordCount.setTextColor(mContext.getResources().getColor(R.color.commonlib_red_warning));
                } else {
                    mTvWordCount.setTextColor(mContext.getResources().getColor(R.color.gray_three));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initData() {

        iComplaintPresenter.v2pGetReportTag();

    }


    private void addTextViewToFlexBox(final ReportTag bean) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.uilib_item_complaint_label, null);
        final TextView label = view.findViewById(R.id.tv_complaint_label);
        label.setText(bean.getRtDesc());
        label.setTag(bean);
        if (bean.getTagSelected() == 0) {
            label.setTextColor(getContext().getResources().getColor(R.color.gray_three));
            label.setBackground(getContext().getResources().getDrawable(R.drawable.background_global_gray_bg));
        } else {
            label.setTextColor(Color.WHITE);
            label.setBackground(getContext().getResources().getDrawable(R.drawable.background_global_blue_full));
        }

        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportTag bean = (ReportTag) v.getTag();
                if (bean.getTagSelected() == 0) {
                    bean.setTagSelected(1);
                    label.setTextColor(Color.WHITE);
                    label.setBackground(getContext().getResources().getDrawable(R.drawable.background_global_blue_full));
                } else {
                    bean.setTagSelected(0);
                    label.setTextColor(getContext().getResources().getColor(R.color.gray_three));
                    label.setBackground(getContext().getResources().getDrawable(R.drawable.background_global_gray_bg));
                }
            }
        });
        mFblLabels.addView(view);
    }

    @Override
    public void onClick(View view) {

        int viewId = view.getId();
        Log.d(TAG, "onclick " + viewId);
        if (viewId == R.id.iv_cancel) {
            dismiss();
        } else if (viewId == R.id.tv_submit) {
            if (mImageList.size() > 9) {
                ToastUtil.show(String.format(getContext().getResources().getString(R.string.uilib_comment_iamge_limite)), MAX_IMAGE);
                return;
            }
            if ( mEtComplain.length() < 10 || mEtComplain.length() > 200) {
                ToastUtil.show(R.string.uilib_complaint_words_limit);
                return;
            }
            if(mImageList != null && mImageList.size() > 0){
                //有图片先上传图片
                iComplaintPresenter.v2pPostImage(mImageList);
            }else {
                iComplaintPresenter.v2pPostReport(getComplaintBean());
            }
        }
    }

    private ComplaintBean getComplaintBean(){
        ComplaintBean bean = new ComplaintBean();
        bean.setReportTypeId(mType);
        bean.setReportUid(mReportUid);
        bean.setDeviceId(SPUtils.getInstance().getDeviceId());
        bean.setReportDesc(mEtComplain.getText().toString());
        String tagIds = new String();
        for (ReportTag tag : mTagList){
            if (tag.getTagSelected() != 0){
                tagIds = tagIds + tag.getReportTagId() + ",";
            }
        }
        tagIds = tagIds.substring(0,tagIds.length()-1);
        bean.setReportTagId(tagIds);

        return bean;
    }

    @Override
    public void p2vPostImageSuccess(List<AlbumImageBean> list) {
        ComplaintBean bean = getComplaintBean();
        String images = new String();
        for (AlbumImageBean imageBean : list){
            images = images + imageBean.getUrl() + ",";
        }
        images = images.substring(0,images.length()-1);
        bean.setReportImgs(images);
        iComplaintPresenter.v2pPostReport(bean);
    }

    @Override
    public void p2vPostImageFail(String error) {
        ToastUtil.show(R.string.uilib_complaint_error);
    }

    @Override
    public void p2vPostReportSuccess() {
        ToastUtil.show(R.string.uilib_complaint_success);
        dismiss();
    }

    @Override
    public void p2vPostReportError(String error) {
        ToastUtil.show(R.string.uilib_complaint_error);
    }

    @Override
    public void p2vGetReportTagSuccess(List<ReportTag> tags) {
        mTagList = tags;
        for (ReportTag bean : tags) {
            addTextViewToFlexBox(bean);
        }
    }

    @Override
    public void p2vGetReportTagError(String error) {
        ToastUtil.show(R.string.uilib_complaint_net_no_work);
    }
}
