package com.mvpt.controller;

import com.mvpt.dto.UserDTO;
import com.mvpt.dto.UserMapper;
import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserStatus;
import com.mvpt.services.IUserService;
import com.mvpt.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CPUserServlet", urlPatterns = "/users")

public class CPUserServlet extends HttpServlet {
    IUserService userService = UserService.getInstance();
    UserMapper userMapper = UserMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if (action == null)
            action = "";

        switch (action) {
            case "create":
                showCreateUserForm(req, resp);
                break;
            case "edit":
                showEditUserForm(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if (action == null)
            action = "";

        switch (action) {
            case "create":
                addUser(req, resp);
                break;
            case "edit":
                editUser(req, resp);
                break;
        }
    }


    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/create.jsp");

        try {
            String fullName = req.getParameter("fullName").trim();
            String mobile = req.getParameter("mobile").trim();
            String email = req.getParameter("email").trim();
            String password = req.getParameter("password").trim();
            String address = req.getParameter("address").trim();
            Role role = Role.parseRole(req.getParameter("role"));
            UserStatus status = UserStatus.parseUserStatus("ACTIVE");

            user = new User(fullName, mobile, email, password, address, role, status);

            //Check validate front-end
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
            }
            // Check email and mobile exists
            boolean isExistsByEmail = userService.existsByEmail(email);
            boolean isexistsByMobile = userService.existsByMobile(mobile);

            if (isExistsByEmail) {
                errors.add("Email exists");
            }
            if (isexistsByMobile) {
                errors.add("Mobile exists");
            }

            //Do add result
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("user", user);

                req.setAttribute("success", false);
                dispatcher.forward(req, resp);

            } else {
                UserDTO userDTO = userMapper.toDTO(user);
                userService.add(userDTO);

                req.setAttribute("success", true);

                dispatcher.forward(req, resp);
            }
        } catch (IllegalArgumentException ex) {
            errors.add("Role invalid");
            ex.printStackTrace();

            req.setAttribute("success", false);
            req.setAttribute("errors", errors);

            dispatcher.forward(req, resp);
        }
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = null;
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/edit.jsp");

        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String fullName = req.getParameter("fullName").trim();
            String mobile = req.getParameter("mobile").trim();
            String email = req.getParameter("email").trim();
            String password = req.getParameter("password").trim();
            String address = req.getParameter("address").trim();
            Role role = Role.parseRole(req.getParameter("role"));
            UserStatus status = UserStatus.parseUserStatus(req.getParameter("status"));

            user = new User(id, fullName, mobile, email, password, address, role, status);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<User> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
            }
            // Check email and mobile exists
            boolean isExistsByEmail = userService.existsByEmail(email);
            boolean isexistsByMobile = userService.existsByMobile(mobile);
            String originalEmail = userService.getOriginalEmail(id);
            String originalMobile = userService.getOriginalMobile(id);


            if (isExistsByEmail) {
                if(!originalEmail.equals(email))
                    errors.add("Email exists");
            }
            if (isexistsByMobile) {
                if(!originalMobile.equals(mobile))
                    errors.add("Mobile exists");
            }

            //Do add result
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("user", user);
                req.setAttribute("success", false);
                dispatcher.forward(req, resp);

            } else {
                UserDTO userDTO = userMapper.toDTO(user);
                userService.update(userDTO);

                user = userService.findById(id);
                req.setAttribute("user", user);

                req.setAttribute("success", true);
                dispatcher.forward(req, resp);
            }
        } catch (IllegalArgumentException ex) {
            errors.add("Role or Status invalid");
            ex.printStackTrace();

            user = userService.findById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user", user);

            req.setAttribute("success", false);
            req.setAttribute("errors", errors);

            dispatcher.forward(req, resp);
        }


    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cp/user/list.jsp");
        List<UserDTO> users = userService.findAll();
        req.setAttribute("users", users);
        dispatcher.forward(req, resp);
    }

    private void showCreateUserForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cp/user/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditUserForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cp/user/edit.jsp");
        User user = userService.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("user", user);
        dispatcher.forward(req, resp);
    }


}
