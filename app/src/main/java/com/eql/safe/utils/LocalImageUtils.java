package com.eql.safe.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;


import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.SparseArray;

import com.eql.safe.entity.ImageDir;
import com.eql.safe.entity.LocalImgItem;

import java.util.ArrayList;


/**
 * 用于操作本地图片类
 * Created by hanxingke on 14-3-29.
 */
public class LocalImageUtils {

    private static LocalImageUtils localImageUtils;
    Context context;
    ContentResolver resolver;

    private LocalImageUtils() {




    }


    private LocalImageUtils(Context context) {

        this.resolver = context.getContentResolver();
        this.context = context;

    }



    public static LocalImageUtils newInstance(Context appContext) {

        if (localImageUtils == null)
            localImageUtils = new LocalImageUtils(appContext);
        return localImageUtils;

    }

    /**
     * 获取sd卡中所有的图片（包括子文件）
     * @return
     */
    public ArrayList<ImageDir> getLocalImgList() {

        // 构造相册索引
        String columns[] = new String[]{Media._ID, Media.BUCKET_ID,
                Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
                Media.SIZE, Media.BUCKET_DISPLAY_NAME};
        Cursor cursor = null;


        SparseArray<String> thumbnailArray = getThumbnailInfo();
        SparseArray<ImageDir> dirSparseArray = new SparseArray<ImageDir>();

        ArrayList<ImageDir> imgDir = new ArrayList<ImageDir>();


        try {

            cursor = resolver.query(Media.EXTERNAL_CONTENT_URI, columns, null, null, null);


            while (cursor.moveToNext()) {

                initLocalImgItemFromCursor(imgDir, cursor, dirSparseArray, thumbnailArray);

            }

            return imgDir;


        } finally {

            if (cursor != null) {

                cursor.close();
                cursor = null;
            }
            thumbnailArray.clear();
            dirSparseArray.clear();
        }



    }


    /**
     * 获取缩略图信息
     * @return
     */
    public SparseArray<String> getThumbnailInfo() {

        String[] projection = {Thumbnails.IMAGE_ID,
                Thumbnails.DATA};
        SparseArray<String> sparseArray = new SparseArray<String>();

        Cursor cursor = null;

        try {

            cursor =resolver.query(Thumbnails.EXTERNAL_CONTENT_URI, projection,
                    null, null, null);

            int columnIdIndex = cursor.getColumnIndex(Thumbnails.IMAGE_ID);
            int columnDataIndex = cursor.getColumnIndex(Thumbnails.DATA);

            String data;
            int imgId;
            while (cursor.moveToNext()) {

                imgId = cursor.getInt(columnIdIndex);
                data = cursor.getString(columnDataIndex);
                sparseArray.put(imgId, data);

            }

            return sparseArray;

        } finally {

            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

    }

    private void initLocalImgItemFromCursor(ArrayList<ImageDir> imgDir, Cursor cursor, SparseArray<ImageDir> dirSparseArray, SparseArray<String> thumbnailArray) {

        int photoIDIndex = cursor.getColumnIndexOrThrow(Media._ID);
        int photoPathIndex = cursor.getColumnIndexOrThrow(Media.DATA);
        int photoNameIndex = cursor.getColumnIndexOrThrow(Media.DISPLAY_NAME);
        int photoTitleIndex = cursor.getColumnIndexOrThrow(Media.TITLE);
        int photoSizeIndex = cursor.getColumnIndexOrThrow(Media.SIZE);
        int bucketDisplayNameIndex = cursor
                .getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
        int bucketIdIndex = cursor.getColumnIndexOrThrow(Media.BUCKET_ID);
        int picasaIdIndex = cursor.getColumnIndexOrThrow(Media.PICASA_ID);

        int bucketId = cursor.getInt(bucketIdIndex);
        int picasaId = cursor.getInt(picasaIdIndex);

        String thumbnailPath = thumbnailArray.get(picasaId);
        String path = cursor.getString(photoPathIndex);
        String dirName = cursor.getString(bucketDisplayNameIndex);

        ImageDir imageDir = dirSparseArray.get(bucketId);
        if (imageDir == null) {
            imageDir = new ImageDir(dirName);
            dirSparseArray.put(bucketId, imageDir);
            imgDir.add(imageDir);
        }
        LocalImgItem item = new LocalImgItem(picasaId, thumbnailPath, path);
        imageDir.addItem(item);

    }


}
