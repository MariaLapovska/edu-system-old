package com.edu.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.service.InfoService;
import com.edu.system.service.ServiceException;

@Controller
@RequestMapping("info")
@AccessRoles(Roles.USER)
public class UserInfoPage {

    private final InfoService infoService;

    @Autowired
    public UserInfoPage(InfoService infoService) {
        this.infoService = infoService;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("info", infoService.get(id));
        return "user_info";
    }

}
