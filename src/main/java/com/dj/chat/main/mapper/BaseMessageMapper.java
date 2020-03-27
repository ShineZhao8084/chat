package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMessageMapper {

    int deleteByPrimaryKey(Long baseMessageId);

    int insert(BaseMessage record);

    int insertSelective(BaseMessage record);

    BaseMessage selectByPrimaryKey(Long baseMessageId);

    int updateByPrimaryKeySelective(BaseMessage record);

    int updateByPrimaryKey(BaseMessage record);

}