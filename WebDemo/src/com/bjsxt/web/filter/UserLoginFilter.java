package com.bjsxt.web.filter;

import com.bjsxt.commons.Constants;
import com.bjsxt.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断当前客户端浏览器是否登录的Filter
 */

@WebFilter(urlPatterns = {"*.do", "*.jsp"})
public class UserLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        // 判断当前的请求是否为login.jsp或者login.do，如果请求的是用户登录的资源那么需要放行。
        if (uri.indexOf("login.jsp") != -1 || uri.indexOf("login.do") != -1 || uri.indexOf("validateCode.do") != -1) {
            // 访问的是登录页面
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = request.getSession();
            Users users = (Users) session.getAttribute(Constants.USER_SESSION_KEY);
            if (users != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.setAttribute(Constants.REQUEST_MSG, "不登录不好使");
                request.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
