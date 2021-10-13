package com.bjsxt.web.listener;

import com.bjsxt.commons.Constants;
import com.bjsxt.pojo.Users;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 解决HttpSession被反复销毁的问题
 */
@WebListener
public class HttpSessionLifeCycleListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 获取ServletContext对象，然后将所对应的HttpSession删除掉
        HttpSession httpSession = se.getSession();
        ServletContext servletContext = httpSession.getServletContext();

        // 拿到session后需要把session所缓存的user对象取出来，用userid删除servlet context 中缓存的session
        Users users = (Users) httpSession.getAttribute(Constants.USER_SESSION_KEY);
        servletContext.removeAttribute(users.getUserid() + "");
    }
}
