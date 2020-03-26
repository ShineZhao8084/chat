package com.dj.chat.main.service.impl;

import com.dj.chat.main.bean.BaseFriendWithAccount;
import com.dj.chat.main.mapper.BaseFriendMappper;
import com.dj.chat.main.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private BaseFriendMappper baseFriendMappper;

    @Override
    public List<BaseFriendWithAccount> listAllMyFriends(Long accountId) {
        return baseFriendMappper.listAllMyFriends(accountId);
    }

    @Override
    public List<BaseFriendWithAccount> listMyFriend(Long accountId, String param) {
        return baseFriendMappper.listMyFriend(accountId, param);
    }

}
