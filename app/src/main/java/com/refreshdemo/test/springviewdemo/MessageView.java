package com.refreshdemo.test.springviewdemo;


import java.util.List;

public interface MessageView {

    void getdatas(List<MessageBean> datas, Boolean isfirst);

    void getmoredatas(List<MessageBean> datas);
}
