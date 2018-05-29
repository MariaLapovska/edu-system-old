package com.edu.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.service.ArticleService;
import com.edu.system.service.InfoService;
import com.edu.system.service.ServiceException;

@Controller
@RequestMapping("article")
@AccessRoles(Roles.USER)
public class UserArticlePage {

    private final ArticleService articleService;
    private final InfoService infoService;

    @Autowired
    public UserArticlePage(ArticleService articleService, InfoService infoService) {
        this.articleService = articleService;
        this.infoService = infoService;
    }

    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("article", articleService.get(id));
        model.addAttribute("info", infoService.findFirst(id));
        return "user_article";
    }
}
