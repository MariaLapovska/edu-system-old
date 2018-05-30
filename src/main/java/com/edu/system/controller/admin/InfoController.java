package com.edu.system.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.controller.AccessRoles;
import com.edu.system.controller.Roles;
import com.edu.system.service.InfoService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Info;

@Controller
@RequestMapping("admin/info")
@AccessRoles(Roles.ADMIN)
public class InfoController {

    private final InfoService infoService;
    private final ArticleController articlePage;

    @Autowired
    public InfoController(InfoService infoService, ArticleController articlePage) {
        this.infoService = infoService;
        this.articlePage = articlePage;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        model.addAttribute("info", infoService.get(id));
        return "admin_info";
    }


    @PostMapping("{id}")
    public String delete(@PathVariable("id") Long id, Model model) throws ServiceException {
        Info info = infoService.get(id);
        infoService.delete(id);
        return articlePage.home(info.getArticle().getId(), model);
    }

}
