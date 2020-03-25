package com.dj.chat.main.service.impl;

import com.dj.chat.main.bean.BaseAccount;
import com.dj.chat.main.mapper.BaseAccountMapper;
import com.dj.chat.main.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private BaseAccountMapper baseAccountMapper;

    @Override
    public List<BaseAccount> findAAccountByAccountNameOrEmail(String param) {
        return baseAccountMapper.findAAccountByAccountNameOrEmail(param);
    }
}
