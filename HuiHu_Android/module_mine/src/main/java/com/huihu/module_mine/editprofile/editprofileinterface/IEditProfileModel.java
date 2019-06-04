package com.huihu.module_mine.editprofile.editprofileinterface;

import com.huihu.uilib.album.entity.AlbumImageBean;

import java.io.File;
import java.util.List;

public interface IEditProfileModel {
    public void p2mGetUserEditDetails();
    public void p2mPutUpdateEducation(String education);
//    public void p2mPostImage(File photo);
    void p2mUpdateSex(int sex);
    void p2mUpdateIndustry(String indutry);
    void p2mPostImageUtil(List<AlbumImageBean> imageBeans );
    void p2mPutUpdateHeadImage(String url);

}
