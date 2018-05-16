package com.edu.system.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.vo.Test;

@Controller
@RequestMapping("admin/test")
public class TestPage {

    private final TestService testService;
    private final ArticlePage articlePage;

    @Autowired
    public TestPage(TestService testService, ArticlePage articlePage) {
        this.testService = testService;
        this.articlePage = articlePage;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("test", testService.get(id));
        return "admin_test";
    }


    @PostMapping("{id}")
    public String create(@PathVariable("id") Long id, Model model) throws ServiceException {
        Test test = testService.get(id);
        testService.delete(id);
        return articlePage.home(test.getArticle().getId(), model);
    }

}
