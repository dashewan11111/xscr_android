package com.adult.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

/***
 * 
 * @author liyuj
 *
 */
public class ImageUtil {

	// 图片缓存到SD卡的路径
	private static final String SDCARD_CACHE_IMG_PATH = Environment
			.getExternalStorageDirectory().getPath() + "/ccigmall/images/";
	public static final String IMAGE_UNSPECIFIED = "image/*";

	/**
	 * 保存图片到SD卡
	 * 
	 * @param imagePath
	 * @param buffer
	 * @throws IOException
	 */
	public static void saveImage(String imagePath, byte[] buffer)
			throws IOException {
		File f = new File(imagePath);
		if (f.exists()) {
			return;
		} else {

			File parentFile = f.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(imagePath);
			fos.write(buffer);
			fos.flush();
			fos.close();
		}
	}

	/**
	 * 获取屏幕的宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}

	/**
	 * 获取屏幕的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getHeight();
	}

	/**
	 * 写图片文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
	 * 
	 * @throws IOException
	 */
	public static void saveImage(Context context, String fileName, Bitmap bitmap)
			throws IOException {
		saveImage(context, fileName, bitmap, 100);
	}

	public static void saveImage(Context context, String fileName,
			Bitmap bitmap, int quality) throws IOException {
		if (bitmap == null)
			return;

		FileOutputStream fos = context.openFileOutput(fileName,
				Context.MODE_PRIVATE);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, quality, stream);
		byte[] bytes = stream.toByteArray();
		fos.write(bytes);
		fos.close();
	}

	/**
	 * 从SD卡加载图片
	 * 
	 * @param imagePath
	 * @return
	 */
	public static Bitmap getImageFromLocal(String imagePath) {
		File file = new File(imagePath);
		if (file.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			file.setLastModified(System.currentTimeMillis());
			return bitmap;
		}
		return null;
	}

	public static Bitmap loadImgThumbnail(String filePath, int w, int h) {
		Bitmap bitmap = getBitmapByPath(filePath);
		return zoomBitmap(bitmap, w, h);
	}

	/**
	 * 放大缩小图片
	 * 
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		Bitmap newbmp = null;
		if (bitmap != null) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			Matrix matrix = new Matrix();
			float scaleWidht = ((float) w / width);
			float scaleHeight = ((float) h / height);
			matrix.postScale(scaleWidht, scaleHeight);
			newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
					true);
		}
		return newbmp;
	}

	/**
	 * 获取bitmap
	 * 
	 * @param filePath
	 * @return
	 */
	public static Bitmap getBitmapByPath(String filePath) {
		return getBitmapByPath(filePath, null);
	}

	public static Bitmap getBitmapByPath(String filePath,
			BitmapFactory.Options opts) {
		FileInputStream fis = null;
		Bitmap bitmap = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bitmap = BitmapFactory.decodeStream(fis, null, opts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return bitmap;
	}

	/**
	 * Bitmap转换到Byte[]
	 * 
	 * @param bm
	 * @return
	 */
	public static byte[] bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, bas);
		return bas.toByteArray();
	}

	// 返回图片存到sd卡的路径
	public static String getCacheImgPath() {
		return SDCARD_CACHE_IMG_PATH;
	}

	/**
	 * 将指定byte数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	/*
	 * public static String byteToHexString(byte[] b) { StringBuffer hexString =
	 * new StringBuffer(); for (int i = 0; i < b.length; i++) { String hex =
	 * Integer.toHexString(b[i] & 0xFF); if (hex.length() == 1) { hex = '0' +
	 * hex; } hexString.append(hex.toUpperCase()); } return
	 * hexString.toString(); }
	 */

	// 根据uri获得图片真实地址
	public static String getPath(Uri uri, Context context) {
		String[] proj = { MediaStore.Images.Media.DATA };
		ContentResolver cr = context.getContentResolver();
		Cursor cursor = cr.query(uri, proj, null, null, null);
		cursor.moveToFirst();
		int actual_image_column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		return cursor.getString(actual_image_column_index);

	}

	/**
	 * 按指定比例压缩
	 * 
	 * @param srcBitmap
	 * @param percent
	 * @return
	 */
	public static Bitmap bitmapZoomByPercent(Bitmap srcBitmap, double percent) {
		int srcWidth = srcBitmap.getWidth();
		int srcHeight = srcBitmap.getHeight();

		float scaleWidth = (float) percent;
		float scaleHeight = (float) percent;

		return bitmapZoomByScale(srcBitmap, scaleWidth, scaleHeight);
	}

	/**
	 * 使用长宽缩放比缩放
	 * 
	 * @param srcBitmap
	 * @param scaleWidth
	 * @param scaleHeight
	 * @return
	 */
	public static Bitmap bitmapZoomByScale(Bitmap srcBitmap, float scaleWidth,
			float scaleHeight) {
		int srcWidth = srcBitmap.getWidth();
		int srcHeight = srcBitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcWidth,
				srcHeight, matrix, true);
		if (resizedBitmap != null) {
			return resizedBitmap;
		} else {

			return srcBitmap;
		}
	}

	/**
	 * 根据bitmap创建file对象
	 * 
	 * @param context
	 *            上下文
	 * @param filePath
	 *            文件绝对路径
	 * @param bitmap
	 *            要生成File的bitMap
	 * @return
	 */
	public static File generatorFileFromBitmap(Context context,
			String filePath, Bitmap bitmap) {
		System.gc();
		File file = new File(filePath);
		FileOutputStream outStream = null;
		ByteArrayOutputStream bos = null;
		try {
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
				outStream = new FileOutputStream(file);
				bos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				byte[] mPicdat = bos.toByteArray();
				outStream.write(mPicdat);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bitmap != null && !bitmap.isRecycled()) {
				// bitmap.recycle();
			}
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 删除本地存储的分享的icon
	 * 
	 * @param context
	 */
	public static void deleteShareIcon(Context context) {
		File iconFile = new File(ImageUtil.getCacheImgPath()
				+ "share_ic_launcher.png");
		if (iconFile != null && iconFile.exists()) {
			iconFile.delete();
		}
	}

	/**
	 * Get round corners without background.
	 * 
	 * @param bitmap
	 * @param roundPx
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmapNoBackground(Bitmap bitmap,
			float roundPx) {
		return getRoundedCornerBitmap(bitmap, roundPx);
	}

	/**
	 * Get round corners
	 * 
	 * @param bitmap
	 *            Orgin image
	 * @param roundPx
	 * @return
	 */
	protected static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * Get round corners with background.
	 * 
	 * @param width
	 * @param height
	 * @param roundPx
	 * @param color
	 * @return
	 */
	protected static Bitmap getRoundedCornerBitmapWithBackground(int width,
			int height, float roundPx, int color) {
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, width, height);
		final RectF rectF = new RectF(rect);
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(color);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		return output;
	}

	/**
	 * Get round corners with background.
	 * 
	 * @param bitmap
	 * @param color
	 * @param intervalPx
	 * @param roundPx
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmapWithBackground(Bitmap bitmap,
			int color, int intervalPx, float roundPx) {
		Bitmap temp = getRoundedCornerBitmap(bitmap, roundPx);
		Bitmap output = Bitmap.createBitmap(temp.getWidth() + intervalPx * 2,
				temp.getHeight() + intervalPx * 2, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());
		final RectF rectF = new RectF(rect);
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		canvas.drawBitmap(temp, intervalPx, intervalPx, null);
		return output;
	}

	public static Bitmap getRightTopRoundedCornerBitmap(Bitmap bitmap,
			float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(-20, 0, bitmap.getWidth(),
				bitmap.getHeight() + 20);

		final Paint paint = new Paint();
		int color = 0xff424242;
		paint.setColor(color);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Bitmap getRightBottomRoundedCornerBitmap(Bitmap bitmap,
			float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(-20, -20, bitmap.getWidth(),
				bitmap.getHeight());

		final Paint paint = new Paint();
		int color = 0xff424242;
		paint.setColor(color);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * Resize bitmap.
	 * 
	 * @param bitmap
	 * @param newHeight
	 * @param newWidth
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, double newHeight,
			double newWidth) {
		Bitmap result = bitmap;
		if (newHeight > 0 && newWidth > 0) {
			int bmpHeight = bitmap.getHeight();
			int bmpWidth = bitmap.getWidth();
			if (bmpHeight > newWidth || bmpWidth > newHeight) {
				DecimalFormat df2 = new DecimalFormat("###.000");
				float wScale = Float.parseFloat(df2
						.format(newHeight / bmpWidth));
				float hScale = Float.parseFloat(df2
						.format(newWidth / bmpHeight));
				Matrix matrix = new Matrix();
				matrix.postScale(Math.min(wScale, hScale),
						Math.min(wScale, hScale));

				result = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight,
						matrix, true);
			}
		}
		return result;
	}

	/**
	 * Crop Bitmap.
	 * 
	 * @param bmp
	 * @return
	 */
	public static Bitmap cropBitmap(Bitmap bmp) {
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		int sc = width > height ? height : width;
		int retX = width > height ? (width - height) / 2 : 0;
		int retY = width > height ? 0 : (height - width) / 2;
		return Bitmap.createBitmap(bmp, retX, retY, sc, sc, null, false);
	}

	/**
	 * Load Bitmap
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Bitmap loadBitmap(File file) throws FileNotFoundException {
		BitmapFactory.Options bitopt = new BitmapFactory.Options();
		bitopt.inSampleSize = 1;
		FileInputStream fis = new FileInputStream(file);
		Bitmap bm = BitmapFactory.decodeStream(fis);
		try {
			fis.close();
		} catch (IOException e) {
			Log.e("ImageUtil", "loadBitmap", e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					Log.e("ImageUtil", "loadBitmap", e);
				}
			}
		}
		return bm;
	}

	/**
	 * Make canescent Bitmap.
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap toGrayBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap grayImg = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(grayImg);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		float contrast = 0.7f;
		float brightness = -25;
		cm.set(new float[] { contrast, 0, 0, 0, brightness, 0, contrast, 0, 0,
				brightness, 0, 0, contrast, 0, brightness, 0, 0, 0, contrast, 0 });
		paint.setColorFilter(new ColorMatrixColorFilter(cm));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return grayImg;
	}

	/**
	 * Drawable に　bitmapを転換する
	 * 
	 * @param resources
	 * @param drawable
	 * @return
	 */
	public static Bitmap toDrawable(Resources resources, int drawable) {
		InputStream is = null;
		Bitmap mBitmap = null;
		try {
			is = resources.openRawResource(drawable);
			mBitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			Log.e("ImageUtil", "toDrawable", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					Log.e("ImageUtil", "toDrawable", e);
				}
			}
		}
		return mBitmap;
	}

	public static Bitmap reSizeBitmap(String filePath) {
		Bitmap bm = getSmallBitmap(filePath);
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			baos.close();
		} catch (IOException e) {
			Log.e("ImageUtil", "reSizeBitmap", e);
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					Log.e("ImageUtil", "reSizeBitmap", e);
				}
			}
		}
		return bm;
	}

	public static Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		int width = options.outWidth;
		int height = options.outHeight;
		int reqWidth = 0;
		int reqHeight = 0;
		float scal = height / (float) width;
		float scal2 = width / (float) height;
		if (scal > scal2) {
			if (height > 720) {
				reqHeight = 720;
				reqWidth = (int) (reqHeight / scal);
			} else {
				reqHeight = height;
				reqWidth = width;
			}
		} else {
			if (width > 720) {
				reqWidth = 720;
				reqHeight = (int) (reqWidth / scal2);
			} else {
				reqWidth = width;
				reqHeight = height;
			}
		}
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

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
			default:
				degree = 0;
				break;
			}
		} catch (IOException e) {
			Log.e("ImageUtil", "readPictureDegree", e);
		}
		return degree;
	}

	public static Bitmap postRotate(Bitmap bmp, float degree) {
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix,
				true);
		return bitmap;
	}

	/**
	 * Free up the memory
	 */
	public static void recycleBitmapDrawable(Drawable d) {
		if (d != null) {
			if (d instanceof BitmapDrawable) {
				BitmapDrawable bd = (BitmapDrawable) d;
				bd.getBitmap().recycle();
				bd = null;
			}
		}
	}

	/**
	 * Sets a Bitmap as the content of this ImageView.
	 * 
	 * @param iv
	 *            The ImageView to set
	 * @param bm
	 *            The bitmap to set
	 */
	public static void setImageBitmap(ImageView iv, Bitmap bm) {
		if (iv != null && bm != null) {
			recycleBitmapDrawable(iv.getDrawable());
			iv.setImageBitmap(bm);
		}
	}

	/**
	 * Set the background to a given Drawable, or remove the background.
	 * 
	 * @param iv
	 *            The ImageView to set
	 * @param bm
	 *            The bitmap to set
	 */
	public static void setBackgroundDrawable(ImageView iv, Bitmap bm) {
		if (iv != null && bm != null) {
			recycleBitmapDrawable(iv.getBackground());
			iv.setBackgroundDrawable(new BitmapDrawable(bm));
		}
	}

	/**
	 * Set the background to a given resource. The resource should refer to a
	 * Drawable object or 0 to remove the background.
	 * 
	 * @param iv
	 *            The ImageView to set
	 * @param resid
	 *            The identifier of the resource.
	 */
	public static void setBackgroundResource(ImageView iv, int resid) {
		if (iv != null && resid > 0) {
			recycleBitmapDrawable(iv.getBackground());
			iv.setBackgroundResource(resid);
		}
	}
}
