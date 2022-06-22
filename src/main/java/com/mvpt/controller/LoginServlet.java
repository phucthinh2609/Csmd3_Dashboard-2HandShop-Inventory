package com.mvpt.controller;

import com.mvpt.dto.UserDTO;
import com.mvpt.services.IUserService;
import com.mvpt.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    IUserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/login/login.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/login/login.jsp");
        String username = "";
        String password = "";
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            username = req.getParameter("username");
            password = req.getParameter("password");
            UserDTO userDTO = userService.adminLogin(username, password);

            if (userDTO != null) {
                HttpSession session = req.getSession();
                session.setAttribute("username", userDTO.getEmail());

                resp.sendRedirect("/cp");
            }
            else {
                errors.add("User or password incorrect");
                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.setAttribute("success", false);
                req.setAttribute("errors", errors);
                dispatcher.forward(req, resp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            errors.add("Email or password incorrect");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("success", false);
            req.setAttribute("errors", errors);
            dispatcher.forward(req, resp);
        }
    }

    protected void CreatCookie(HttpServletRequest req, HttpServletResponse resp, String _username, String _password) {

        Cookie username = new Cookie("username", _username);
        Cookie password = new Cookie("password", _password);

        username.setMaxAge(60 * 60 * 24);
        password.setMaxAge(60 * 60 * 24);

        resp.addCookie(username);
        resp.addCookie(password);


    }
}
