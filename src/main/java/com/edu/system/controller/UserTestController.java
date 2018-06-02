package com.edu.system.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
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
import com.edu.system.repository.LinkCadrRepository;
import com.edu.system.service.ServiceException;
import com.edu.system.service.TestService;
import com.edu.system.service.UserAttemptService;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.LinkCadr;
import com.edu.system.vo.Test;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;
import com.edu.system.vo.types.LinkCadrType;

@Controller
@RequestMapping("test")
@AccessRoles(Roles.USER)
public class UserTestController {

    private final TestService testService;
    private final AbstractCadrRepository abstractCadrRepository;
    private final LinkCadrRepository linkCadrRepository;
    private final UserAttemptService userAttemptService;

    @Autowired
    public UserTestController(TestService testService, AbstractCadrRepository abstractCadrRepository, LinkCadrRepository linkCadrRepository, UserAttemptService userAttemptService) {
        this.testService = testService;
        this.abstractCadrRepository = abstractCadrRepository;
        this.linkCadrRepository = linkCadrRepository;
        this.userAttemptService = userAttemptService;
    }

    @GetMapping("{id}")
    public String home(@PathVariable("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response, boolean toInfo) throws ServiceException, IOException {
        Test test = testService.get(id);
        User user = (User) request.getSession().getAttribute("user");
        UserAttempt userAttempt = userAttemptService.getUserAttempt(user, test);
        model.addAttribute("test", test);
        LinkCadr right = linkCadrRepository.findByFromCadrAndType(test, LinkCadrType.STRAIGHT);
        LinkCadr reverse = linkCadrRepository.findByFromCadrAndType(test, LinkCadrType.REVERSE);
        model.addAttribute("lastInfoId", reverse.getToCadr().getId());
        if (right != null) {
            model.addAttribute("nextId", right.getToCadr());
            if (userAttempt.getCount() >= 3) {
                if (!toInfo) {
                    response.sendRedirect("/test/" + right.getToCadr().getId());
                }
            }
        } else {
            if (userAttempt.getCount() >= 3) {
                response.sendRedirect("/result");
            }
        }
        return "user_test";
    }


    @PostMapping("{id}")
    public String create(@PathVariable("id") Long id, Model model, @RequestParam("payload") String payload, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ValidatorResult validate = testService.validate(id, payload, user);
        model.addAttribute("result", validate.getSuccess());
        model.addAttribute("resultPresent", true);
        model.addAttribute("attempt", validate.getAttempts());
        return home(id, model, request, response, true);
    }

}
