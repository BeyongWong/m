package com.example.admin.mysimplenews;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2016/11/8.
 */

public class NBAFragment extends BaseNewsFragment {
    @Override
    public void initData() {
        super.initData();
        String s = SPUtils.get(getClass().getName());
        if(TextUtils.isEmpty(s)) {
            loadData("http://c.m.163.com/nc/article/list/T1348649145984/0-20.html", 0);
        }else{
            loadSuccess(s,0);
        }
    }

    @Override
    public void loadSuccess(String con, int type) {
        if (type == 0) {
            relash(con);
            // 任何时候，只要请求成功第一页都要缓存
            SPUtils.save(this.getClass().getName(),con);
        } else {
            load(con);
        }
    }

    int page = 1;

    private void load(String con) {
        isLoad = false;
        page++;
        addData(con);
    }

    private void relash(String con) {
        list.clear();
        swip.setRefreshing(false);
       addData(con);
    }

    private void addData(String con) {
        try {
            JSONArray arr = new JSONObject(con).getJSONArray("T1348649145984");
            if(arr.length()>2){
                setNormalState();
                for(int i=2;i<arr.length();i++){
                    try {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        String imgsrc = jsonObject.getString("imgsrc");
                        String title = jsonObject.getString("title");
                        String digest = jsonObject.getString("digest");
                        String postid = jsonObject.getString("postid");
//                    if(!TextUtils.isEmpty(digest))  可以判断
                        list.add(new NewsItemBean(imgsrc,title,digest,postid));

                    }catch (Exception e){
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
        loadData("http://c.m.163.com/nc/article/list/T1348649145984/0-20.html", 0);
    }

    @Override
    public void loadMore() {
        loadData("http://c.m.163.com/nc/article/list/T1348649145984/"+((page+1)*20)+"-20.html", 1);
    }

    @Override
    public void tryAgain() {
        loadData("http://c.m.163.com/nc/article/list/T1348649145984/0-20.html", 0);
    }



    @Override
    public void loadMoreAgain() {
        loadData("http://c.m.163.com/nc/article/list/T1348649145984/"+((page+1)*20)+"-20.html", 1);
    }
}
