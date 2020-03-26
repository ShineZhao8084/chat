package com.dj.chat.main.service;

import com.dj.chat.main.bean.BaseDialogueWithFriendAndAccount;

import java.util.List;

public interface DialogueService {

    List<BaseDialogueWithFriendAndAccount> listAllMyDialogueByAccountId(Long accountId);

    List<BaseDialogueWithFriendAndAccount> listMyDialogue(Long accountId, String param);


}
