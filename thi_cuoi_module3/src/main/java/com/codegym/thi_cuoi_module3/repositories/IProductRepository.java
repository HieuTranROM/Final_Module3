package com.codegym.thi_cuoi_module3.repositories;

import com.codegym.thi_cuoi_module3.models.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    void save(Product product);

    List<Product> findTopOrderedProducts(int topCount);

    List<Product> findProductsByDate(String startDate, String endDate);
}
