package com.codegym.thi_cuoi_module3.repositories.impl;

import com.codegym.thi_cuoi_module3.models.Product;
import com.codegym.thi_cuoi_module3.repositories.IProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select * from products");
            ResultSet resultSet = preparedStatement.executeQuery();
            int idProduct;
            String nameProduct;
            int price;
            int discount;
            int stock_quantity;
            while (resultSet.next()) {
                idProduct = resultSet.getInt("product_id");
                nameProduct = resultSet.getString("product_name");
                price = resultSet.getInt("price");
                discount = resultSet.getInt("discount");
                stock_quantity = resultSet.getInt("stock_quantity");
                products.add(new Product(idProduct, nameProduct, price, discount, stock_quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().
                    prepareStatement("insert into products(product_id,product_name,price,discount,stock_quantity) values (?, ?, ?, ?,?)");
            preparedStatement.setInt(1,product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getDiscount());
            preparedStatement.setInt(5, product.getStock_quantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findTopOrderedProducts(int topCount) {
        List<Product> topProducts = new ArrayList<>();

        try {
            Connection connection = BaseRepository.getConnection();
            String sql = "SELECT p.product_id, p.product_name, p.price, p.discount, p.stock_quantity, " +
                    "       COUNT(od.order_id) AS total_orders " +
                    "FROM products p " +
                    "JOIN order_details od ON p.product_id = od.product_id " +
                    "GROUP BY p.product_id " +
                    "ORDER BY total_orders DESC " +
                    "LIMIT ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, topCount);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idProduct = resultSet.getInt("product_id");
                String nameProduct = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                int discount = resultSet.getInt("discount");
                int stock_quantity = resultSet.getInt("stock_quantity");
                Product product = new Product(idProduct, nameProduct, price, discount, stock_quantity);
                topProducts.add(product);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching top ordered products", e);
        }

        return topProducts;
    }

    @Override
    public List<Product> findProductsByDate(String startDate, String endDate) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.product_id, p.product_name, p.price, p.discount, p.stock_quantity " +
                "FROM products p " +
                "JOIN order_details od ON p.product_id = od.product_id " +
                "JOIN orders o ON od.order_id = o.order_id " +
                "WHERE o.order_date BETWEEN ? AND ?";
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(query);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idProduct = resultSet.getInt("product_id");
                String nameProduct = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                int discount = resultSet.getInt("discount");
                int stockQuantity = resultSet.getInt("stock_quantity");
                products.add(new Product(idProduct, nameProduct, price, discount, stockQuantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
