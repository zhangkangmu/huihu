package com.huihu.uilib.album.presenter;

import com.huihu.uilib.album.albuminterface.IAlbumModel;
import com.huihu.uilib.album.albuminterface.IAlbumPresenter;
import com.huihu.uilib.album.albuminterface.IAlbumView;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.model.ImpAlbumModel;

import java.util.List;

public class ImpAlbumPresenter implements IAlbumPresenter {

    private final IAlbumModel iAlbumModel = new ImpAlbumModel(this);
    private final IAlbumView iAlbumView;

    private int pageIndex = 0;

    public ImpAlbumPresenter(IAlbumView iAlbumView) {
        this.iAlbumView = iAlbumView;
    }

    @Override
    public void v2pGetPicture() {
        iAlbumModel.p2mGetPictureDb(0);
    }

    @Override
    public void v2pOpenSystemCamera() {
        iAlbumView.p2vOpenSystemCamera();
    }

    @Override
    public void v2pOpenPictureFragment(int type, int position) {
        iAlbumView.p2vOpenPictureFragment(type, position);
    }

    @Override
    public void v2pGetMorePicture() {
        iAlbumModel.p2mGetPictureDb(pageIndex + 1);
    }

    @Override
    public void m2pOnSuccessGetPicture(int pageIndex, List<AlbumImageBean> imageList) {
        if (imageList == null || imageList.size() == 0) return;
        if (pageIndex > 0) {
            iAlbumView.p2vShowMorePicture(imageList);
        } else {
            iAlbumView.p2vShowPicture(imageList);
        }
        this.pageIndex = pageIndex;
    }

}
