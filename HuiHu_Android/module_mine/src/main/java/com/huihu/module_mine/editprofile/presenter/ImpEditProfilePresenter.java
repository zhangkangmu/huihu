package com.huihu.module_mine.editprofile.presenter;

import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfileModel;
import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfilePresenter;
import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfileView;
import com.huihu.module_mine.editprofile.entity.User;
import com.huihu.module_mine.editprofile.model.ImpEditProfileModel;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public class ImpEditProfilePresenter implements IEditProfilePresenter {
    private final IEditProfileModel iEditProfileModel = new ImpEditProfileModel(this);
    private final IEditProfileView iEditProfileView;

    public ImpEditProfilePresenter(IEditProfileView iEditProfileView) {
        this.iEditProfileView = iEditProfileView;
    }
    @Override
    public void v2pGetUserEditDetails() {
        iEditProfileModel.p2mGetUserEditDetails();
    }
    public void m2pGetUserEditDetailsError(String error){
        iEditProfileView.p2vGetUserEditDetailsError(error);
    }
    @Override
    public void m2pInitProfileFragment(User user){
        iEditProfileView.p2vInitProfileFragment(user);
    }
    @Override
    public void v2pUpdateSex(int sex){
        iEditProfileModel.p2mUpdateSex(sex);
    }
    @Override
    public void m2pUpdateSexError(String error){
        iEditProfileView.p2vUpdateSexError(error);
    }
    @Override
    public void m2pUpdateSexSuccess(){
        iEditProfileView.p2vUpdateSexSuccess();
    }

    @Override
    public void v2pUpdateIndustry(String industry){
        iEditProfileModel.p2mUpdateIndustry(industry);
    }

    @Override
    public void m2pUpdateIndustryError(String error){
        iEditProfileView.p2vUpdateIndustryError(error);
    }

    @Override
    public void m2pUpdateIndustrySuccess(){
        iEditProfileView.p2vUpdateIndustrySuccess();
    }

    @Override
    public void v2pPutUpdateEducation(String education){
        iEditProfileModel.p2mPutUpdateEducation(education);
    }

    @Override
    public void m2pPutUpdateEducationSuccess() {
        iEditProfileView.p2vPutUpdateEducationSuccess();
    }

    @Override
    public void m2pPutUpdateEducationError(String error) {
        iEditProfileView.p2vPutUpdateEducationError(error);
    }

    @Override
    public void v2pPostImageUtil(List<AlbumImageBean> imageBeans) {
        iEditProfileModel.p2mPostImageUtil(imageBeans);
    }

    @Override
    public void m2pPostImageUtilSuccess(String url) {
        iEditProfileView.p2vPostImageUtilSuccess();
        iEditProfileModel.p2mPutUpdateHeadImage(url);
    }

    @Override
    public void m2pPostImageUtilFail(String error) {
        iEditProfileView.p2vPostImageUtilFail(error);
    }


    @Override
    public void m2pPutUpdateHeadImageError(String error) {
        iEditProfileView.p2vPutUpdateHeadImageError(error);
    }

    @Override
    public void m2pPutUpdateHeadImageSuccess() {
        iEditProfileView.p2vPutUpdateHeadImageSuccess();
    }
}
