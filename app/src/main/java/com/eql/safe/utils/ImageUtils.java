package com.eql.safe.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;


public class ImageUtils {

	public static Bitmap getBitmapFromPath(String filePath,int width,int height) {

		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);

		opts.inJustDecodeBounds = false;
		opts.inSampleSize = computeSampleSize(opts, -1, width * height);

		try {
			bitmap = BitmapFactory.decodeFile(filePath, opts);
		} catch (Exception e) {
		}
		return bitmap;

	}


	public static Bitmap getBitmapFromPath(Context context,int resId,int width,int height) {

		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(),resId, opts);

		opts.inJustDecodeBounds = false;
		opts.inSampleSize = computeSampleSize(opts, -1, width * height);

		try {
			bitmap = BitmapFactory.decodeResource(context.getResources(),resId);
            return ThumbnailUtils.extractThumbnail(bitmap,width,height);
		} catch (Exception e) {
		}
		return bitmap;

	}

    //等比例缩放
    public static Bitmap adaptive(Bitmap bitmap,int targetWidth,int targetHeight) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();// 获取资源位图的宽
        int height = bitmap.getHeight();// 获取资源位图的高
        float w = targetWidth / bitmap.getWidth();
        float h = targetHeight / bitmap.getHeight();
        matrix.postScale(w, h);// 获取缩放比例
        // 根据缩放比例获取新的位图
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,matrix, true);
        return newbmp;
    }

    public static Bitmap cutPicture(Context context, int resId,int width, int height) {

        Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(resId)).getBitmap();
        if (bitmap != null) {
            float scaleWidth = ((float) width) / bitmap.getWidth();
            float scaleHeight = ((float) height) / bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return resizedBitmap;
        } else {
            return null;
        }
    }


	public static Bitmap getBitmapFromPath(String filePath){

		return getBitmapFromPath(filePath,480,800);
	}





	public static Bitmap getBitmapFromPath(Context context,int resId) {

		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeResource(context.getResources(), resId, null);
		} catch (Exception e) {
		}
		return bitmap;

	}




	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {
        if (width > height) {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        } else {
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }
    }
    return inSampleSize;
}


	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);
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

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));
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



	public static String getImageSavePath(Context context,String name){

		String filePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath()+"/"+name;
		return filePath;
	}



	// 缩放图片
	public static Bitmap zoomImg(String img, int newWidth ,int newHeight){
	// 图片源
	   Bitmap bm = BitmapFactory.decodeFile(img);
	   if(null!=bm){
	    return zoomImg(bm,newWidth,newHeight);
	   }
	   return null;
	}

	public static Bitmap zoomImg(Context context,String img, int newWidth ,int newHeight){
		// 图片源
		try {
			Bitmap bm = BitmapFactory.decodeStream(context.getAssets()
					.open(img));
			if (null != bm) {
				return zoomImg(bm, newWidth, newHeight);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 缩放图片
	public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
	   // 获得图片的宽高
	   int width = bm.getWidth();
	   int height = bm.getHeight();
	   // 计算缩放比例
	   float scaleWidth = ((float) newWidth) / width;
	   float scaleHeight = ((float) newHeight) / height;
	   // 取得想要缩放的matrix参数
	   Matrix matrix = new Matrix();
	   matrix.postScale(scaleWidth, scaleHeight);
	   // 得到新的图片
	   Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
	   return newbm;
	}


	public static Bitmap getRoundHead(String imgPath, int newWidth, int newHeight) {
		return toRoundBitmap(getBitmapFromPath(imgPath,newWidth,newHeight));
	}

    /**
     * 转换图片成圆形
     * @param bitmap 传入Bitmap对象
     * @return
     */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		if(!bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
		return output;
	}

    public static String getRightImgUrl(String imgUrl){

        if(TextUtils.isEmpty(imgUrl)){

            return "";
        }

        if (imgUrl.indexOf("[") != -1 && imgUrl.indexOf("http") != -1) {

            try {
                imgUrl = new JSONArray(imgUrl).getString(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return imgUrl;
    }

    public static String[] getRightImgArrayUrl(String imgUrl){

        if (imgUrl.indexOf("[") != -1 && imgUrl.indexOf("http") != -1) {

            try {
                JSONArray imgArray = new JSONArray(imgUrl);
                String[] imgs = new String[imgArray.length()];
                for(int i=0;i<imgArray.length();i++){

                    imgs[i] = imgArray.get(i).toString();
                }
                return imgs;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return new String[0];
    }





}
