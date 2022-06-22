package com.mvpt.dao;

import com.mvpt.dto.ProductDTO;

import java.util.List;

public interface IProductDAO {
    List<ProductDTO> selectAll();

    void insert(ProductDTO newProductDTO);

    void update(ProductDTO newProductDTO);

    ProductDTO selectById(Integer id);

    Integer getUserIdByEmail(String email);

    boolean existById(Integer id);

    List<ProductDTO> searchProduct(String keySearch);

    List<ProductDTO> selectProductsById(Integer id);
}
