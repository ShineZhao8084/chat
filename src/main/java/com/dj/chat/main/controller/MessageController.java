package com.dj.chat.main.controller;

import com.alibaba.fastjson.JSON;
import com.dj.chat.main.bean.AjaxResponseBody;
import com.dj.chat.main.bean.BaseMessage;
import com.dj.chat.main.service.MessageService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(path = "/message/{dialogueId}/{currentPage}", method = RequestMethod.GET)
    public String getMyMessageByDialogueId(@PathVariable Long dialogueId, @PathVariable int currentPage) {
        PageInfo<BaseMessage> messageList = messageService.getMyMessageByDialogueId(dialogueId, currentPage);
        return JSON.toJSONString(messageList);
    }


}
