package com.adult.android.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Surface;

public class CameraUtil {
    /**
     * 加载图片的最大值，超过以后，压缩图片至小于该值
     */
    private static final int MAX_IMAGE_SIZE = 1 * 1024 * 1024;
    private static final String FILE_DATE_FORMAT = "yyyyMMdd_HHmmss";

    /**
     * Check if this device has a camera
     */
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * 判断照片旋转角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
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
            return degree;
        }
        return degree;
    }


    public static int getCameraDisplayOrientation(Activity activity,
                                                  int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }

    /**
     * 获取图片文件bitmap的宽高
     *
     * @return int[]数组，长度为2
     */
    public static int[] getBitmapDisplay(String filePath) {
        int[] result = new int[2];
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        result[0] = options.outWidth;
        result[1] = options.outHeight;
        return result;
    }

    public static Bitmap decodeSampledBitmapFromPath(String filePath,
                                                     int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, Math.min(reqWidth, reqHeight), reqWidth * reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap decodeSampledBitmapFromUri(Uri uri, Context context) {
        try {
            InputStream input = context.getContentResolver().openInputStream(uri);
            // First decode with inJustDecodeBounds=true to check dimensions
            final int bytesLength = input.available();
            if (bytesLength > MAX_IMAGE_SIZE) {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(input, new Rect(), options);
                input.close();

                int reqWidth = options.outWidth;
                int reqHeight = options.outHeight;

                float compress = (float) MAX_IMAGE_SIZE / (float) bytesLength;

                reqWidth *= compress;
                reqHeight *= compress;
                // Calculate inSampleSize
                options.inSampleSize = calculateInSampleSize(options, Math.min(reqWidth, reqHeight), reqWidth * reqHeight);

                // Decode bitmap with inSampleSize set
                options.inJustDecodeBounds = false;
                Bitmap resultBitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), new Rect(), options);
                return resultBitmap;
            } else {
                return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使bitmap的大小不超过规定大小
     */
    public static int makeSuitableQuality(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int maxBytes) {
        ByteArrayOutputStream tempBaos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(compressFormat, quality, tempBaos);
        int bytes = tempBaos.size();
        while (bytes > maxBytes && quality >= 10) {
            try {
                tempBaos.close();
                tempBaos = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempBaos = new ByteArrayOutputStream();
            quality -= 10;
            bitmap.compress(compressFormat, quality, tempBaos);
            bytes = tempBaos.size();
        }
        try {
            tempBaos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quality;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


    public static byte[] readStream(InputStream inStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.close();
            inStream.close();
        } catch (IOException e) {

        }
        return outStream.toByteArray();
    }

    public static int[] getBitmapSizeFromPath(String filePath) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(filePath, options);
        int[] array = new int[2];
        array[0] = options.outWidth;
        array[1] = options.outHeight;
        return array;
    }

    public static int[] getBitmapSizeFromUri(Context context, Uri uri) {
        InputStream input = null;
        int[] array = new int[2];
        try {
            input = context.getContentResolver().openInputStream(uri);
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            byte[] bytes = readStream(input);
            BitmapFactory.decodeStream(new ByteArrayInputStream(bytes), new Rect(), options);
            input.close();

            array[0] = options.outWidth;
            array[1] = options.outHeight;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static Bitmap decodeSampledBitmapFromBitmap(Bitmap bitmap,
                                                       int reqWidth, int reqHeight) {

        int bytes = bitmap.getRowBytes() * bitmap.getHeight();

        ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
        bitmap.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

        byte[] byteArray = buffer.array(); //Get the underlying array containing the data.
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, Math.min(reqWidth, reqHeight), reqWidth * reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, Math.min(reqWidth, reqHeight), reqWidth * reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 计算图片的缩放值
     */
//    public static int calculateInSampleSize(BitmapFactory.Options options,
//                                            int reqWidth, int reqHeight) {
//        // Raw height and width of image
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//
//            // Calculate the largest inSampleSize value that is a power of 2 and
//            // keeps both
//            // height and width larger than the requested height and width.
//            while ((halfHeight / inSampleSize) > reqHeight
//                    && (halfWidth / inSampleSize) > reqWidth) {
//                inSampleSize *= 2;
//            }
//        }
//        return inSampleSize;
//    }
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565
                );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    // Bitmap转换成Drawable
    public Drawable bitmap2Drawable(Resources resources, Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(resources, bitmap);
        Drawable d = (Drawable) bd;
        return d;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(proj[0]);
            cursor.moveToFirst();
            final String path = cursor.getString(column_index);
            if (!TextUtils.isEmpty(path)) {
                return path;
            } else {
                return contentUri.getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return null;
    }

    /**
     * 压缩bitmap
     *
     * @param bitmap    image path
     * @param reqWidth  request width
     * @param reqHeight request height
     * @param format    The format of the compressed image,like Bitmap.CompressFormat.JPEG,Bitmap.CompressFormat.PNG
     * @param quality   Hint to the compressor, 0-100. 0 meaning compress for
     *                  small size, 100 meaning compress for max quality. Some
     *                  formats, like PNG which is lossless, will ignore the
     *                  quality setting
     */
    public static Bitmap compressBitmap(Bitmap bitmap, int reqWidth, int reqHeight, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, quality, baos);
        byte[] b = baos.toByteArray();
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    public static File writeBitmapToFile(Bitmap bitmap, int quality, String filePath) {
        FileOutputStream m_fileOutPutStream = null;
        try {
            m_fileOutPutStream = new FileOutputStream(filePath);//写入的文件路径
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, m_fileOutPutStream);
        try {
            m_fileOutPutStream.flush();
            m_fileOutPutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath);
    }

    public static int getImageDisplayOrientationByUri(Context context, Uri uri) {
        ContentResolver cr = context.getContentResolver();
        String[] columns = {MediaStore.Images.Media.ORIENTATION};
        Cursor cursor = cr.query(uri, columns, null, null, null);// 根据Uri从数据库中找
        if (cursor != null && cursor.moveToFirst()) {
            // 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
            int orientation = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Images.Media.ORIENTATION));// 获取旋转的角度
            cursor.close();
            return orientation;
        } else {
            //TODO 部分手机上，返回的path并不是真正的path，导致无法正确读取旋转角度。
            String filePath = uri.getPath();
            return readPictureDegree(filePath);
        }
    }

    /**
     * 旋转照片
     */
    public static Bitmap rotateBitmap(Bitmap b, int degrees) {
        Matrix m = new Matrix();
        m.postRotate(degrees);
        try {
            Bitmap b2 = Bitmap.createBitmap(
                    b, 0, 0, b.getWidth(), b.getHeight(), m, true);
            if (b != b2) {
                b.recycle();  //Bitmap操作完应该显式的释放
                b = b2;
            }
        } catch (OutOfMemoryError ex) {
            ex.printStackTrace();
            return b;
        }
        return b;
    }

    public static String getAbsoluteImagePath(Uri contentUri, Context context) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static Bitmap.CompressFormat getFileCompressFormat(String fileName) {
        if (TextUtils.isEmpty(fileName) || !fileName.contains(".")) {
            return Bitmap.CompressFormat.JPEG;
        }
        final String endName = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (endName.equals("png") || endName.equals("PNG")) {
            return Bitmap.CompressFormat.PNG;
        } else if (endName.equals("jpg") || endName.equals("jpeg") || endName.equals("JPG") || endName.equals("JPEG")) {
            return Bitmap.CompressFormat.JPEG;
        }
        return Bitmap.CompressFormat.JPEG;
    }
}
