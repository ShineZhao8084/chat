package com.dj.chat.main.service;

import com.dj.chat.main.bean.BaseFriendWithAccount;

import java.util.List;

public interface FriendService {

    List<BaseFriendWithAccount> listAllMyFriends(Long accountId);
}
