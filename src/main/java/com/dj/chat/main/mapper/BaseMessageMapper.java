package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseMessage;

public interface BaseMessageMapper {
    int deleteByPrimaryKey(Long baseMessageId);

    int insert(BaseMessage record);

    int insertSelective(BaseMessage record);

    BaseMessage selectByPrimaryKey(Long baseMessageId);

    int updateByPrimaryKeySelective(BaseMessage record);

    int updateByPrimaryKey(BaseMessage record);
}