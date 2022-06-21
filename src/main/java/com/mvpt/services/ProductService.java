//package com.mvpt.services;
//
//
//import com.mvpt.Constant;
//import com.mvpt.dao.IProductDAO;
//import com.mvpt.dao.IUserDAO;
//import com.mvpt.dao.ProductDAO;
//import com.mvpt.exceptions.NotFoundException;
//import com.mvpt.model.Role;
//import com.mvpt.model.User;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class ProductService implements IProductService {
//    private final IProductDAO productDAO;
//
//    //Singleton Design Pattern
//    private static ProductService instance;
//
//    private ProductService() {
//        productDAO = ProductDAO.getInstance();
//    }
//
//    public static ProductService getInstance() {
//        if (instance == null)
//            instance = new ProductService();
//        return instance;
//    }
//
//    @Override
//    public List<Product> findAll() {
//        //return userDao.selectAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
//        List<Product> products = new ArrayList<>();
//        for (Product product : productDAO.selectAll()) {
//            products.add(product);
//        }
//        return products;
//    }
//
//    @Override
//    public Product adminLogin(String username, String password) {
//        User user = userDao.login(username, password, Role.ADMIN);
//        if (user == null)
//            throw new NotFoundException(Constant.USER_NOT_FOUND);
//        return userMapper.toDTO(user);
//    }
//
////    public void logout() {
////        currentUser = null;
////    }
//
//    @Override
//    public void add(Product newProduct) {
//        newProduct.setCreatedAt(new Date());
//        userDao.insert(newProduct);
//    }
//
//    @Override
//    public void update(Product newUser) {
//        newUser.setUpdatedAt(new Date());
//        userDao.update(newUser);
//    }
//
//    @Override
//    public boolean existById(Integer id) {
//        return userDao.existById(id);
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        return userDao.existsByEmail(email);
//    }
//
//    @Override
//    public boolean existsByMobile(String phone) {
//        return userDao.existsByPhone(phone);
//    }
//
//    @Override
//    public String getOriginalEmail(Integer id) {
//        return userDao.getOriginalEmail(id);
//    }
//
//    @Override
//    public String getOriginalMobile(Integer id) {
//        return userDao.getOriginalMobile(id);
//    }
//
//    @Override
//    public User findById(Integer id) {
//        User user = userDao.selectById(id);
//        if (user == null)
//            throw new NotFoundException(Constant.USER_NOT_FOUND);
//
//        return user;
//    }
//
//    @Override
//    public List<User> searchUser(String keySearch) {
//        return userDao.searchUser(keySearch);
//    }
//
//    @Override
//    public List<User> findUsersById(Integer id) {
//        return userDao.selectUsersById(id);
//    }
//
//}
