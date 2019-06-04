package com.huihu.module_mine.editprofile.editprofileinterface;

import com.huihu.module_mine.editprofile.entity.User;

public interface IEditProfileView {
    void p2vInitProfileFragment(User user);
    void p2vGetUserEditDetailsError(String error);

    void p2vPutUpdateHeadImageSuccess();
    void p2vPutUpdateHeadImageError(String error);

    void p2vUpdateSexError(String error);
    void p2vUpdateSexSuccess();

    void p2vUpdateIndustryError(String error);
    void p2vUpdateIndustrySuccess();

    void p2vPutUpdateEducationSuccess();
    void p2vPutUpdateEducationError(String error);

    void p2vPostImageUtilFail(String error);
    void p2vPostImageUtilSuccess();
}
