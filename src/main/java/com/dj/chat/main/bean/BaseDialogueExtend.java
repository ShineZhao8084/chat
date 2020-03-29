package com.dj.chat.main.bean;

public class BaseDialogueExtend extends BaseDialogue {

    private String loginName;

    private String friendName;

    private Integer unReadMessageCount;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Integer getUnReadMessageCount() {
        return unReadMessageCount;
    }

    public void setUnReadMessageCount(Integer unReadMessageCount) {
        this.unReadMessageCount = unReadMessageCount;
    }
}
