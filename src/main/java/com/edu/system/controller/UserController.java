package com.edu.system.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.system.controller.admin.HomeController;
import com.edu.system.service.ServiceException;
import com.edu.system.service.UserService;
import com.edu.system.vo.User;

@Controller
@RequestMapping("login")
@AccessRoles(Roles.ALL)
public class UserController {

    private final UserService userService;
    private final HomeController homePage;

    @Autowired
    public UserController(UserService userService, HomeController homePage) {
        this.userService = userService;
        this.homePage = homePage;
    }

    @GetMapping
    public String login(Model model) {
        return "login_page";
    }

    @PostMapping
    public String auth(Model model, @RequestParam("login") String login, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        Optional<User> optionalUser = userService.authenticate(login, password);
        User user;
        if (!optionalUser.isPresent()) {
            user = new User();
            user.setLogin(login);
            user.setPass(password);
            user.setRoles(Roles.ADMIN);
            user = userService.register(user);
        } else {
            user = optionalUser.get();
        }
        request.getSession().setAttribute("user", user);
        if (user.getRoles() == Roles.ADMIN) {
            response.sendRedirect("/admin");
        }
        return "login_page";
    }
}