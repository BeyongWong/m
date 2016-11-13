package com.example.admin.mysimplenews;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;

/**
 * Created by admin on 2016/11/9.
 */

public class StatusBar {
        public static void setColor(Activity a, int c){
     if(Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT){
         a.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
         ViewGroup rootView = (ViewGroup) a.getWindow().getDecorView();
         // 获取隐藏的id
         int stausBarId = a.getResources().getIdentifier("status_bar_height", "dimen", "android");
         float dimension = a.getResources().getDimension(stausBarId);
         Log.e("tag", dimension + "");
         View statusView = new View(a);
         statusView.setLayoutParams(new FrameLayout.LayoutParams(a.getWindowManager().getDefaultDisplay().getWidth(), (int) dimension));
         statusView.setBackgroundColor(c);
         rootView.addView(statusView,0);
     }
    }

}
