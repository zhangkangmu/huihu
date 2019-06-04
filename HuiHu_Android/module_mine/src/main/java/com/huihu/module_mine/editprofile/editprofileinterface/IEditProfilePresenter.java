package com.huihu.module_mine.editprofile.editprofileinterface;

import com.huihu.module_mine.editprofile.entity.User;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.io.File;
import java.net.URL;
import java.util.List;

public interface IEditProfilePresenter {
    void v2pGetUserEditDetails();
    void m2pInitProfileFragment(User user);
    void m2pGetUserEditDetailsError(String error);

    void m2pPutUpdateHeadImageError(String error);
    void m2pPutUpdateHeadImageSuccess();

    void v2pUpdateSex(int sex);
    void m2pUpdateSexSuccess();
    void m2pUpdateSexError(String error);

    void v2pUpdateIndustry(String industry);
    void m2pUpdateIndustrySuccess();
    void m2pUpdateIndustryError(String error);

    void v2pPutUpdateEducation(String education);
    void m2pPutUpdateEducationSuccess();
    void m2pPutUpdateEducationError(String error);

    void v2pPostImageUtil(List<AlbumImageBean> imageBeans );
    void m2pPostImageUtilSuccess(String url);
    void m2pPostImageUtilFail(String error);
}
