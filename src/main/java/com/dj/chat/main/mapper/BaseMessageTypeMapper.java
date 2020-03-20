package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseMessageType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMessageTypeMapper {

    int deleteByPrimaryKey(Integer messsgeTypeId);

    int insert(BaseMessageType record);

    int insertSelective(BaseMessageType record);

    BaseMessageType selectByPrimaryKey(Integer messsgeTypeId);

    int updateByPrimaryKeySelective(BaseMessageType record);

    int updateByPrimaryKey(BaseMessageType record);

}