package com.edu.system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.edu.system.controller.AccessRoles;
import com.edu.system.controller.Roles;
import com.edu.system.vo.User;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.getBeanType().equals(BasicErrorController.class)) {
                if (request.getSession().getAttribute("user") != null) {
                    return true;
                } else {
                    response.sendRedirect("/login");
                }
            }
            if (method.hasMethodAnnotation(AccessRoles.class)) {
                return prepareResponse(request, response, method.getMethodAnnotation(AccessRoles.class).value());
            } else if (method.getBeanType().isAnnotationPresent(AccessRoles.class)) {
                return prepareResponse(request, response, method.getBeanType().getAnnotation(AccessRoles.class).value());
            }
        }
        if (handler.getClass().isAnnotationPresent(AccessRoles.class)) {
            return prepareResponse(request, response, handler.getClass().getAnnotation(AccessRoles.class).value());
        }
        return false;
    }

    private boolean prepareResponse(HttpServletRequest request, HttpServletResponse response, Roles roles) throws IOException {
        Object nullUser = request.getSession().getAttribute("user");
        if (nullUser == null) {
            boolean condition = roles == Roles.ALL;
            if (!condition) {
                response.sendRedirect("/login");
            }
            return condition;
        }
        User user = (User) nullUser;
        boolean condition = roles == Roles.ALL || roles.getWeight() <= user.getRoles().getWeight();
        if (!condition) {
            response.sendRedirect("/login");
        }
        return condition;
    }
}
