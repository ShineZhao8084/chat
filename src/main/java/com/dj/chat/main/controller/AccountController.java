package com.dj.chat.main.controller;

import com.alibaba.fastjson.JSON;
import com.dj.chat.main.bean.AjaxResponseBody;
import com.dj.chat.main.bean.BaseAccount;
import com.dj.chat.main.service.AccountService;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(path = "/account/{param}", method = RequestMethod.GET)
    public String findAccountByAccountNameOrEmail(@PathVariable String param) {
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<BaseAccount> baseAccountList = accountService.findAAccountByAccountNameOrEmail(param);
        return JSON.toJSONString(ajaxResponseBody);
    }

}
