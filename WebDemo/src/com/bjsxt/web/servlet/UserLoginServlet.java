package com.bjsxt.web.servlet;

import com.bjsxt.commons.Constants;
import com.bjsxt.exceptions.UserNotFoundException;
import com.bjsxt.pojo.Users;
import com.bjsxt.services.UserLoginService;
import com.bjsxt.services.impl.UserLoginServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login.do")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        String code = req.getParameter("code");
        try {
            // 建立客户端与服务端的会话状态
            // 通过session判断用户有没有登录
            HttpSession session = req.getSession();
            String codeTemp = (String) session.getAttribute(Constants.VALIDATE_CODE_KEY);
            if (codeTemp.equals(code)) {
                UserLoginService userLoginService = new UserLoginServiceImpl();
                Users users = userLoginService.userLogin(username, userpwd);
                session.setAttribute(Constants.USER_SESSION_KEY, users);
                ServletContext servletContext = this.getServletContext();
                HttpSession temp = (HttpSession) servletContext.getAttribute(users.getUserid() + "");
                if (temp != null) {
                    servletContext.removeAttribute(users.getUserid() + "");
                    temp.invalidate();
                }
                servletContext.setAttribute(users.getUserid() + "", session);

                // 使用重定向方式跳转首页
                // 重定向可以改变访问路径
                resp.sendRedirect("main.jsp");
            } else {
                req.setAttribute(Constants.REQUEST_MSG, "验证码有误，请重新输入");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }



        } catch (UserNotFoundException e) {
            req.setAttribute("msg", e.getMessage());

            // 请求转发forward，不改变客户端浏览器地址
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 如果当前用户用户登录时，系统出现错误，需要跳到一个同一的错误页面
            resp.sendRedirect("error.jsp");
        }
    }
}
