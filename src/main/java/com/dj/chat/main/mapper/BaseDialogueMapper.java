package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseDialogue;
import com.dj.chat.main.bean.BaseDialogueExtend;
import com.dj.chat.main.bean.BaseMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseDialogueMapper {

    int deleteByPrimaryKey(Long dialogueId);

    int insert(BaseDialogue record);

    int insertSelective(BaseDialogue record);

    BaseDialogue selectByPrimaryKey(Long dialogueId);

    int updateByPrimaryKeySelective(BaseDialogue record);

    int updateByPrimaryKey(BaseDialogue record);

    List<BaseDialogueExtend> listAllMyDialogueByAccountId(Long accountId);

    List<BaseDialogueExtend> listMyDialogue(@Param("accountId") Long accountId, @Param("param") String param);

    void updateByFromAccountIdAndToAccountId(BaseDialogue baseDialogueSender);

    BaseDialogue selectByFromAccountIdAndToAccountId(@Param("from") Long from, @Param("to") Long to);

}