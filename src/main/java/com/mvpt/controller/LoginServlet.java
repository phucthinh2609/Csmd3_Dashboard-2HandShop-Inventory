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
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        loginUser(req, resp);

    }

    private void doSession(HttpServletRequest req, HttpServletResponse resp, UserDTO userDTO) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("username", userDTO.getEmail());

        resp.sendRedirect("/cp");
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/login/login.jsp");
        String username = "";
        String password = "";

        try {
            username = req.getParameter("username");
            password = req.getParameter("password");
            UserDTO userDTO = userService.adminLogin(username, password);

            if (userDTO != null) {
                doSession(req, resp, userDTO);
            } else {
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
}
