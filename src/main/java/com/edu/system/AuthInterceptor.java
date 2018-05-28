package com.edu.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.edu.system.controller.AccessRoles;
import com.edu.system.controller.Roles;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.hasMethodAnnotation(AccessRoles.class)) {
                return method.getMethodAnnotation(AccessRoles.class).value() == Roles.ALL ||
                        method.getMethodAnnotation(AccessRoles.class).value().getWeight() <= Roles.ADMIN.getWeight();
            } else if(method.getBeanType().isAnnotationPresent(AccessRoles.class)){
                return method.getBeanType().getAnnotation(AccessRoles.class).value() == Roles.ALL ||
                        method.getBeanType().getAnnotation(AccessRoles.class).value().getWeight() <= Roles.ADMIN.getWeight();
            }
        }
        if (handler.getClass().isAnnotationPresent(AccessRoles.class)) {
            return handler.getClass().getAnnotation(AccessRoles.class).value() == Roles.ALL ||
                    handler.getClass().getAnnotation(AccessRoles.class).value().getWeight() <= Roles.ADMIN.getWeight();
        }
        return false;
    }
}
