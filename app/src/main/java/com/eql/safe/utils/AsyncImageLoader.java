package com.eql.safe.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by hanxingke on 14-3-26.
 */
public class AsyncImageLoader {


    public static final int FADE_ANIM_DUR = 300;

    private static ImageLoader mImageLoader = ImageLoader.getInstance();


    public static void init(Context context) {

        ImageLoaderConfiguration config = getConfig(context);
        ImageLoader.getInstance().init(config);

    }


    public static void load(String imgUrl, ImageView imageView, int defaultImg) {


        load(imgUrl, imageView, defaultImg, null);

    }


    public static void load(String imgUrl, ImageView imageView) {


        load(imgUrl, imageView, 0, null);

    }


    public static void load(String imgUrl, ImageView imageView, SimpleImageLoadingListener loadingListener) {


        load(imgUrl, imageView, 0, loadingListener);

    }


    public static void load(String imgUrl, ImageView imageView,DisplayImageOptions options , SimpleImageLoadingListener loadingListener) {


        mImageLoader.displayImage(imgUrl, imageView, options, loadingListener);

    }



    public static void load(String imgUrl, ImageView imageView, int defaultImg, SimpleImageLoadingListener loadingListener) {


        DisplayImageOptions.Builder builder = getDefaultDisplayImageOptionsBuild();
        if (defaultImg > 0) {
            builder.showImageOnFail(defaultImg);
            builder.showImageForEmptyUri(defaultImg);
        }

        mImageLoader.displayImage(imgUrl, imageView, builder.build(), loadingListener);
    }


    public static void loadList(String imgUrl, ImageView imageView, DisplayImageOptions options,SimpleImageLoadingListener loadingListener) {

        mImageLoader.displayImage(imgUrl, imageView, options, loadingListener);


    }


    public static void loadList(String imgUrl, ImageView imageView, DisplayImageOptions options) {

        mImageLoader.displayImage(imgUrl, imageView, options);


    }


    private static ImageLoaderConfiguration getConfig(Context context) {


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheSize(60 * 1024 * 1024)
                .defaultDisplayImageOptions(getDefaultDisplayImageOptionsBuild().build())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()

                .build();
        return config;
    }


    public static DisplayImageOptions.Builder getDefaultDisplayImageOptionsBuild(int defaultImg,int failImg) {

        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .showImageForEmptyUri(defaultImg)
                .showImageOnFail(failImg)
                .showImageOnLoading(defaultImg)
                .imageScaleType(ImageScaleType.EXACTLY);
        return builder;
    }


    public static DisplayImageOptions.Builder getDefaultDisplayImageOptionsBuild() {

        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY);
        return builder;

    }


    public static DisplayImageOptions.Builder getListDisplayImageOptions(int failImg,int emptyImg) {


        DisplayImageOptions.Builder builder = getDefaultDisplayImageOptionsBuild();
        if(emptyImg > 0){

            builder.showImageForEmptyUri(emptyImg);
            builder.showImageOnLoading(emptyImg);
        }
        if(failImg > 0) {
            builder.showImageOnFail(failImg);
        }
        builder.cacheInMemory(true);
        builder.considerExifParams(true);
//        builder.displayer(new SimpleBitmapDisplayer());
        return builder;

    }

    public static DisplayImageOptions.Builder getListDisplayImageOptions() {

        return getListDisplayImageOptions(0,0);

    }

    public static DisplayImageOptions.Builder getListDisplayImageOptions(int defaultImg) {

        return getListDisplayImageOptions(defaultImg,defaultImg);

    }


    public static SimpleImageLoadingListener getListAnimOfSimpleImageLoadingListener(){

        return new AnimateFirstDisplayListener();
    }


    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, AsyncImageLoader.FADE_ANIM_DUR);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
