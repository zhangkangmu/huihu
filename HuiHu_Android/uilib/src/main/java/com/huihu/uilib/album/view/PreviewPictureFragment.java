package com.huihu.uilib.album.view;

import android.os.Bundle;

import com.huihu.uilib.album.entity.AlbumImageBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/3/15 0015
 * description:
 */
public class PreviewPictureFragment extends BasePictureFragment {

    public static PreviewPictureFragment newInstance(int position, int limit){
        PreviewPictureFragment fragment = new PreviewPictureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        bundle.putInt(SELECT_LIMIT, limit);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected List<AlbumImageBean> getImageBeanList() {
        List<AlbumImageBean> beans = new ArrayList<AlbumImageBean>();
        if (interaction != null) beans.addAll(interaction.getSelectList());
        return beans;
    }

    @Override
    protected List<AlbumImageBean> getSelectList() {
        List<AlbumImageBean> beans = new ArrayList<AlbumImageBean>();
        if (interaction != null) beans.addAll(interaction.getSelectList());
        return beans;
    }

    @Override
    protected void onAddPicture(AlbumImageBean bean) {
        EventBus.getDefault().post(bean);
    }

    @Override
    protected void onDeletePicture(AlbumImageBean bean) {
        EventBus.getDefault().post(bean);
    }
}
