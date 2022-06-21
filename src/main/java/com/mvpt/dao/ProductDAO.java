package com.mvpt.dao;

import com.mvpt.dto.ProductDTO;
import com.mvpt.model.Product;
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


    private static final String VIEW_ALL_PRODUCTS = "{SELECT * FROM vw_products}";

    private static final String SP_INSERT_PRODUCT = "{CALL sp_insert_product(?, ?, ?, ?)}";

    private static final String SP_UPDATE_PRODUCT = "{CALL sp_update_product(?, ?, ?, ?, ?)}";

    private static final String SP_SELECT_PRODUCT_BY_ID = "{CALL sp_select_product_by_id(?)";

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
    public void insert(Product newProduct) {

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_INSERT_PRODUCT);
        ) {
            statement.setString(1, newProduct.getTitle());
            statement.setString(2, newProduct.getImage());
            statement.setInt(3, 988);
            statement.setString(4, newProduct.getContent());
            System.out.println(statement);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product newProduct) {
        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_UPDATE_PRODUCT)
        ) {
            statement.setInt(1, newProduct.getId());
            statement.setString(2, newProduct.getTitle());
            statement.setString(3, newProduct.getImage());
            statement.setInt(4, 998);
            statement.setString(5, newProduct.getContent());

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
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return product;
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
