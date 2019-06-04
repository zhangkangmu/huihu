package com.huihu.uilib.album.albuminterface;

import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public interface IAlbumView {
    void p2vShowPicture(List<AlbumImageBean> imageList);
    void p2vShowMorePicture(List<AlbumImageBean> imageList);
    void p2vOpenSystemCamera();
    void p2vOpenPictureFragment(int type, int position);
}
