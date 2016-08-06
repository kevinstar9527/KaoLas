package com.hyphenate.easeuisimpledemo;

import android.app.Application;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

public class DemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        EMOptions options  = new EMOptions();
        options.setAutoLogin(true);

        EaseUI.getInstance().init(this, null);
    }
    
}
