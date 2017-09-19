package com.ixzus.appkotlin;

import android.app.Application;
import android.graphics.Color;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import es.dmoral.toasty.Toasty;


/**
 * Created by huan on 2017/9/19.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Toasty.Config.getInstance()
                .setInfoColor(Color.BLUE) // optional
                .setTextColor(Color.WHITE) // optional
                .setTextSize(14) // optional
                .apply(); // required
    }
}
