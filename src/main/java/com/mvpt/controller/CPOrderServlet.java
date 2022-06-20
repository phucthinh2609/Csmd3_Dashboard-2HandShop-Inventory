package com.mvpt.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CPOrderServlet", urlPatterns = "/orders")
public class CPOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "create-purchase":
                showCreatePurchaseOrderForm(req, resp);
                break;
            case "create-sales":
                showCreateSalesOrderForm(req, resp);
                break;
            default:
                showOrderListForm(req, resp);
                break;
        }

    }

    private void showCreatePurchaseOrderForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/order/create-purchase.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCreateSalesOrderForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/order/create-sales.jsp");
        dispatcher.forward(req, resp);
    }

    private void showOrderListForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/order/list.jsp");
        dispatcher.forward(req, resp);
    }
}
