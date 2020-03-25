package com.dj.chat.main.mapper;

import com.dj.chat.main.bean.BaseAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseAccountMapper {

    int deleteByPrimaryKey(Long accountId);

    int insert(BaseAccount record);

    int insertSelective(BaseAccount record);

    BaseAccount selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(BaseAccount record);

    int updateByPrimaryKeyWithBLOBs(BaseAccount record);

    int updateByPrimaryKey(BaseAccount record);

    BaseAccount loadUserByUsername(String loginName);
}