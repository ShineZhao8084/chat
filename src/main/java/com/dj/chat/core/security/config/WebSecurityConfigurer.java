package com.dj.chat.core.security.config;

import com.dj.chat.core.security.handler.AjaxAuthenticationEntryPoint;
import com.dj.chat.core.security.handler.AjaxAuthenticationFailureHandler;
import com.dj.chat.core.security.handler.AjaxAuthenticationSuccessHandler;
import com.dj.chat.core.security.handler.AjaxLogoutSuccessHandler;
import com.dj.chat.core.security.service.SelfUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Resource
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）
    @Resource
    AjaxAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）
    @Resource
    AjaxLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
    @Resource
    SelfUserDetailsService userDetailsService; // 自定义user

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF
        http.csrf().disable()
                // 未经过认证的用户访问受保护的资源
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/imserver/**", "/css/**", "/error/**", "/fonts/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/login", "/register", "/forgot-password").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()  //开启登录
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                // 已经认证的用户访问自己没有权限的资源处理
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                // cookie 失效时间,默认有效期为14天
                .and().rememberMe().rememberMeParameter("remember-me");
    }

}