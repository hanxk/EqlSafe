package com.eql.safe.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 图片目录
 * Created by hanxingke on 14-3-29.
 */
public final  class ImageDir implements Parcelable {

    public String dirName;
    public ArrayList<LocalImgItem> imgList;


    private ImageDir(){

    }

    public ImageDir(String dirName){

        this.dirName = dirName;
        this.imgList = new ArrayList<LocalImgItem>();
    }

    public void addItem(LocalImgItem item){

        this.imgList.add(item);

    }



    public ImageDir(Parcel parcel) {
        this.dirName = parcel.readString();
        this.imgList = parcel.readArrayList(LocalImgItem.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dirName);
        parcel.writeList(imgList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageDir> CREATOR = new Creator<ImageDir>() {
        @Override
        public ImageDir createFromParcel(Parcel parcel) {
            return new ImageDir(parcel);
        }

        @Override
        public ImageDir[] newArray(int i) {
            return new ImageDir[i];
        }
    };






}
