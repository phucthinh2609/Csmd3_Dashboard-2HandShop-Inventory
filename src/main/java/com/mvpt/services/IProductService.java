package com.mvpt.services;


import com.mvpt.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();

    void add(ProductDTO newProductDTO);

    void update(ProductDTO newProductDTO);

    boolean existById(Integer id);

    ProductDTO findById(Integer id);

    List<ProductDTO> searchUser(String keySearch);

    List<ProductDTO> findProductsById(Integer id);
}
