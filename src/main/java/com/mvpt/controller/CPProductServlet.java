//package com.mvpt.controller;
//
//import com.mvpt.utils.UploadImage;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@WebServlet (name = "CPProductServlet", urlPatterns = "/products")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//        maxFileSize = 1024 * 1024 * 50, // 50MB
//        maxRequestSize = 1024 * 1024 * 50) // 50MB
//public class CPProductServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html/charset=UTF-8");
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//
//        String action = req.getParameter("action");
//
//        if (action == null)
//            action = "";
//
//        switch (action){
//            case "create":
//                showCreateProductForm(req, resp);
//                break;
//            default:
//                showProductsListForm(req, resp);
//                break;
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html/charset=UTF-8");
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//
//        String action = req.getParameter("action");
//        String imageName = "";
//
//        if (action == null)
//            action = "";
//
//        switch (action) {
//            case "create":
//                imageName = UploadImage.uploadImages(req, resp);
//                addProduct(req, resp, imageName);
//                break;
//            case "edit":
//                imageName = UploadImage.uploadImages(req, resp);
//                editProduct(req, resp, imageName);
//                break;
//        }
//
//    }
//
//    private void showCreateProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    private void showProductsListForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/list.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    private void editProduct(HttpServletRequest req, HttpServletResponse resp, String imageName) {
//
//    }
//
//    private void addProduct(HttpServletRequest req, HttpServletResponse resp, String imageName) throws ServletException, IOException {
//        Product product = null;
//        List<String> errors = new ArrayList<>();
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
//
//        try {
//            String title = req.getParameter("title").trim();
//            String image = req.getParameter("image").trim();
//            String content = req.getParameter("content").trim();
//
//            product = new Product(title, image, content);
//
//            //Check validate front-end
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//            Validator validator = validatorFactory.getValidator();
//            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
//            if (!constraintViolations.isEmpty()) {
//                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
//                    errors.add(constraintViolation.getMessage());
//                }
//            }
//            // Check error
//
//            //Do add result
//            if (!errors.isEmpty()) {
//                req.setAttribute("errors", errors);
//                req.setAttribute("user", product);
//
//                req.setAttribute("success", false);
//                dispatcher.forward(req, resp);
//
//            } else {
//                userService.add(product);
//
//                req.setAttribute("success", true);
//
//                dispatcher.forward(req, resp);
//            }
//        } catch (IllegalArgumentException ex) {
//            errors.add("Role invalid");
//            ex.printStackTrace();
//
//            req.setAttribute("success", false);
//            req.setAttribute("errors", errors);
//
//            dispatcher.forward(req, resp);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
