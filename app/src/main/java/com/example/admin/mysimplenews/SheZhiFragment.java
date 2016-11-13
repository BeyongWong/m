package com.example.admin.mysimplenews;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by admin on 2016/11/10.
 */

public class SheZhiFragment extends BaseFragment {
    private WebView web;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View root=inflater.inflate(R.layout.fragment_shezhi,container,false);
        this.web = (WebView) root.findViewById(R.id.web);
        web.loadUrl("file:///android_asset/index.html");
        return root;
    }
}
