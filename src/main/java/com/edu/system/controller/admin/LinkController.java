package com.edu.system.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.controller.AccessRoles;
import com.edu.system.controller.Roles;
import com.edu.system.repository.AbstractCadrRepository;
import com.edu.system.repository.LinkCadrRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.AbstractCadr;
import com.edu.system.vo.LinkCadr;
import com.edu.system.vo.types.LinkCadrType;

@Controller
@RequestMapping("admin/link")
@AccessRoles(Roles.ADMIN)
public class LinkController {

    private final LinkCadrRepository linkCadrRepository;
    private final AbstractCadrRepository abstractCadrRepository;
    private final ArticleService articleService;

    @Autowired
    public LinkController(LinkCadrRepository linkCadrRepository, AbstractCadrRepository abstractCadrRepository, ArticleService articleService) {
        this.linkCadrRepository = linkCadrRepository;
        this.abstractCadrRepository = abstractCadrRepository;
        this.articleService = articleService;
    }

    @PostMapping("{id}")
    public void create(@RequestParam("from") Long from, @RequestParam("to") Long to, @RequestParam("type") LinkCadrType type, @PathVariable("id") Long id, HttpServletResponse httpServletResponse) throws ServiceException, IOException {
        AbstractCadr fromCadr = abstractCadrRepository.findById(from).orElseThrow(() -> new ServiceException("cant find cadr by id:" + from));
        AbstractCadr toCadr = abstractCadrRepository.findById(to).orElseThrow(() -> new ServiceException("cant find cadr by id:" + to));
        LinkCadr linkCadr = new LinkCadr();
        linkCadr.setFromCadr(fromCadr);
        linkCadr.setToCadr(toCadr);
        linkCadr.setType(type);
        linkCadr.setArticle(articleService.get(id));
        linkCadrRepository.save(linkCadr);
        httpServletResponse.sendRedirect("/admin/article/" + id);
    }
}
