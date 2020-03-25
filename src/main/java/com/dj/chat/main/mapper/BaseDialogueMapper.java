package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueWithFriendAndAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseDialogueMapper {
    int deleteByPrimaryKey(Long dialogueId);

    int insert(BaseDialogue record);

    int insertSelective(BaseDialogue record);

    BaseDialogue selectByPrimaryKey(Long dialogueId);

    int updateByPrimaryKeySelective(BaseDialogue record);

    int updateByPrimaryKey(BaseDialogue record);

    List<BaseDialogueWithFriendAndAccount> listAllMyDialogueByAccountId(Long accountId);

}