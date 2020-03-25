package com.dj.chat.main.service;

import com.dj.chat.main.bean.BaseAccount;

import java.util.List;

public interface AccountService {
    List<BaseAccount> findAAccountByAccountNameOrEmail(String param);
}
