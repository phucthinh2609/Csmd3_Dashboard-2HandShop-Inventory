package com.mvpt.services;


import com.mvpt.dto.ProductDTO;
import com.mvpt.model.Product;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

    void add(Product newProduct);

    void update(Product newProduct);

    boolean existById(Integer id);

    ProductDTO findById(Integer id);

    List<ProductDTO> searchUser(String keySearch);

    List<ProductDTO> findProductsById(Integer id);
}
