package com.eql.safe;

import android.app.Application;

import com.eql.safe.utils.AsyncImageLoader;


/**
 * Created by hanxingke on 14-5-25.
 */
public class EqlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AsyncImageLoader.init(this);
    }
}
