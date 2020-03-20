package com.dj.chat.main.service.impl;

import com.dj.chat.main.bean.BaseAccount;
import com.dj.chat.main.mapper.BaseAccountMapper;
import com.dj.chat.main.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private BaseAccountMapper baseAccountMapper;

    @Override
    public BaseAccount loadUserByUsername(String email) {
        return baseAccountMapper.loadUserByUsername(email);
    }
}
