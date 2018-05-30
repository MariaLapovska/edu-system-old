package com.edu.system.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.controller.AccessRoles;
import com.edu.system.controller.Roles;
import com.edu.system.service.CategoryService;
import com.edu.system.vo.Category;

@Controller
@RequestMapping("admin")
@AccessRoles(Roles.ADMIN)
public class HomeController {

    private final CategoryService categoryService;

    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String home(Model model){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "admin_home";
    }


    @PostMapping
    public String create(Model model, @RequestParam("name") String name){
        categoryService.create(name);
        return home(model);
    }
}
