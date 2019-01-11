package com.refreshdemo.test.Smartrefreashdemo;


import java.util.List;

public interface MessageView {

    void getdatas(List<MessageBean> datas, Boolean isfirst);

    void getmoredatas(List<MessageBean> datas);

    void gettotalPage(int totalpage);

}
