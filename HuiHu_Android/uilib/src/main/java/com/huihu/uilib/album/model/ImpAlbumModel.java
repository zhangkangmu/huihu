package com.huihu.uilib.album.model;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.uilib.album.albuminterface.IAlbumModel;
import com.huihu.uilib.album.albuminterface.IAlbumPresenter;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

public class ImpAlbumModel implements IAlbumModel {

    public static final int PAGE_SIZE = 200;

    private final IAlbumPresenter iAlbumPresenter;

    public ImpAlbumModel(IAlbumPresenter iAlbumPresenter) {
        this.iAlbumPresenter = iAlbumPresenter;
    }

    @Override
    public void p2mGetPictureDb(final int pageIndex) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String[] projection = {MediaStore.Images.Media._ID,MediaStore.Images.Media.DATA};
                String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC limit " + PAGE_SIZE + " offset " + pageIndex * PAGE_SIZE;
                Cursor cursor = BaseApplication.getApplication().getContentResolver().query(uri, projection, null, null, sortOrder);
                List<AlbumImageBean> albumImageBeanList = new LinkedList<>();
                if (cursor != null){
                    while (cursor.moveToNext()){
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                        albumImageBeanList.add(new AlbumImageBean(id, path));
                    }
                    cursor.close();
                }
                iAlbumPresenter.m2pOnSuccessGetPicture(pageIndex, albumImageBeanList);
            }
        });
    }

}
