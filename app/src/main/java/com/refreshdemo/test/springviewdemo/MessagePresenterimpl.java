package com.refreshdemo.test.springviewdemo;

import android.util.Log;


import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessagePresenterimpl implements MessagePresenter {


    MessageView messageView;


    String netmessage="{\n" +
            "\t\"message\": \"查询成功\",\n" +
            "\t\"status\": 0,\n" +
            "\t\"data\": {\n" +
            "\t\t\"count\": 100,\n" +
            "\t\t\"current\": 1,\n" +
            "\t\t\"endRowNum\": 19,\n" +
            "\t\t\"-\": 20,\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\"mesId\": 12,\n" +
            "\t\t\t\"mesType\": \"活动通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"优惠活动\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 1\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}, {\n" +
            "\t\t\t\"mesId\": 13,\n" +
            "\t\t\t\"mesType\": \"系统通知\",\n" +
            "\t\t\t\"mesName\": \"通知名称\",\n" +
            "\t\t\t\"mesContent\": \"版本升级\",\n" +
            "\t\t\t\"startTime\": \"2018-08-23\",\n" +
            "\t\t\t\"endTime\": \"2018-08-24\",\n" +
            "\t\t\t\"mark\": 0\n" +
            "\t\t}],\n" +
            "\t\t\"startRowNum\": 0,\n" +
            "\t\t\"totalPage\": 1\n" +
            "\t}\n" +
            "}";

    public MessagePresenterimpl(MessageView messageView) {
        this.messageView = messageView;
    }


    /**
     *
     *  获取消息列表的数据
     *
     * */

    @Override
    public void getData(final int page, final Boolean isfirst) {

        Log.e("success: ",netmessage);
        try {
            JSONObject jsonObject = new JSONObject(netmessage);
            String data = jsonObject.getString("data");
            JSONObject jsonObject1 = new JSONObject(data);
            String list = jsonObject1.getString("list");
            ArrayList<MessageBean> messagedata= (ArrayList<MessageBean>) GsonUtil.parseJsonToList(list,
                    new TypeToken<List<MessageBean>>(){}.getType());

            //  todo 确定第一页是1，而不是0
            if (page==1) {
                messageView.getdatas(messagedata, isfirst);
            }else {
                messageView.getmoredatas(messagedata);
            }



        } catch (Exception e1) {

        }

    }




}
