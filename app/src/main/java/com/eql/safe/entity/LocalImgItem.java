package com.eql.safe.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hanxingke on 14-3-29.
 */
public class LocalImgItem implements Parcelable {

    /**
     * ID
     */
    public int id;
    /**
     * 缩略图path
     */
    public String thumbnailPath;
    /**
     * 原图path
     */
    public String path;


    public LocalImgItem(){

    }


    public LocalImgItem(int id,String thumbnailPath,String path){

        this.id = id;
        this.thumbnailPath = thumbnailPath;
        this.path = path;
    }


    public LocalImgItem(Parcel parcel) {
        id = parcel.readInt();
        thumbnailPath = parcel.readString();
        path = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(thumbnailPath);
        parcel.writeString(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LocalImgItem> CREATOR = new Creator<LocalImgItem>() {
        @Override
        public LocalImgItem createFromParcel(Parcel parcel) {
            return new LocalImgItem(parcel);
        }

        @Override
        public LocalImgItem[] newArray(int i) {
            return new LocalImgItem[i];
        }
    };


}
