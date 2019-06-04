package com.huihu.module_mine.feedback.view;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackPresenter;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackView;
import com.huihu.module_mine.feedback.presenter.ImpFeedbackPresenter;
import com.huihu.uilib.adapter.SelectThumbnailAdapter;
import com.huihu.uilib.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FeedbackFragment extends BaseFragment implements IFeedbackView {

    private static final float DECORATION = 10;
    private static final int MIN_TEXT_SIZE = 10;
    private static final int INPUT_LIMIT = 200;

    private final IFeedbackPresenter iFeedbackPresenter = new ImpFeedbackPresenter(this);

    @BindView(R2.id.et_content)
    EditText etContent;
    @BindView(R2.id.tv_limit_tip)
    TextView tvLimitTip;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    Unbinder unbinder;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.et_phone)
    EditText etPhone;
    @BindView(R2.id.tv_commit)
    TextView tvCommit;

    private SelectThumbnailAdapter mAdapter;

    public static FeedbackFragment newInstance() {
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        Bundle bundle = new Bundle();
        feedbackFragment.setArguments(bundle);
        return feedbackFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_feedback, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRv();
        initInputLimitTipAndEtContent();
        TextViewUtils.setTextFakeBold(tvTitle);
        changeCommitStatus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initRv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvContent.setLayoutManager(linearLayoutManager);
        mAdapter = new SelectThumbnailAdapter(this);
        rvContent.setAdapter(mAdapter);
        rvContent.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = DensityUtil.dip2px(getContext(), DECORATION);
                outRect.right = DensityUtil.dip2px(getContext(), DECORATION);
            }
        });
        mAdapter.setDataChangeListener(new SelectThumbnailAdapter.OnDataChangeListener() {
            @Override
            public void onDataChange() {
                changeCommitStatus();
            }
        });
    }

    private void initInputLimitTipAndEtContent() {
        tvLimitTip.setText(String.format(getString(R.string.module_mine_limit_tip), 0));
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvLimitTip.setText(String.format(getString(R.string.module_mine_limit_tip), s.length()));
                int color = ContextCompat.getColor(getContext()
                        , s.length() > INPUT_LIMIT ? R.color.commonlib_red_warning : R.color.gray_three);
                tvLimitTip.setTextColor(color);
                changeCommitStatus();
            }
        });
    }

    @OnClick({R2.id.tv_commit, R2.id.iv_back})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_commit) {
            postFeedback();
        } else if (id == R.id.iv_back){
            pop();
        }
    }

    @Override
    public void p2vPostFeedbackSuccess() {
        ToastUtil.show("反馈成功，小汇立马处理");
        pop();
    }

    @Override
    public void p2vUploadImageSuccess() {
        iFeedbackPresenter.v2pPostFeedback(mAdapter.getThumbnailUrls()
                , etContent.getText().toString(), etPhone.getText().toString());
    }

    private void postFeedback() {
        if (etContent.getText().length() < MIN_TEXT_SIZE && mAdapter.getThumbnailUrls().size() == 0) {
            ToastUtil.show(R.string.module_mine_content_not_null);
        } else {
            iFeedbackPresenter.v2pUploadImage(mAdapter.getThumbnailUrls());
        }
    }

    private void changeCommitStatus() {
        if (etContent.getText().length() < MIN_TEXT_SIZE && mAdapter.getThumbnailUrls().size() == 0) {
            tvCommit.setEnabled(false);
        } else {
            tvCommit.setEnabled(true);
        }
    }
}
