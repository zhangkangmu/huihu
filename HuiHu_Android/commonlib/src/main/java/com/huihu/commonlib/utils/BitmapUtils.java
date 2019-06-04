package com.huihu.commonlib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * bitmap操作、转换、压缩等
 *
 * @author 周凯
 * @date 2015-9-21 下午3:07:21
 * @description
 */
public class BitmapUtils {

    public static byte[] bitmapTobyteArray(Bitmap bmp) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        byte[] result = output.toByteArray();
        output.close();
        return result;
    }

    /**
     * 数据转化成文件
     *
     * @param datas       数据源
     * @param imgPathTemp 文件夹路径
     * @param fileName    文件名
     * @return
     */
    public static File ByteToFile(byte[] datas, String imgPathTemp, String fileName) throws IOException {
        File destDir = new File(imgPathTemp);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File file = new File(imgPathTemp, fileName);
        if (!file.isDirectory()) {
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ByteArrayInputStream bais = new ByteArrayInputStream(datas);
            int data = bais.read();
            while (data != -1) {
                fos.write(data);
                data = bais.read();
            }
            fos.close();
            bais.close();
        }
        return file;
    }

    // ��Drawableת��ΪBitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_4444
                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

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
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * bitmap转普通字符串
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = new String(bitmapBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * bitmap转文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static void bitmapToFile(Bitmap bm, String fileName) throws IOException, NullPointerException {
        File myCaptureFile = new File(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }

    /**
     * 图片文件格式转换
     *
     * @param src
     * @param format
     * @param out
     */
    public static void picConvert(String src, Bitmap.CompressFormat format, String out) {
        Bitmap srcBitmap = BitmapFactory.decodeFile(src);
        File outfile = new File(out);
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(outfile));
            srcBitmap.compress(format, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 图片变灰度，用于用户离线或隐身的时候
     *
     * @param bitmap
     * @return
     */
    public static Bitmap bitmap2Gray(Bitmap bitmap) {
        int width, height;
        height = bitmap.getHeight();
        width = bitmap.getWidth();
        Bitmap bmpGray = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGray);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bitmap, 0, 0, paint);
        return bmpGray;
    }

    /**
     * 毛玻璃效果
     * @param context
     * @param sentBitmap
     * @param radius 0-25，值越大越模糊
     * @return
     */
    public static Bitmap doBlur(Context context, Bitmap sentBitmap, int radius) {
        if (Build.VERSION.SDK_INT > 16) {
            int scaleRatio = 2;
            sentBitmap = Bitmap.createScaledBitmap(sentBitmap,
                    sentBitmap.getWidth() / scaleRatio,
                    sentBitmap.getHeight() / scaleRatio,
                    false);
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

            RenderScript rs = RenderScript.create(context);
            Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(radius /* e.g. 3.f */);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        return null;
    }
    /** 获取图片大小 */
    public static int getBitmapSize(Bitmap bitmap){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }
    //===============以下是图片压缩有关的================

    /**
     * 压缩图片质量
     *
     * @param bitmap
     * @return
     */
    public static Bitmap compressQuality(Bitmap bitmap, int targetSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100; //此处可尝试用90%开始压缩，跳过100%压缩
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        int length = baos.toByteArray().length;
        while ((length = baos.toByteArray().length) / 1024 > targetSize) {
            // 每次都减少10
            options -= 10;
            // 重置baos即清空baos
            baos.reset();
            // 这里压缩options%，把压缩后的数据存放到baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(baos.toByteArray());
        return BitmapFactory.decodeStream(in, null, null);
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
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
    /*
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        if(bitmap==null)return null;
        //旋转图片 动作
        Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static final int PHOTO_W=720;
    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）
     *
     * @param srcPath
     * @return
     */
    public static Bitmap compressSize(String srcPath, int targetSize) {
        if(new File(srcPath).length()<300*1024){//小于300kb的图片直接传原图
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
                if (bitmap == null) return null;
                bitmap = rotaingImageView(readPictureDegree(srcPath), bitmap);//翻转图片
                return bitmap;//压缩好比例大小后再进行质量压缩
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
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
//            //现在主流手机比较多是1280*720分辨率，所以高和宽我们设置为
//            float hh = 1280f;//这里设置高度为800f
//            float ww = 720f;//这里设置宽度为480f
//            if (w > h) {
//                hh = 1280f;
//                ww = 720f;
//            }
//            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//            int be = 1;//be=1表示不缩放
//            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//                be = (int) (newOpts.outWidth / ww);
//            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//                be = (int) (newOpts.outHeight / hh);
//            }
//            if (be <= 0)
//                be = 1;
//            //另外一种算压缩比的方法：
//            //图片比例压缩倍数 就是 （宽度压缩倍数+高度压缩倍数）/2
////            be = (int) ((w / STANDARD_WIDTH + h/ STANDARD_HEIGHT) / 2);
//            newOpts.inSampleSize = be;//设置缩放比例

                //以屏幕宽为基准压缩图片
                if (w < PHOTO_W) {
                    newOpts.inSampleSize = 1;//设置缩放比例
                } else {
                    newOpts.inSampleSize = w / PHOTO_W;//设置缩放比例
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
                bitmap = rotaingImageView(readPictureDegree(srcPath), bitmap);//翻转图片
                if (!isLong) {
                    if (bitmap.getWidth() > PHOTO_W) {
                        bitmap = zoomImg(bitmap);
                    }
                }
                return bitmap;//压缩好比例大小后再进行质量压缩
//        return compressQuality(bitmap,200);//压缩好比例大小后再进行质量压缩
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
     *  获取720宽的图片
     * @param bm 所要转换的bitmap
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm){
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) PHOTO_W) / width;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);//只要720宽
        // 得到新的图片
        try {
            Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
            return newbm;
        }catch (Error e){
            return bm;
        }
    }
    /**
     * 图片按比例大小压缩方法（根据Bitmap图片压缩）
     * @param image
     * @return
     */
    public static Bitmap compressSize(Bitmap image, int targetSize)
    {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
                baos.reset();//重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
            }
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            Bitmap bitmap;// = BitmapFactory.decodeStream(isBm, null, newOpts);
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 400f;//这里设置高度为800f
            float ww = 240f;//这里设置宽度为480f
            if (w > h) {
                hh = 240f;
                ww = 400f;
            }
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;//设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            isBm = new ByteArrayInputStream(baos.toByteArray());
            bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
            return compressQuality(bitmap, targetSize);//压缩好比例大小后再进行质量压缩
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }catch (OutOfMemoryError error){
            error.printStackTrace();
            return null;
        }
    }
}
