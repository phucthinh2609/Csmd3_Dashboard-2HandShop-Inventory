package com.mvpt.controller;

import com.mvpt.dto.ProductDTO;
import com.mvpt.dto.ProductMapper;
import com.mvpt.dto.UserDTO;
import com.mvpt.model.Product;
import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserStatus;
import com.mvpt.services.IProductService;
import com.mvpt.services.ProductService;
import com.mvpt.utils.UploadImage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Port;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet (name = "CPProductServlet", urlPatterns = "/products")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CPProductServlet extends HttpServlet {
    IProductService productService = ProductService.getInstance();
    ProductMapper productMapper = ProductMapper.getInstance();

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        if (session.getAttribute("username") == null) {
            resp.sendRedirect("/login");
        }
        else {
            String action = req.getParameter("action");

            String userName = (String) session.getAttribute("user");
            System.out.println(userName);

            if (action == null)
                action = "";

            switch (action){
                case "create":
                    showCreateProductForm(req, resp);
                    break;
                case "edit":
                    showEditProductForm(req, resp);
                default:
                    listOfProducts(req, resp);
                    break;
            }
        }
    }

    private void showEditProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/edit.jsp");
        List<String> errors = new ArrayList<>();

        try {
            List<ProductDTO> productDTOS = productService.findProductsById(Integer.parseInt(req.getParameter("id")));
            if (productDTOS.isEmpty()){
                errors.add("Id invalid");
                req.setAttribute("success", false);
                req.setAttribute("errors", errors);
                dispatcher.forward(req, resp);
            }else {
                ProductDTO productDTO = productService.findById(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("product", productDTO);
                dispatcher.forward(req, resp);
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            errors.add("Id dont match format");
            req.setAttribute("success", false);
            req.setAttribute("errors", errors);
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String imageName = "";

        if (action == null)
            action = "";

        switch (action) {
            case "create":
                imageName = UploadImage.uploadImages(req, resp);
                addProduct(req, resp, imageName);
                break;
            case "edit":
                imageName = UploadImage.uploadImages(req, resp);
                editProduct(req, resp, imageName);
                break;
        }

    }

    private void showCreateProductForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void listOfProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cp/product/list.jsp");
        String search = "";
        List<ProductDTO> products = new ArrayList<>();

        if(req.getParameter("search")!=null){
            search = req.getParameter("search");
        }

        if (!search.isEmpty()) {
            products = productService.searchUser(search);
            req.setAttribute("products", products);

        }else {
            products = productService.findAll();
            req.setAttribute("products", products);
        }
        dispatcher.forward(req, resp);
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp, String imageName) throws ServletException, IOException {
        Product product = null;
        ProductDTO productDTO = null;
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/edit.jsp");

        try {
            Integer id = Integer.parseInt(req.getParameter("id").trim());
            String title = req.getParameter("title").trim();
            String image = imageName.trim();
            String content = req.getParameter("content").trim();

            product = new Product(id, title, image, content);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
            }
            // Check email and mobile exists


            //Do add result
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("product", product);
                req.setAttribute("success", false);
                dispatcher.forward(req, resp);

            } else {
                productService.update(product);

                productDTO = productMapper.toDTO(product);
                productDTO = productService.findById(id);
                System.out.println(productDTO);
                req.setAttribute("product", productDTO);

                req.setAttribute("success", true);
                dispatcher.forward(req, resp);
            }
        } catch (IllegalArgumentException ex) {
            errors.add("Role or Status invalid");
            ex.printStackTrace();

            productDTO = productService.findById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("product", productDTO);

            req.setAttribute("success", false);
            req.setAttribute("errors", errors);

            dispatcher.forward(req, resp);

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp, String imageName) throws ServletException, IOException {
        Product product = null;
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");

        try {
            String title = req.getParameter("title").trim();
            String image = imageName.trim();
            String content = req.getParameter("content").trim();

            product = new Product(title, image, content);

            //Check validate front-end
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
            }
            // Check error

            //Do add result
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("product", product);

                req.setAttribute("success", false);
                dispatcher.forward(req, resp);

            } else {
                productService.add(product);

                req.setAttribute("success", true);

                dispatcher.forward(req, resp);
            }
        } catch (IllegalArgumentException ex) {
            errors.add("Role invalid");
            ex.printStackTrace();

            req.setAttribute("success", false);
            req.setAttribute("errors", errors);

            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
