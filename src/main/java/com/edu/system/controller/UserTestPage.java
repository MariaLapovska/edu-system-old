package com.edu.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.controller.admin.ArticlePage;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;

@Controller
@RequestMapping("test")
public class UserTestPage {

    private final TestService testService;

    @Autowired
    public UserTestPage(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("test", testService.get(id));
        return "user_test";
    }


    @PostMapping("{id}")
    public String create(@PathVariable("id") Long id, Model model, @RequestParam("payload") String payload) throws ServiceException {
        ValidatorResult validate = testService.validate(id, payload);
        model.addAttribute("result", validate.getSuccess());
        return home(id, model);
    }

}
