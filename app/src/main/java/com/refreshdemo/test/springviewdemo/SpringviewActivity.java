package com.refreshdemo.test.springviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.aliheader.AliFooter;
import com.liaoinstan.springview.aliheader.AliHeader;
import com.liaoinstan.springview.aliheader.SpringView;
import com.refreshdemo.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SpringviewActivity extends AppCompatActivity implements View.OnClickListener,MessageView{


    private TextView readed_tv;
    private RecyclerView recyclerview;
    private MessageRecyclerAdapter adapter;
    private SpringView springView;
    private LinearLayout lefticonrl;
    private ImageView backim;
    private HashMap<Integer, Boolean> pointdata;

    private List<MessageBean> messagedata=new ArrayList<>();

    private boolean reBuildview =true;
    private MessagePresenterimpl presenterimpl;
    private ImageView no_message_im;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_view);

        presenterimpl = new MessagePresenterimpl(this);
        no_message_im = (ImageView) findViewById(R.id.no_message_im);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageRecyclerAdapter(this);
        // 当前未第一页,请求网络，然后这个类只实现当有数据的时候
        presenterimpl.getData(1,true);
        springView = (SpringView) findViewById(R.id.springview);
        springView.setType(SpringView.Type.FOLLOW);

        // 设置监听，这些都是手动刷新
        // 自动的下拉刷新是能够监听到的
        //  刷新和加载结果的回调
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Log.e("--->","下拉刷新");
                //如果不走这个方法，那么刷新或者加载就停不下来
                //springView.onFinishFreshAndLoad();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //  不是头一次进来
                        presenterimpl.getData(1,false);
                        springView.onFinishFreshAndLoadDelay();

                    }
                },800);
            }

            @Override
            public void onLoadmore() {
                Log.e("--->","上拉加载");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 这个页数是要从recyclerview中判断出来的
                        presenterimpl.getData(2,false);
                       // springView.onFinishFreshAndLoadDelay();
                    }
                },800);
            }

        });

        //添加阿里巴巴的头部和尾部
        springView.setHeader(new AliHeader(this, 0, true));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new AliFooter(this, true));
        adapter.setOnItemClickListener(new MessageRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //pointdata.put(position,true);

            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getdatas(final List<MessageBean> datas, Boolean isfirst) {

        // 这个是当没有网的时候
        if (datas==null){
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

        if (isfirst) {
            // https://github.com/liaoinstan/SpringView/issues/58
            messagedata = datas;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.setdatas(datas);
                    recyclerview.setAdapter(adapter);
                    springView.callFresh();
                }
            }, 100);

        }else {
            recyclerview.setAdapter(adapter);
            adapter.setdatas(datas);
        }

    }

    @Override
    public void getmoredatas(List<MessageBean> datas) {
        adapter.adddatas(datas);
        springView.onFinishFreshAndLoadDelay();

    }
}
