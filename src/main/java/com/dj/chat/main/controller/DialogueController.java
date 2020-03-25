package com.dj.chat.main.controller;

import com.alibaba.fastjson.JSON;
import com.dj.chat.main.bean.AjaxResponseBody;
import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueWithFriendAndAccount;
import com.dj.chat.main.service.DialogueService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DialogueController {

    @Resource
    private DialogueService dialogueService;

    @RequestMapping(path = "/dialogue/{accountId}", method = RequestMethod.GET)
    public String listAllMyDialogue(@PathVariable Long accountId) {
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<BaseDialogueWithFriendAndAccount> baseDialogues = dialogueService.listAllMyDialogueByAccountId(accountId);
        ajaxResponseBody.setStatus("200");
        ajaxResponseBody.setResult(baseDialogues);
        return JSON.toJSONString(ajaxResponseBody);
    }

}
