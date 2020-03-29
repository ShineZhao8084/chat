package com.dj.chat.main.bean;

import java.util.List;

public class BaseDialogueResponseWrapper {

    private BaseMessage baseMessage;

    private BaseDialogue baseDialogue;

    private List<BaseDialogueExtend> fromBaseDialogueList;

    private List<BaseDialogueExtend> toBaseDialogueList;

    public BaseMessage getBaseMessage() {
        return baseMessage;
    }

    public void setBaseMessage(BaseMessage baseMessage) {
        this.baseMessage = baseMessage;
    }

    public BaseDialogue getBaseDialogue() {
        return baseDialogue;
    }

    public void setBaseDialogue(BaseDialogue baseDialogue) {
        this.baseDialogue = baseDialogue;
    }

    public List<BaseDialogueExtend> getFromBaseDialogueList() {
        return fromBaseDialogueList;
    }

    public void setFromBaseDialogueList(List<BaseDialogueExtend> fromBaseDialogueList) {
        this.fromBaseDialogueList = fromBaseDialogueList;
    }

    public List<BaseDialogueExtend> getToBaseDialogueList() {
        return toBaseDialogueList;
    }

    public void setToBaseDialogueList(List<BaseDialogueExtend> toBaseDialogueList) {
        this.toBaseDialogueList = toBaseDialogueList;
    }
}
