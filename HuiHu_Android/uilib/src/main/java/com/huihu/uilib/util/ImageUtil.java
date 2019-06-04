package com.huihu.uilib.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * create by wangjing on 2019/3/27 0027
 * description:提供关于图片操作的方法。
 */
public class ImageUtil {

    private static final int DEFAULT_IMAGE_WIDTH = 720;
    private static final int DEFAULT_SIZE = 300;
    private static final int KB = 1024;

    public static String getImageBase64(String path) {
        Bitmap bitmap = getBitmapByPath(path);
        if (bitmap != null){
            return bitmapToBase64(bitmap);
        } else {
            return "";
        }
    }

    public static String getImageBase64(@NonNull Bitmap bitmap){
        if (bitmap.getByteCount() < DEFAULT_SIZE * KB / 8){
            return bitmapToBase64(bitmap);
        } else {
            return bitmapToBase64(compressQuality(bitmap, DEFAULT_SIZE));
        }
    }

    /**
     * 获取上传的bitmap
     * @param srcPath 本地图片路径
     * @return 压缩过的Bitmap
     */
    private static Bitmap getBitmapByPath(String srcPath) {
        if (new File(srcPath).length() < DEFAULT_SIZE * KB) {//小于300kb的图片直接传原图
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
                if (bitmap == null) return null;
                bitmap = rotateImageView(readPictureDegree(srcPath), bitmap);//翻转图片
                return bitmap;
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            boolean isLong = false;
            if (w < 0) {//不知道怎么产生的，导致w=-1 TODO
                newOpts.inSampleSize = 4;//小米手机拍照尺寸，3120*4160，设置缩放比例3120/720=4
            } else {
                //以屏幕宽为基准压缩图片
                if (w < DEFAULT_IMAGE_WIDTH) {
                    newOpts.inSampleSize = 1;//设置缩放比例
                } else {
                    newOpts.inSampleSize = w / DEFAULT_IMAGE_WIDTH;//设置缩放比例
                }
                if (h / newOpts.inSampleSize > 5000) {
                    isLong = true;
                    newOpts.inSampleSize = (int) Math.rint(h * 1.0 / 5000);//设置长图的比例
                }
            }
            newOpts.inJustDecodeBounds = false;
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
                if (bitmap == null) return null;
                bitmap = rotateImageView(readPictureDegree(srcPath), bitmap);//翻转图片
                if (!isLong) {
                    if (bitmap.getWidth() > DEFAULT_IMAGE_WIDTH) {
                        bitmap = zoomImg(bitmap);
                    }
                }
                return compressQuality(bitmap, DEFAULT_SIZE);
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 旋转图像
     * @param angle 图片当前角度
     * @param bitmap 图像bitmap
     * @return 获取旋转过的图像
     */
    private static Bitmap rotateImageView(int angle, Bitmap bitmap) {
        if (bitmap == null) return null;
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 获取路径图片的角度
     * @param path 图片路径
     * @return 当前图片角度
     */
    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 缩放图片
     * @param bm 需要缩放的Bitmap
     * @return 缩放好的图片
     */
    private static Bitmap zoomImg(Bitmap bm) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) DEFAULT_IMAGE_WIDTH) / width;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);//只要720宽
        // 得到新的图片
        try {
            Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
            return newbm;
        } catch (Error e) {
            return bm;
        }
    }

    /**
     * 把bitmap转为Base64String
     * @param bitmap
     * @return BASE64的String
     */
    private static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();
//                bitmap.recycle();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 压缩图片质量的方法。最多压缩3次
     * @param bitmap
     * @param targetSize 目标的大小单位KB
     * @return
     */
    private static Bitmap compressQuality(Bitmap bitmap, int targetSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 90;
        int length = baos.toByteArray().length;
        while ((length = baos.toByteArray().length) / KB > targetSize) {
            options -= 10;
            if (options < 70) break;
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
        return BitmapFactory.decodeStream(in, null, null);
    }

}
