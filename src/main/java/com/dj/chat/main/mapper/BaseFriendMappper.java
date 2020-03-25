package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseFriend;
import com.dj.chat.main.bean.BaseFriendWithAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseFriendMappper {

    int deleteByPrimaryKey(Long friendId);

    int insert(BaseFriend record);

    int insertSelective(BaseFriend record);

    BaseFriend selectByPrimaryKey(Long friendId);

    int updateByPrimaryKeySelective(BaseFriend record);

    int updateByPrimaryKey(BaseFriend record);

    List<BaseFriendWithAccount> listAllMyFriends(Long accountId);

}