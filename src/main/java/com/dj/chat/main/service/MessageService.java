package com.dj.chat.main.service;

import com.dj.chat.core.websocket.bean.MessageReceive;
import com.dj.chat.main.bean.BaseDialogue;

import java.util.List;
import java.util.Map;

public interface MessageService {

    Map<String, List<BaseDialogue>> onMessage(MessageReceive messageReceive);


}
