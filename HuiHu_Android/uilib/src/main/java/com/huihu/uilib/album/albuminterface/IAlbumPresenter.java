package com.huihu.uilib.album.albuminterface;

import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public interface IAlbumPresenter {
    void v2pGetPicture();
    void v2pOpenSystemCamera();
    void v2pOpenPictureFragment(int type, int position);
    void v2pGetMorePicture();
    void m2pOnSuccessGetPicture(int pageIndex, List<AlbumImageBean> imageList);
}
