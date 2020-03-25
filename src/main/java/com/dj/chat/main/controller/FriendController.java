package com.dj.chat.main.controller;

import com.alibaba.fastjson.JSON;
import com.dj.chat.main.bean.AjaxResponseBody;
import com.dj.chat.main.bean.BaseFriendWithAccount;
import com.dj.chat.main.service.FriendService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FriendController {

    @Resource
    private FriendService friendService;

    @RequestMapping(path = "/friend/{accountId}", method = RequestMethod.POST)
    public String listAllMyFriends(@PathVariable Long accountId) {
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<BaseFriendWithAccount> friendList = friendService.listAllMyFriends(accountId);
        ajaxResponseBody.setStatus("200");
        ajaxResponseBody.setResult(friendList);
        return JSON.toJSONString(ajaxResponseBody);
    }

}
