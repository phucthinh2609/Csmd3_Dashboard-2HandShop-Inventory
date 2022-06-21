package com.mvpt.controller;

import com.mvpt.services.IUserService;
import com.mvpt.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/login/login.jsp");
        dispatcher.forward(req, resp);

//
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            userService.adminLogin(username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
        resp.sendRedirect("/cp");
    }
}
