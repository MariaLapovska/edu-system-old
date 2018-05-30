package com.edu.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.repository.AbstractCadrRepository;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.AbstractCadr;
import com.edu.system.vo.Test;
import com.edu.system.vo.User;

@Controller
@RequestMapping("test")
@AccessRoles(Roles.USER)
public class UserTestController {

    private final TestService testService;
    private final AbstractCadrRepository abstractCadrRepository;

    @Autowired
    public UserTestController(TestService testService, AbstractCadrRepository abstractCadrRepository) {
        this.testService = testService;
        this.abstractCadrRepository = abstractCadrRepository;
    }

    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model) throws ServiceException {
        Test test = testService.get(id);
        model.addAttribute("test", test);
        List<AbstractCadr> cadrs = abstractCadrRepository.findByArticleOrderById(test.getArticle());
        AbstractCadr last = null;
        for (AbstractCadr cadr : cadrs) {
            if (cadr.getClassName().equals("Info")) {
                last = cadr;
            }
            if (cadr.getNextInfo() != null) {
                continue;
            }
            if (cadr.getNextTest() != null && cadr.getNextTest().getId().equals(id)) {
                break;
            }
        }
        model.addAttribute("lastInfoId", last.getId());
        return "user_test";
    }


    @PostMapping("{id}")
    public String create(@PathVariable("id") Long id, Model model, @RequestParam("payload") String payload, HttpServletRequest request) throws ServiceException {
        User user = (User) request.getSession().getAttribute("user");
        ValidatorResult validate = testService.validate(id, payload, user);
        model.addAttribute("result", validate.getSuccess());
        model.addAttribute("resultPresent", true);
        model.addAttribute("attempt", validate.getAttempts());
        return home(id, model);
    }

}
