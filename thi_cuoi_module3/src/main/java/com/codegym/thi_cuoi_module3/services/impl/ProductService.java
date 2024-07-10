package com.codegym.thi_cuoi_module3.services.impl;

import com.codegym.thi_cuoi_module3.models.Product;
import com.codegym.thi_cuoi_module3.repositories.IProductRepository;
import com.codegym.thi_cuoi_module3.repositories.impl.ProductRepository;
import com.codegym.thi_cuoi_module3.services.IProductService;

import java.util.List;

public class ProductService implements IProductService {

    private static IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findTopOrderedProducts(int topCount) {
        return productRepository.findTopOrderedProducts(topCount);
    }

    public List<Product> findProductsByDate(String startDate, String endDate) {
        return productRepository.findProductsByDate(startDate, endDate);
    }
}
