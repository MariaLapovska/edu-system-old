package com.edu.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.repository.LinkCadrRepository;
import com.edu.system.service.InfoService;
import com.edu.system.service.ServiceException;
import com.edu.system.vo.Info;
import com.edu.system.vo.LinkCadr;
import com.edu.system.vo.types.LinkCadrType;

@Controller
@RequestMapping("info")
@AccessRoles(Roles.USER)
public class UserInfoController {

    private final InfoService infoService;
    private final LinkCadrRepository linkCadrRepository;

    @Autowired
    public UserInfoController(InfoService infoService, LinkCadrRepository linkCadrRepository) {
        this.infoService = infoService;
        this.linkCadrRepository = linkCadrRepository;
    }


    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        Info info = infoService.get(id);
        model.addAttribute("info", info);
        LinkCadr right = linkCadrRepository.findByFromCadrAndType(info, LinkCadrType.STRAIGHT);
        if (right != null) {
            model.addAttribute("nextId", right.getToCadr());
        }
        return "user_info";
    }

}
