package com.huihu.uilib.imagecrop.view;

import android.app.Activity;
import android.content.Intent;

import com.huihu.uilib.imagecrop.customize.CropImage;

import java.nio.file.Path;

/**
 * Created by $USER_NAME on 2019/3/29.
 * description：
 */
public class CropImageActivity extends CropImage {

    public static void openSelf(Activity activity, String  imagePath, int requestCode){
        Intent intent = new Intent(activity, CropImageActivity.class);
        intent.putExtra(CropImage.IMAGE_PATH, imagePath);
        intent.putExtra(CropImage.SCALE,false);
        intent.putExtra(CropImage.ASPECT_X,3);
        intent.putExtra(CropImage.ASPECT_Y,2);
        intent.putExtra(CropImage.CIRCLE_CROP,"true");
        intent.putExtra(CropImage.RETURN_DATA, true);
        activity.startActivityForResult(intent, requestCode);
    }
}
