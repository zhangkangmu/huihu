package com.huihu.uilib.imagecrop.presenter;

import com.huihu.uilib.imagecrop.imagecropinterface.IImageCropModel;
import com.huihu.uilib.imagecrop.imagecropinterface.IImageCropPresenter;
import com.huihu.uilib.imagecrop.imagecropinterface.IImageCropView;
import com.huihu.uilib.imagecrop.model.ImpImageCropModel;

public class ImpImageCropPresenter implements IImageCropPresenter {
    private final IImageCropModel iImageCropModel = new ImpImageCropModel(this);
    private final IImageCropView iImageCropView;

    public ImpImageCropPresenter(IImageCropView iImageCropView) {
        this.iImageCropView = iImageCropView;
    }
}
