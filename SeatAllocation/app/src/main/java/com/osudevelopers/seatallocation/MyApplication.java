package com.osudevelopers.seatallocation;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuta on 2016/10/18.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
