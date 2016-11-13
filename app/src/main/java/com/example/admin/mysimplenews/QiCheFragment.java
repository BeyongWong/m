package com.example.admin.mysimplenews;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by admin on 2016/11/8.
 */

public class QiCheFragment extends BaseNewsFragment {
    @Override
    public void initData() {
        super.initData();
        String s = SPUtils.get(getClass().getName());
        if(TextUtils.isEmpty(s)) {
            loadData("http://c.m.163.com/nc/article/list/T1348654060988/0-20.html", 0);
        }else{
            loadSuccess(s,0);
        }
    }

    @Override
    public void loadSuccess(String con, int type) {
        if (type == 0) {
            relash(con);
            SPUtils.save(this.getClass().getName(),con);
        } else {
            loader(con);
        }
    }

    private void loader(String con) {
        isLoad = false;
        page++;
        addData(con);
    }

    private void relash(String con) {
        swip.setRefreshing(false);
        list.clear();
        addData(con);
    }

    private void addData(String con) {
        try {
            JSONArray arr = new JSONObject(con).getJSONArray("T1348654060988");
            if(arr.length()>2){
                setNormalState();
                for (int i = 2; i < arr.length(); i++) {
                    try {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        String imgsrc = jsonObject.getString("imgsrc");
                        String title = jsonObject.getString("title");
                        String digest = jsonObject.getString("digest");
                        String postid = jsonObject.getString("postid");
//                    if(!TextUtils.isEmpty(digest))  可以判断
                        list.add(new NewsItemBean(imgsrc, title, digest, postid));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }else{
                setNoDataState();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        loadData("http://c.m.163.com/nc/article/list/T1348654060988/0-20.html", 0);
    }

    @Override
    public void loadMore() {
        loadData("http://c.m.163.com/nc/article/list/T1348654060988/" + ((page + 1) * 20) + "-20.html", 1);
    }

    @Override
    public void tryAgain() {
        loadData("http://c.m.163.com/nc/article/list/T1348654060988/0-20.html", 0);
    }



    @Override
    public void loadMoreAgain() {
        loadData("http://c.m.163.com/nc/article/list/T1348654060988/" + ((page + 1) * 20) + "-20.html", 1);
    }

    int page = 1;
}
