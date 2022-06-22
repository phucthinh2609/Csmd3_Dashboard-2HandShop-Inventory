package com.mvpt.dao;

import com.mvpt.dto.ProductDTO;
import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserStatus;
import com.mvpt.utils.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mvpt.utils.MySQLConnUtils.getConnection;

public class ProductDAO implements IProductDAO {
    //Singleton Design Pattern
    private static ProductDAO instance;

    private ProductDAO() {
    }

    public static ProductDAO getInstance() {
        if (instance == null)
            instance = new ProductDAO();
        return instance;
    }


    private static final String VIEW_ALL_PRODUCTS = "SELECT * FROM vw_products";

    private static final String SP_INSERT_PRODUCT = "{CALL sp_insert_product(?, ?, ?, ?)}";

    private static final String SP_UPDATE_PRODUCT = "{CALL sp_update_product(?, ?, ?, ?, ?)}";

    private static final String SP_SELECT_PRODUCT_BY_ID = "{CALL sp_select_product_by_id(?)}";

    private static final String SP_SELECT_USER_BY_EMAIL = "{CALL sp_select_user_by_email(?)}";

    private static final String SEARCH_PRODUCT = "{CALL sp_search_product(?)}";



    @Override
    public List<ProductDTO> selectAll() {
        List<ProductDTO> products = new ArrayList<>();

        try (
                Connection conn = MySQLConnUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(VIEW_ALL_PRODUCTS);
             ){

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title") ;
                String image = rs.getString("image");
                Date createdAt = rs.getDate("created_at");
                String createdBy = rs.getString("created_by");
                Date updatedAt = rs.getDate("updated_at");
                String updatedBy = rs.getString("updated_by");
                String content = rs.getString("content");

                products.add(new ProductDTO(id, title, image, createdAt, createdBy, updatedAt, updatedBy, content));
            }
        } catch (SQLException ex) {
            MySQLConnUtils.printSQLException(ex);
        }
        return products;
    }

    @Override
    public void insert(ProductDTO newProductDTO) {

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_INSERT_PRODUCT);
        ) {
            Integer userId = getUserIdByEmail(newProductDTO.getCreatedBy());

            statement.setString(1, newProductDTO.getTitle());
            statement.setString(2, newProductDTO.getImage());
            statement.setInt(3, userId);
            statement.setString(4, newProductDTO.getContent());
            System.out.println(statement);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductDTO newProductDTO) {
        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_UPDATE_PRODUCT)
        ) {
            Integer userId = getUserIdByEmail(newProductDTO.getUpdatedBy());

            statement.setInt(1, newProductDTO.getId());
            statement.setString(2, newProductDTO.getTitle());
            statement.setString(3, newProductDTO.getImage());
            statement.setInt(4, userId);
            statement.setString(5, newProductDTO.getContent());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductDTO selectById(Integer id) {
        ProductDTO product = null;

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_SELECT_PRODUCT_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String image = rs.getString("image");
                Date createdAt = rs.getDate("created_at");
                String createdBy = rs.getString("created_by");
                Date updatedAt = rs.getDate("updated_at");
                String updatedBy = rs.getString("updated_by");
                String content = rs.getString("content");


                product = new ProductDTO (id, title, image, createdAt, createdBy, updatedAt, updatedBy, content);
                System.out.println(product);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return product;
    }

    @Override
    public Integer getUserIdByEmail(String email) {
        User user = null;
        Integer userId = null;

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_SELECT_USER_BY_EMAIL);
        ) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String password = rs.getString("password");
                String address = rs.getString("address");
                Role role = Role.parseRole(rs.getString("role"));
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                Date lastLogin = rs.getDate("last_login");
                UserStatus status = UserStatus.parseUserStatus(rs.getString("status"));

                user = new User(id, fullName, mobile, email, password, address, role, createdAt, updatedAt, lastLogin, status);

                userId = user.getId();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return userId;
    }

    @Override
    public boolean existById(Integer id) {
        return selectById(id) != null;
    }

    @Override
    public List<ProductDTO> searchProduct(String keySearch) {
        List<ProductDTO> productList = new ArrayList<>();

        try {
            Connection connection = MySQLConnUtils.getConnection();


            CallableStatement statement = connection.prepareCall(SEARCH_PRODUCT);
            statement.setString(1, '%' + keySearch + '%');
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title") ;
                String image = rs.getString("image");
                Date createdAt = rs.getDate("created_at");
                String createdBy = rs.getString("created_by");
                Date updatedAt = rs.getDate("updated_at");
                String updatedBy = rs.getString("updated_by");
                String content = rs.getString("content");

                productList.add(new ProductDTO(id, title, image, createdAt, createdBy, updatedAt, updatedBy, content));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<ProductDTO> selectProductsById(Integer id) {
        List<ProductDTO> productDTOList = new ArrayList<>();

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_SELECT_PRODUCT_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title") ;
                String image = rs.getString("image");
                Date createdAt = rs.getDate("created_at");
                String createdBy = rs.getString("created_by");
                Date updatedAt = rs.getDate("updated_at");
                String updatedBy = rs.getString("updated_by");
                String content = rs.getString("content");

                productDTOList.add(new ProductDTO(id, title, image, createdAt, createdBy, updatedAt, updatedBy, content));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productDTOList;
    }


}
