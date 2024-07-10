package com.codegym.thi_cuoi_module3.controllers;

import com.codegym.thi_cuoi_module3.models.Product;
import com.codegym.thi_cuoi_module3.services.IProductService;
import com.codegym.thi_cuoi_module3.services.impl.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product")
public class ProductControllers extends HttpServlet {
    private static IProductService productService = new ProductService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                req.getRequestDispatcher("/product/create.jsp").forward(req, resp);
                break;
            case "top":
                int topCount = Integer.parseInt(req.getParameter("topCount"));
                List<Product> topProducts = productService.findTopOrderedProducts(topCount);
                req.setAttribute("products", topProducts);
                req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
                break;
            case "viewByDate":
                String startDate = req.getParameter("startDate");
                String endDate = req.getParameter("endDate");
                List<Product> productsByDate = productService.findProductsByDate(startDate, endDate);
                req.setAttribute("productsByDate", productsByDate);
                req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
                break;
            default:
                List<Product> products = productService.findAll();
                req.setAttribute("products", products);
                req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                String nameProduct = req.getParameter("name_product");
                int price = Integer.parseInt(req.getParameter("price"));
                int discount = Integer.parseInt(req.getParameter("discount"));
                int stock_quantity = Integer.parseInt(req.getParameter("stock_quantity"));
                Product product = new Product(nameProduct,price,discount,stock_quantity);
                productService.save(product);
                req.setAttribute("message", "Thêm mới thành công");
                List<Product> products = productService.findAll();
                req.setAttribute("products", products);
                req.getRequestDispatcher("/product/list.jsp").forward(req, resp);
                break;
        }
    }

}
