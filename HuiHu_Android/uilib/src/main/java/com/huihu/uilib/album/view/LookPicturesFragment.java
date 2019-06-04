package com.huihu.uilib.album.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.StatusBarUtil;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.uilib.R;
import com.huihu.uilib.R2;
import com.huihu.uilib.album.adapter.AlbumPictureAdapter;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * create by wangjing on 2019/3/25 0025
 * description:
 */
public abstract class LookPicturesFragment extends BaseFragment {

    private static final String POSITION = "position";

    @BindView(R2.id.rv_album)
    RecyclerView rvAlbum;
    Unbinder unbinder;
    @BindView(R2.id.tv_now_position)
    TextView tvNowPosition;
    @BindView(R2.id.iv_delete)
    ImageView ivDelete;

    private static List<AlbumImageBean> albumImageBeans = new LinkedList<>();
    private static OnDeleteListener deleteListener;
    private AlbumPictureAdapter mAdapter;
    private int mCurrentPosition;

    public static LookPicturesFragment newInstance(int position, List<AlbumImageBean> beans, OnDeleteListener listener, boolean isCanDelete) {
        LookPicturesFragment fragment = isCanDelete ? new CanDelete() : new NoDelete();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        fragment.setArguments(bundle);
        albumImageBeans.addAll(beans);
        deleteListener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.uilib_fragment_look_picture_can_delete
                , container, false);
        unbinder = ButterKnife.bind(this, v);
        StatusBarUtil.setStatusBarBgColorAndIcon(getActivity()
                , ContextCompat.getColor(getContext(), R.color.comresourcelib_background_look_picture_actionbar)
                , StatusBarUtil.STATUS_BAR_LIGHT_ICON);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentPosition = getArguments().getInt(POSITION, 0);
        mAdapter = new AlbumPictureAdapter(getContext());
        rvAlbum.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvAlbum.setLayoutManager(layoutManager);
        layoutManager.scrollToPosition(mCurrentPosition);
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                if (position >= albumImageBeans.size()) position = albumImageBeans.size() - 1;
                mCurrentPosition = position;
                changeNowPosition();
                return position;
            }
        };
        snapHelper.attachToRecyclerView(rvAlbum);
        TextViewUtils.setTextFakeBold(tvNowPosition);
        changeNowPosition();
        mAdapter.setImageBeanList(albumImageBeans);
        mAdapter.notifyDataSetChanged();
        ivDelete.setVisibility(isCanDelete() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        albumImageBeans.clear();
        deleteListener = null;
        StatusBarUtil.setStatusBarBgColorAndIcon(getActivity()
                , ContextCompat.getColor(getContext(), R.color.white)
                , StatusBarUtil.STATUS_BAR_DART_ICON);
    }


    @OnClick({R2.id.iv_delete, R2.id.iv_back})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_delete) {
            deletePicture();
        } else if (id == R.id.iv_back) {
            pop();
        }
    }

    private void deletePicture() {
        AlbumImageBean bean = albumImageBeans.get(mCurrentPosition);
        albumImageBeans.remove(bean);
        mAdapter.notifyItemRemoved(mCurrentPosition);
        if (deleteListener != null) deleteListener.onDelete(bean);
        if (mCurrentPosition >= albumImageBeans.size())
            mCurrentPosition = albumImageBeans.size() - 1;
        changeNowPosition();
    }

    private void changeNowPosition() {
        tvNowPosition.setText(mCurrentPosition + 1 + "/" + albumImageBeans.size());
    }


    protected abstract boolean isCanDelete();

    public interface OnDeleteListener {
        void onDelete(AlbumImageBean bean);
    }

    public static class CanDelete extends LookPicturesFragment{

        @Override
        protected boolean isCanDelete() {
            return true;
        }
    }

    public static class NoDelete extends LookPicturesFragment{

        @Override
        protected boolean isCanDelete() {
            return false;
        }
    }
}
