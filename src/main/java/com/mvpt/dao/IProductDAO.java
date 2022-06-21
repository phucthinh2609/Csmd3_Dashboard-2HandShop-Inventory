package com.mvpt.dao;

import com.mvpt.dto.ProductDTO;
import com.mvpt.model.Product;

import java.util.List;

public interface IProductDAO {
    List<ProductDTO> selectAll();

    void insert(Product newProduct);

    void update(Product newProduct);

    ProductDTO selectById(Integer id);

    boolean existById(Integer id);

    List<ProductDTO> searchProduct(String keySearch);

    List<ProductDTO> selectProductsById(Integer id);
}
