package com.huihu.uilib.album.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.uilib.R;
import com.huihu.uilib.album.adapter.AlbumPictureAdapter;
import com.huihu.uilib.album.adapter.PictureSelectAdapter;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;
/**
 * create by wangjing on 2019/3/14 0014
 * description:
 */
public abstract class BasePictureFragment extends Fragment implements View.OnClickListener {

    protected static final String POSITION = "position";
    protected static final String SELECT_LIMIT = "select_position";

    RecyclerView rvAlbum, rvSelect;
    ImageView iv_back;
    TextView tvSelectOrder, tvComplete;

    private AlbumPictureAdapter mViewPageAdapter;
    private PictureSelectAdapter mPictureSelectAdapter;
    protected InteractionActivity interaction;
    protected List<AlbumImageBean> imageBeanList;
    protected List<AlbumImageBean> selectList;
    private int mCurrentPosition;
    private int limit;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionActivity){
            interaction = (InteractionActivity) context;
        }
        imageBeanList = getImageBeanList();
        selectList = getSelectList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        limit = getArguments().getInt(SELECT_LIMIT, 9);
        initRvSelect();
        initRvAlbum();
        iv_back.setOnClickListener(this);
        tvSelectOrder.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
        changeSelectNumber();
        changeSelectOrder();
    }

    private View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.comresourcelib_fragment_look_picture, container, false);
        rvAlbum = view.findViewById(R.id.rv_album);
        iv_back = view.findViewById(R.id.iv_back);
        tvSelectOrder = view.findViewById(R.id.tv_select_order);
        rvSelect = view.findViewById(R.id.rv_select);
        tvComplete = view.findViewById(R.id.tv_complete);
        return view;
    }

    private void initRvAlbum(){
        mViewPageAdapter = new AlbumPictureAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        PagerSnapHelper snapHelper = new PagerSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                mCurrentPosition = position;
                changeSelectOrder();
                return position;
            }
        };
        snapHelper.attachToRecyclerView(rvAlbum);
        rvAlbum.setLayoutManager(layoutManager);
        rvAlbum.setAdapter(mViewPageAdapter);
        mCurrentPosition = getArguments().getInt(POSITION, 0);
        rvAlbum.getLayoutManager().scrollToPosition(mCurrentPosition);
        mViewPageAdapter.setImageBeanList(imageBeanList);
        mViewPageAdapter.notifyDataSetChanged();
    }

    private void initRvSelect(){
        LinearLayoutManager selectLayoutManager = new LinearLayoutManager(getContext());
        selectLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSelect.setLayoutManager(selectLayoutManager);
        mPictureSelectAdapter = new PictureSelectAdapter(getContext());
        rvSelect.setAdapter(mPictureSelectAdapter);
        mPictureSelectAdapter.setImageBeanList(selectList);
        mPictureSelectAdapter.notifyDataSetChanged();
        mPictureSelectAdapter.setListener(new PictureSelectAdapter.OnClickSelectListener() {
            @Override
            public void onClick(AlbumImageBean bean) {
                mCurrentPosition = imageBeanList.indexOf(bean);
                rvAlbum.getLayoutManager().scrollToPosition(mCurrentPosition);
                changeSelectOrder();
            }
        });
    }

    private int lastIndex = -1;

    private void changeSelectOrder(){
        if (mCurrentPosition >= imageBeanList.size()) return;
        AlbumImageBean bean = imageBeanList.get(mCurrentPosition);
        int index = selectList.indexOf(bean);
        mPictureSelectAdapter.setCurrentPosition(index);
        if (index != -1) {
            tvSelectOrder.setText(String.valueOf(index + 1));
            mPictureSelectAdapter.notifyItemChanged(index, index);
            cancelLastSelect();
            lastIndex = index;
            rvSelect.getLayoutManager().scrollToPosition(index);
        } else {
            tvSelectOrder.setText("");
            cancelLastSelect();
        }

    }

    private void cancelLastSelect(){
        if (lastIndex > -1){
            mPictureSelectAdapter.notifyItemChanged(lastIndex, -1);
            lastIndex = -1;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_select_order){
            addOrDeletePicture();
        } else if (id == R.id.iv_back){
            if (interaction != null) interaction.finishFragment();
        } else if (id == R.id.tv_complete){
            if (interaction != null) interaction.completeSelect();
        }
    }

    private void changeSelectNumber(){
        if (selectList.size() > 0) {
            tvComplete.setText(String.format(getString(R.string.comresourcelib_album_complete_number), selectList.size()));
        } else {
            tvComplete.setText(R.string.comresourcelib_album_complete);
        }
    }

    private void addOrDeletePicture(){
        AlbumImageBean bean = imageBeanList.get(mCurrentPosition);
        int index = selectList.indexOf(bean);
        if (index == -1 ){
            addPicture(bean);
        } else {
           deletePicture(index, bean);
        }
        if (interaction != null) interaction.notifySelectListChange(bean);
        changeSelectNumber();
    }

    private void addPicture(AlbumImageBean bean){
        if (selectList.size() < limit){
            selectList.add(bean);
            lastIndex = selectList.size() - 1;
            mPictureSelectAdapter.setCurrentPosition(lastIndex);
            mPictureSelectAdapter.notifyItemInserted(lastIndex);
            rvSelect.getLayoutManager().scrollToPosition(lastIndex);
            tvSelectOrder.setText(String.valueOf(selectList.size()));
        } else {
            ToastUtil.show(String.format(getString(R.string.comresourcelib_album_select_limit), limit));
        }
        onAddPicture(bean);
    }

    private void deletePicture(int index, AlbumImageBean bean){
        selectList.remove(bean);
        lastIndex = -1;
        mPictureSelectAdapter.setCurrentPosition(lastIndex);
        mPictureSelectAdapter.notifyItemRemoved(index);
        mPictureSelectAdapter.notifyItemRangeChanged(index , mPictureSelectAdapter.getItemCount());
        tvSelectOrder.setText("");
        int selectCount = selectList.size();
        if (index != selectCount){
            for (int i = index; i < selectCount; i++){
                AlbumImageBean imageBean = selectList.get(i);
                if (interaction != null) interaction.notifySelectListChange(imageBean);
            }
        }
        onDeletePicture(bean);
    }

    protected abstract List<AlbumImageBean> getImageBeanList();
    protected abstract List<AlbumImageBean> getSelectList();
    protected void onAddPicture(AlbumImageBean bean){};
    protected void onDeletePicture(AlbumImageBean bean){};

    /**
     * 跟activity交互的接口
     */
    public interface InteractionActivity{
        List<AlbumImageBean> getAlbumImageList();
        List<AlbumImageBean> getSelectList();
        void notifySelectListChange(AlbumImageBean bean);
        void finishFragment();
        void completeSelect();
    }

}
