package com.edu.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;

@Controller
@RequestMapping("article")
public class UserArticlePage {

    private final ArticleService articleService;
    private final TestService testService;

    @Autowired
    public UserArticlePage(ArticleService articleService, TestService testService) {
        this.articleService = articleService;
        this.testService = testService;
    }

    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("article", articleService.get(id));
        return "user_article";
    }
}
