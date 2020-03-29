package com.dj.chat.main.service.impl;

import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueExtend;
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
    public List<BaseDialogueExtend> listAllMyDialogueByAccountId(Long accountId) {
        return baseDialogueMapper.listAllMyDialogueByAccountId(accountId);
    }

    @Override
    public List<BaseDialogueExtend> listMyDialogue(Long accountId, String param) {
        return baseDialogueMapper.listMyDialogue(accountId, param);
    }

}
