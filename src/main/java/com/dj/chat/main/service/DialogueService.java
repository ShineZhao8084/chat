package com.dj.chat.main.service;

import com.dj.chat.main.bean.BaseDialogueExtend;

import java.util.List;

public interface DialogueService {

    List<BaseDialogueExtend> listAllMyDialogueByAccountId(Long accountId);

    List<BaseDialogueExtend> listMyDialogue(Long accountId, String param);


}
