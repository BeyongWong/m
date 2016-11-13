package com.example.admin.mysimplenews;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/11/10.
 */

public class TianQiFragment extends BaseFragment {
    private android.widget.TextView tianqiTv;
    private android.widget.ImageView tianqiIv;
    private android.widget.TextView fengxiangtv;
    private android.widget.RelativeLayout jintian;
    private android.support.v7.widget.RecyclerView recycler;
    private LoadView loadView;
    private TextView wenDuTv;
    List<TianQiBean.DataBean.ForecastBean> list=new ArrayList<>();
    private MyAdapter adapter;
    Map<String,Integer> map=new HashMap<>();

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View root=inflater.inflate(R.layout.fragment_tianqi,container,false);
        this.recycler = (RecyclerView) root.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        this.jintian = (RelativeLayout) root.findViewById(R.id.jintian);
        this.fengxiangtv = (TextView) root.findViewById(R.id.fengxiangtv);
        this.tianqiIv = (ImageView) root.findViewById(R.id.tianqiIv);
        this.tianqiTv = (TextView) root.findViewById(R.id.tianqiTv);
        wenDuTv = (TextView) root.findViewById(R.id.wenduTv);
        loadView = new LoadView(getActivity());
        loadView.show();
        adapter = new MyAdapter();
        map.put("暴雪",R.mipmap.biz_plugin_weather_baoxue);
        map.put("暴雨",R.mipmap.biz_plugin_weather_baoyu);
        map.put("暴雪",R.mipmap.biz_plugin_weather_daxue);
        map.put("多云",R.mipmap.biz_plugin_weather_duoyun);
        map.put("雷阵雨",R.mipmap.biz_plugin_weather_leizhenyu);
        map.put("特大暴雨",R.mipmap.biz_plugin_weather_tedabaoyu);
        map.put("雾",R.mipmap.biz_plugin_weather_wu);
        map.put("霾",R.mipmap.biz_plugin_weather_wu);
        map.put("小雪",R.mipmap.biz_plugin_weather_xiaoxue);
        map.put("小雨",R.mipmap.biz_plugin_weather_xiaoyu);
        map.put("阴",R.mipmap.biz_plugin_weather_yin);
        map.put("阵雪",R.mipmap.biz_plugin_weather_zhenxue);
        map.put("阵雨",R.mipmap.biz_plugin_weather_zhenyu);
        map.put("中雪",R.mipmap.biz_plugin_weather_zhongxue);
        map.put("中雨",R.mipmap.biz_plugin_weather_zhongyu);
        return root;
    }

    @Override
    public void initAdapter() {
        super.initAdapter();

        recycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        HttpUtils.getInstance().loadGetData("http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC", new HttpUtils.OnLoadDataListener() {
            @Override
            public void success(String con) {
                loadView.dismiss();
                Log.e("tag",con);
                TianQiBean tianQiBean = new Gson().fromJson(con, TianQiBean.class);
                TianQiBean.DataBean.ForecastBean forecastBean = tianQiBean.getData().getForecast().get(0);
                tianqiTv.setText("北京  "+forecastBean.getDate());
                wenDuTv.setText(forecastBean.getHigh()+"  "+forecastBean.getLow());
                fengxiangtv.setText(forecastBean.getFengxiang()+"\n"+forecastBean.getType());
                Integer integer = map.get(forecastBean.getType());
                if(integer==null)
                    integer=R.mipmap.biz_plugin_weather_wu;
                tianqiIv.setImageResource(integer);
                tianQiBean.getData().getForecast().remove(forecastBean);
                list.addAll(tianQiBean.getData().getForecast());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void faild(String msg) {
                loadView.dismiss();
                Snackbar.make(recycler,"联网失败",Snackbar.LENGTH_INDEFINITE).setAction("点击重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initData();
                    }
                }).show();
            }
        });
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(getActivity(),R.layout.item_tiqnai,null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            TianQiBean.DataBean.ForecastBean forecastBean = list.get(position);
            holder.dataTv.setText(forecastBean.getDate());
            holder.wenDuTv.setText(forecastBean.getHigh()+"  "+forecastBean.getLow());
//            Log.e("tag",holder.fengxiangTv+"");
            holder.fengxiang1Tv.setText(forecastBean.getFengxiang()+"\n"+forecastBean.getType());
            Integer integer = map.get(forecastBean.getType());
            if(integer==null)
                integer=R.mipmap.biz_plugin_weather_wu;
            holder.iv.setImageResource(integer);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dataTv;
        ImageView iv;
        TextView wenDuTv;
        TextView fengxiang1Tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            dataTv= (TextView) itemView.findViewById(R.id.dateTv);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            wenDuTv= (TextView) itemView.findViewById(R.id.wenduTv);
            fengxiang1Tv= (TextView) itemView.findViewById(R.id.abcTv);
            RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(dp2px(250),dp2px(250));
        itemView.setLayoutParams(params);
        }
    }
    private  int dp2px(int dp){
        return (int) (getResources().getDisplayMetrics().density*dp);
    }
}
