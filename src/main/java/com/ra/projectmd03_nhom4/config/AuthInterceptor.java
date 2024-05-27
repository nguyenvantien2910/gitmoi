package com.ra.projectmd03_nhom4.config;
import com.ra.projectmd03_nhom4.constant.RoleName;
import com.ra.projectmd03_nhom4.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User users = (User) request.getSession().getAttribute("user");
        if(users != null) {
            if(users.getRoles().stream().anyMatch(roles -> roles.getRoleName().equals(RoleName.ROLE_ADMIN))) {
                return true;
            } else {
                response.sendRedirect("/403");
                return false;
            }
        } else {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
    }
}
