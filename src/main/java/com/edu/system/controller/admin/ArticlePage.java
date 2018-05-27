package com.edu.system.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.repository.AbstractCadrRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.InfoService;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.vo.Article;
import com.edu.system.vo.types.TestType;

@Controller
@RequestMapping("admin/article")
public class ArticlePage {

    private final ArticleService articleService;
    private final TestService testService;
    private final AbstractCadrRepository abstractCadrRepository;
    private final InfoService infoService;

    @Autowired
    public ArticlePage(ArticleService articleService, TestService testService, AbstractCadrRepository abstractCadrRepository, InfoService infoService) {
        this.articleService = articleService;
        this.testService = testService;
        this.abstractCadrRepository = abstractCadrRepository;
        this.infoService = infoService;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        Article article = articleService.get(id);
        model.addAttribute("article", article);
        model.addAttribute("cadrs", abstractCadrRepository.findByArticle(article));
        return "admin_article";
    }


    @PostMapping("{id}/test")
    public void createTest(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("body") String body,
                       @RequestParam("condition") String condition, @RequestParam("type") TestType type, @RequestParam("color") String color, HttpServletResponse response) throws ServiceException, IOException {
        testService.create(name, body, condition, type, color, id);
        response.sendRedirect("/admin/article/" + id);
    }

    @PostMapping("{id}/info")
    public void createInfo(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("body") String body, @RequestParam("color") String color, HttpServletResponse response) throws ServiceException, IOException {
        infoService.create(name, body, color, id);
        response.sendRedirect("/admin/article/" + id);
    }

}
