package com.dj.chat.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login() {

        return "";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register() {

        return "";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout() {

        return "";
    }

    @RequestMapping(path = "/forgot-password", method = RequestMethod.POST)
    public String forgotPassword() {

        return "";
    }

}
