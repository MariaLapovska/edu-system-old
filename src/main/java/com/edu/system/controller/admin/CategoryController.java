package com.edu.system.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.controller.AccessRoles;
import com.edu.system.controller.Roles;
import com.edu.system.service.ArticleService;
import com.edu.system.service.CategoryService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Article;
import com.edu.system.vo.types.TestType;

@Controller
@RequestMapping("admin/category")
@AccessRoles(Roles.ADMIN)
public class CategoryController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        List<Article> articles = articleService.getArticlesByCategory(id);
        model.addAttribute("articles", articles);
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin_category";
    }


    @PostMapping("{id}")
    public String create(@PathVariable("id") Long id, Model model, @RequestParam("name") String name, @RequestParam("body") String body) throws ServiceException {
        articleService.create(name, body, id);
        return home(id, model);
    }

}
