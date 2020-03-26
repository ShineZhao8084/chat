package com.dj.chat.main.service;

import com.alibaba.fastjson.JSONObject;
import com.dj.chat.main.bean.BaseMessage;

public interface MessageService {

    void onMessage(JSONObject jsonObject);


}
