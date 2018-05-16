package com.edu.system.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.vo.types.TestType;

@Controller
@RequestMapping("admin/article")
public class ArticlePage {

    private final ArticleService articleService;
    private final TestService testService;

    @Autowired
    public ArticlePage(ArticleService articleService, TestService testService) {
        this.articleService = articleService;
        this.testService = testService;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("article", articleService.get(id));
        model.addAttribute("tests", testService.getByArticle(id));
        return "admin_article";
    }


    @PostMapping("{id}")
    public String create(@PathVariable("id") Long id, Model model, @RequestParam("name") String name, @RequestParam("body") String body,
                         @RequestParam("condition") String condition, @RequestParam("type") TestType type) throws ServiceException {
        testService.create(name, body, condition, type, id);
        return home(id, model);
    }

}
