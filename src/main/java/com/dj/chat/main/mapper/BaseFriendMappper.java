package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseFriend;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseFriendMappper {

    int deleteByPrimaryKey(Long friendId);

    int insert(BaseFriend record);

    int insertSelective(BaseFriend record);

    BaseFriend selectByPrimaryKey(Long friendId);

    int updateByPrimaryKeySelective(BaseFriend record);

    int updateByPrimaryKey(BaseFriend record);

}