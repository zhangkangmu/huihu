package com.huihu.uilib.album.view;

import android.os.Bundle;

import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/3/15 0015
 * description:
 */
public class AlbumPictureFragment extends BasePictureFragment {

    public static AlbumPictureFragment newInstance(int position, int limit){
        AlbumPictureFragment fragment = new AlbumPictureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        bundle.putInt(SELECT_LIMIT, limit);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected List<AlbumImageBean> getImageBeanList() {
        return interaction == null ? new ArrayList<AlbumImageBean>() : interaction.getAlbumImageList();
    }

    @Override
    protected List<AlbumImageBean> getSelectList() {
        return interaction == null ? new ArrayList<AlbumImageBean>() : interaction.getSelectList();
    }

}
