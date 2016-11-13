package com.example.admin.mysimplenews;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/7.
 */

public class TuPianFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private android.support.v7.widget.RecyclerView recycler;
    private SwipeRefreshLayout swip;
    private List<TuPianBean> list;
    private LinearLayoutManager linearLayoutManager;
    private TuPianAdapter tuPianAdapter;
    private View emptyView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_tupian, container, false);
        this.swip = (SwipeRefreshLayout) rootView.findViewById(R.id.swip);
        this.recycler = (RecyclerView) rootView.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);
        swip.setColorSchemeColors(Color.parseColor("#FF4081"));
        swip.setOnRefreshListener(this);
        emptyView = rootView.findViewById(R.id.emptyView);
        emptyView.setOnClickListener(this);
        list = new ArrayList<>();
        Log.e("tag", "onCreateView");
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState){
//                    case RecyclerView.SCROLL_STATE_IDLE:
//                        Log.e("tag","空闲");
//                        break;
//                    case RecyclerView.SCROLL_STATE_SETTLING:
//                        Log.e("tag","松手");
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        Log.e("tag","拖动");
//                        break;
//                }
                // 到达尾部 判断一次
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                    View lastView = linearLayoutManager.findViewByPosition(lastVisibleItemPosition);

                    if (lastVisibleItemPosition == list.size() - 1 && lastView != null) {
//                        Log.e("tag","到达了尾部");

                        if (recyclerView.getHeight() == lastView.getBottom()) {
                            // 到达尾部
//                            Toast.makeText(getActivity(),"此页面值显示20条数据，更多数据下拉刷新",Toast.LENGTH_SHORT).show();
                            Snackbar.make(swip, "此页面值显示20条数据，更多数据下拉刷新", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.e("tag","滚动--》"+dy);

            }
        });
//        recycler.addon
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        swip.setRefreshing(true);
        HttpUtils.getInstance().loadGetData("http://api.laifudao.com/open/tupian.json", new HttpUtils.OnLoadDataListener() {
            @Override
            public void success(String con) {
                //json 解析 面向业务逻辑变成
                // 添加数据
                try {
                    JSONArray arr = new JSONArray(con);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        list.add(new TuPianBean(jsonObject.getString("title"), jsonObject.getString("sourceurl"), jsonObject.getInt("height"), jsonObject.getInt("width")));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    swip.setRefreshing(false);
                    tuPianAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void faild(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                // 切换失败布局
                swip.setRefreshing(false);
                if (list.size() == 0)
                    emptyView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        tuPianAdapter = new TuPianAdapter();
        recycler.setAdapter(tuPianAdapter);
    }


    @Override
    public void onRefresh() {
        loadData();
    }

    private void loadData() {
        // 加载 数据
        HttpUtils.getInstance().loadGetData("http://api.laifudao.com/open/tupian.json", new HttpUtils.OnLoadDataListener() {
            @Override
            public void success(String con) {
                list.clear();
                try {
                    JSONArray arr = new JSONArray(con);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        list.add(new TuPianBean(jsonObject.getString("title"), jsonObject.getString("sourceurl"), jsonObject.getInt("height"), jsonObject.getInt("width")));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    tuPianAdapter.notifyDataSetChanged();
                    swip.setRefreshing(false);
                }
            }

            @Override
            public void faild(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                swip.setRefreshing(false);
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        swip.setRefreshing(true);
        emptyView.setVisibility(View.GONE);
        loadData();
    }

    class TuPianAdapter extends RecyclerView.Adapter<TuPianHodler> {

        @Override
        public TuPianHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TuPianHodler(View.inflate(getActivity(), R.layout.item_tupian, null));
        }

        @Override
        public void onBindViewHolder(TuPianHodler holder, int position) {
            TuPianBean tuPianBean = list.get(position);
            holder.titleTv.setText(tuPianBean.title);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getActivity().getWindowManager().getDefaultDisplay().getWidth(), tuPianBean.height);
            holder.imageView.setLayoutParams(params);

            Glide.with(getActivity()).load(tuPianBean.imagePath).placeholder(R.drawable.jianbina).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class TuPianHodler extends RecyclerView.ViewHolder {
        TextView titleTv;
        ImageView imageView;

        public TuPianHodler(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
