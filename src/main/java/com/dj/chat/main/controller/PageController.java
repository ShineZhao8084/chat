package com.dj.chat.main.controller;

import com.dj.chat.core.security.bean.SelfUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView indexPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        Map<String, Object> model = new HashMap<String, Object>();
        SelfUserDetails userDetails = (SelfUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("accountId", userDetails.getAccountId());
        model.put("loginName", userDetails.getUsername());
        model.put("email", userDetails.getEmail());
        return new ModelAndView("index", model);
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {

        return new ModelAndView("login");
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {

        return new ModelAndView("register");
    }

    @RequestMapping(path = "/forgot-password", method = RequestMethod.GET)
    public ModelAndView forgotPasswordPage() {

        return new ModelAndView("forgot-password");
    }

}
