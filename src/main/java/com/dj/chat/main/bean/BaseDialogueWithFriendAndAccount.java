package com.dj.chat.main.bean;

import java.security.PrivilegedAction;

public class BaseDialogueWithFriendAndAccount extends BaseDialogue {

    private BaseAccount baseAccount;

    private BaseFriend baseFriend;

    public BaseAccount getBaseAccount() {
        return baseAccount;
    }

    public void setBaseAccount(BaseAccount baseAccount) {
        this.baseAccount = baseAccount;
    }

    public BaseFriend getBaseFriend() {
        return baseFriend;
    }

    public void setBaseFriend(BaseFriend baseFriend) {
        this.baseFriend = baseFriend;
    }
}
