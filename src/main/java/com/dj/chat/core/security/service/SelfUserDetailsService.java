package com.dj.chat.core.security.service;

import com.dj.chat.core.security.bean.SelfUserDetails;
import com.dj.chat.main.bean.BaseAccount;
import com.dj.chat.main.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        SelfUserDetails userInfo = new SelfUserDetails();
        BaseAccount user = userService.loadUserByUsername(email);
        if (user == null || user.getState() == -1) {
            throw new UsernameNotFoundException("未找到该用户！");
        }
        if (user.getState() == 2) {  //账号未启用
            userInfo.setEnabled(false);
        }
        if (user.getState() == 3) {  //账号已被锁定
            userInfo.setAccountNonLocked(false);
        }

        // TODO 账号过期

        // TODO 密码过期

        userInfo.setUsername(user.getEmail());
        userInfo.setPassword(user.getLoginPwd());


        return userInfo;
    }

}