package com.dj.chat.main.service.impl;

import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueWithFriendAndAccount;
import com.dj.chat.main.mapper.BaseDialogueMapper;
import com.dj.chat.main.service.DialogueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DialogueServiceImpl implements DialogueService {

    @Resource
    private BaseDialogueMapper baseDialogueMapper;

    @Override
    public List<BaseDialogueWithFriendAndAccount> listAllMyDialogueByAccountId(Long accountId) {
        return baseDialogueMapper.listAllMyDialogueByAccountId(accountId);
    }

}
