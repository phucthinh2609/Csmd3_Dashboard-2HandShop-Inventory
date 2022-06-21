//package com.mvpt.dto;
//
//import com.mvpt.model.Product;
//
//public class ProductMapper {
//    private static ProductMapper instance;
//
//    private ProductMapper() {
//    }
//
//    public static ProductMapper getInstance() {
//        if (instance == null)
//            instance = new ProductMapper();
//        return instance;
//    }
//
//    public ProductDTO toDTO(Product product) {
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setId(product.getId());
//        productDTO.setTitle(product.getTitle());
//        productDTO.setImage(product.getImage());
//        productDTO.setCreatedAt(product.getCreatedAt());
//        productDTO.setCreatedBy(String.valueOf(product.getCreatedBy()));
//        productDTO.setUpdatedAt(product.getUpdatedAt());
//        productDTO.setUpdatedBy(String.valueOf(product.getUpdatedBy()));
//        productDTO.setContent(product.getContent());
//
//        return productDTO;
//    }
//
//
//}
