package com.mvpt.services;


import com.mvpt.Constant;
import com.mvpt.dao.IProductDAO;
import com.mvpt.dao.ProductDAO;
import com.mvpt.dto.ProductDTO;
import com.mvpt.exceptions.NotFoundException;
import com.mvpt.model.Product;

import java.util.Date;
import java.util.List;

public class ProductService implements IProductService {
    private final IProductDAO productDAO;

    //Singleton Design Pattern
    private static ProductService instance;

    private ProductService() {
        productDAO = ProductDAO.getInstance();
    }

    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productDAO.selectAll();
    }

    @Override
    public void add(Product newProduct) {
        newProduct.setCreatedAt(new Date());
        productDAO.insert(newProduct);
    }

    @Override
    public void update(Product newProduct) {
        newProduct.setUpdatedAt(new Date());
        productDAO.update(newProduct);
    }

    @Override
    public boolean existById(Integer id) {
        return productDAO.existById(id);
    }

    @Override
    public ProductDTO findById(Integer id) {
        ProductDTO product = productDAO.selectById(id);
        if (product == null)
            throw new NotFoundException(Constant.USER_NOT_FOUND);

        return product;
    }

    @Override
    public List<ProductDTO> searchUser(String keySearch) {
        return productDAO.searchProduct(keySearch);
    }

    @Override
    public List<ProductDTO> findProductsById(Integer id) {
        return productDAO.selectProductsById(id);
    }

}
