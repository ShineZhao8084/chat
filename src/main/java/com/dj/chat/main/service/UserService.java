package com.dj.chat.main.service;

import com.dj.chat.main.bean.BaseAccount;

public interface UserService {
    BaseAccount loadUserByUsername(String email);
}
