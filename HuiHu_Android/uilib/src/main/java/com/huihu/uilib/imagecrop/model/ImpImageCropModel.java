package com.huihu.uilib.imagecrop.model;

import com.huihu.uilib.imagecrop.imagecropinterface.IImageCropModel;
import com.huihu.uilib.imagecrop.imagecropinterface.IImageCropPresenter;

public class ImpImageCropModel implements IImageCropModel {
    private final IImageCropPresenter iImageCropPresenter;

    public ImpImageCropModel(IImageCropPresenter iImageCropPresenter) {
        this.iImageCropPresenter = iImageCropPresenter;
    }
}
