package com.mvpt.controller;

import com.mvpt.dao.IProductDAO;
import com.mvpt.dao.ProductDAO;
import com.mvpt.dto.ProductDTO;
import com.mvpt.dto.ProductMapper;
import com.mvpt.model.Product;
import com.mvpt.services.IProductService;
import com.mvpt.services.ProductService;
import com.mvpt.utils.UploadFile;
import com.mvpt.utils.UploadImage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        HttpSession session = req.getSession();
        String sessionEmail = (String) session.getAttribute("username");

        String action = req.getParameter("action");
        String imageName = "";

        if (action == null)
            action = "";

        switch (action) {
            case "create":
                addProduct(req, resp, sessionEmail);
                break;
            case "edit":
                editProduct(req, resp, sessionEmail);
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

    private void editProduct(HttpServletRequest req, HttpServletResponse resp, String emailSession) throws ServletException, IOException {
        Product product = null;
        ProductDTO productDTO = null;
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/edit.jsp");

        try {
            Integer id = Integer.parseInt(req.getParameter("id").trim());
            String title = req.getParameter("title").trim();
            String image = UploadFile.uploadImagesServer(req);
            String updatedBy = emailSession.trim();
            String content = req.getParameter("content").trim();

            productDTO = new ProductDTO(id, title, image, updatedBy, content);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<ProductDTO>> constraintViolations = validator.validate(productDTO);

            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<ProductDTO> constraintViolation : constraintViolations) {
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
                productService.update(productDTO);

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

    private void addProduct(HttpServletRequest req, HttpServletResponse resp, String emailSession) throws ServletException, IOException {
        ProductDTO productDTO = null;
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");

        try {
            String title = req.getParameter("title").trim();
            String image = UploadFile.uploadImagesServer(req);
            String createdBy = emailSession.trim();
            String content = req.getParameter("content").trim();

            productDTO = new ProductDTO(title, image, createdBy, content);

            //Check validate front-end
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<ProductDTO>> constraintViolations = validator.validate(productDTO);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<ProductDTO> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
            }
            // Check error

            //Do add result
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.setAttribute("product", productDTO);

                req.setAttribute("success", false);
                dispatcher.forward(req, resp);

            } else {
                productService.add(productDTO);

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
