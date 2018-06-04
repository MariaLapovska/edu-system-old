package com.edu.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.system.repository.UserMarkRepository;
import com.edu.system.service.ArticleService;
import com.edu.system.service.ServiceException;
import com.edu.system.service.UserAttemptService;
import com.edu.system.vo.User;
import com.edu.system.vo.UserAttempt;
import com.edu.system.vo.UserMark;

@Controller
@RequestMapping("result")
@AccessRoles(Roles.USER)
public class ResultController {

    private final UserAttemptService userAttemptService;
    private final UserMarkRepository userMarkRepository;
    private final ArticleService articleService;

    @Autowired
    public ResultController(UserAttemptService userAttemptService, UserMarkRepository userMarkRepository, ArticleService articleService) {
        this.userAttemptService = userAttemptService;
        this.userMarkRepository = userMarkRepository;
        this.articleService = articleService;
    }


    @GetMapping("{id}")
    public String result(@PathVariable("id") Long id, HttpServletRequest httpServletRequest, Model model) throws ServiceException {
        List<UserAttempt> userAttempts = userAttemptService.getUserAttempts((User) httpServletRequest.getSession().getAttribute("user"))
                .stream()
                .filter(userAttempt -> userAttempt.getTest() != null)
                .filter(userAttempt -> userAttempt.getTest().getArticle().getId().equals(id))
                .collect(Collectors.toList());
        model.addAttribute("attempts", userAttempts);
        int count = userAttempts.size();
        int tries = 0;
        for (UserAttempt attempt : userAttempts) {
            if (attempt.getResult()) {
                tries += attempt.getCount() - 1;
            } else {
                tries += 3;
            }
        }
        if (count > 0) {
            double mark = (1.0 - ((double) tries / ((double) count * 3.0))) * 100.0;
            model.addAttribute("mark", (int) mark);
            UserMark userMark = new UserMark();
            userMark.setMark((int) mark);
            userMark.setTotal(count * 3);
            userMark.setTries(tries);
            userMark.setArticle(articleService.get(id));
            userMark.setUser((User) httpServletRequest.getSession().getAttribute("user"));
            userMarkRepository.save(userMark);
        }
        return "user_mark";
    }

}
