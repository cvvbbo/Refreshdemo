package com.refreshdemo.test.Smartrefreashdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.refreshdemo.test.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class SmartRefreshActivity extends AppCompatActivity implements View.OnClickListener,MessageView{


    private int currentpage=1;
    private Boolean first_tag = true;
    private RecyclerView recyclerview;
    private MessageRecyclerAdapter adapter;
    private RefreshLayout refreshLayout;
    private List<MessageBean> messagedata=new ArrayList<>();
    private MessagePresenterimpl presenterimpl;
    private ImageView no_message_im;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_view1);

        presenterimpl = new MessagePresenterimpl(this);
        no_message_im = (ImageView) findViewById(R.id.no_message_im);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageRecyclerAdapter(this);
        // 当前未第一页,请求网络，然后这个类只实现当有数据的时候
        presenterimpl.getData(1,true);
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);

        ClassicsHeader header = new ClassicsHeader(this);
        // todo 隐藏刷新显示的时间
        header.setEnableLastTime(false);
        //header.setFinishDuration(0);
        ClassicsFooter footer = new ClassicsFooter(this);
        //footer.setFinishDuration(0);
        refreshLayout.setRefreshHeader(header);
        refreshLayout.setRefreshFooter(footer);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.e("---->","下拉刷新");
                refreshlayout.finishRefresh(500);
                if (!first_tag) {
                    presenterimpl.getData(1, false);
                }
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Log.e("---->","上拉加载");
                refreshlayout.finishLoadmore(500);
                if (currentpage!=1) {
                    presenterimpl.getData(currentpage, false);
                }

            }
        });

        adapter.setOnItemClickListener(new MessageRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("---->",newState+"");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("--->","onScrolled");

                // todo 返回false的时候就是表示已经滑动到顶部或者底部
                boolean canscroll = recyclerView.canScrollVertically(1);
                if (!canscroll) {
                    currentpage++;
                    Log.e("--->","当前的页数是--> "+ currentpage);
                    Log.e("-->","已经滑倒底部");
                }else {
                    Log.e("--->","没有滑倒底部");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getdatas(final List<MessageBean> datas, Boolean isfirst) {


        // todo 当这里传过来来的数据为null时候，可能会报错（还是从有网到无网的时候会报错）
        // 这个是当没有网的时候
        if (datas == null) {
            no_message_im.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
            return;
        }

        // 这是正常有数据的时候
        else if (datas.size() != 0) {
            no_message_im.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
        }
        // 这个是一开始就没有数据的时候
        else {
            no_message_im.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
            return;
        }

        // 头一次进来的时候的下拉刷新
        if (isfirst) {
            messagedata = datas;
            adapter.setdatas(datas);
            recyclerview.setAdapter(adapter);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        first_tag = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //  todo 可以理解为手动刷新？？
        else {
            messagedata=datas;
            recyclerview.setAdapter(adapter);
            adapter.setdatas(datas);
        }

    }

    @Override
    public void getmoredatas(List<MessageBean> datas) {
        //messagedata.addAll(datas);
        adapter.adddatas(datas);

    }

    @Override
    public void gettotalPage(int totalpage) {

    }
}
