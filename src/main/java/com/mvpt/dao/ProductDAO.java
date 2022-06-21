//package com.mvpt.dao;
//
//import com.mvpt.dto.ProductDTO;
//import com.mvpt.dto.UserDTO;
//import com.mvpt.model.Role;
//import com.mvpt.model.User;
//import com.mvpt.model.UserStatus;
//import com.mvpt.utils.MySQLConnUtils;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static com.mvpt.utils.MySQLConnUtils.getConnection;
//
//public class ProductDAO implements IProductDAO {
//    //Singleton Design Pattern
//    private static ProductDAO instance;
//
//    private ProductDAO() {
//    }
//
//    public static ProductDAO getInstance() {
//        if (instance == null)
//            instance = new ProductDAO();
//        return instance;
//    }
//
//
//    private static final String SELECT_ALL_PRODUCTS = "" +
//            "SELECT " +
//                "p.id," +
//                "p.user_id," +
//                "p.title," +
//                "p.created_at," +
//                "p.created_by," +
//                "p.updated_at," +
//                "p.updated_by," +
//                "p.content " +
//            "FROM products AS p;";
//
//    private static final String SP_INSERT_PRODUCT = "{CALL sp_insert_product(?, ?, ?, ?, ?, ?, ?)}";
//
//    private static final String SP_UPDATE_PRODUCT = "{CALL sp_update_product(?, ?, ?, ?, ?, ?, ?, ?)}";
//
//    private static final String SP_SELECT_PRODUCT_BY_ID = "{CALL sp_select_product_by_id(?)}";
//
//    private static final String SEARCH_PRODUCT = "{CALL sp_search_product(?)}";
//
//
//
//    @Override
//    public List<UserDTO> selectAll() {
//        List<UserDTO> products = new ArrayList<>();
//
//        try (
//                Connection conn = MySQLConnUtils.getConnection();
//                PreparedStatement statement = conn.prepareStatement(SELECT_ALL_PRODUCTS);
//             ){
//
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                Integer id = rs.getInt("id");
//                String title = rs.getString("title") ;
//                String image = rs.getString("image");
//                Date createdAt = rs.getDate("created_at");
//                String createdBy = rs.getString("created_by");
//                Date updatedAt = rs.getDate("updated_at");
//                String updatedBy = rs.getString("updated_by");
//                String content = rs.getString("content");
//
//                products.add(new ProductDTO(id, title, image, createdAt, createdBy, updatedAt, updatedBy, content));
//            }
//        } catch (SQLException ex) {
//            MySQLConnUtils.printSQLException(ex);
//        }
//        return products;
//    }
//
//    @Override
//    public void insert(Product newUser, User user) {
//
//        try (
//                Connection conn = getConnection();
//                CallableStatement statement = conn.prepareCall(SP_INSERT_PRODUCT);
//        ) {
//            statement.setString(1, newUser.getFullName());
//            statement.setString(2, newUser.getMobile());
//            statement.setString(3, newUser.getEmail());
//            statement.setString(4, newUser.getPassword());
//            statement.setString(5, newUser.getAddress());
//            statement.setString(6, newUser.getRole());
//            statement.setString(7, newUser.getStatus());
//            System.out.println(statement);
//            statement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(Product newUser) {
//        try (
//                Connection conn = getConnection();
//                CallableStatement statement = conn.prepareCall(SP_UPDATE_PRODUCT)
//        ) {
//            statement.setInt(1, newUser.getId());
//            statement.setString(2, newUser.getFullName());
//            statement.setString(3, newUser.getMobile());
//            statement.setString(4, newUser.getEmail());
//            statement.setString(5, newUser.getPassword());
//            statement.setString(6, newUser.getAddress());
//            statement.setString(7, newUser.getRole());
//            statement.setString(8, newUser.getStatus());
//
//            statement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public User selectById(Integer id) {
//        User user = null;
//
//        try (
//                Connection conn = getConnection();
//                CallableStatement statement = conn.prepareCall(SP_SELECT_PRODUCT_BY_ID);
//        ) {
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                String fullName = rs.getString("full_name");
//                String mobile = rs.getString("mobile");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                String address = rs.getString("address");
//                Role role = Role.parseRole(rs.getString("role"));
//                Date createdAt = rs.getDate("created_at");
//                Date updatedAt = rs.getDate("updated_at");
//                Date lastLogin = rs.getDate("last_login");
//                UserStatus status = UserStatus.parseUserStatus(rs.getString("status"));
//
//                user = new User(id, fullName, mobile, email, password, address, role, createdAt, updatedAt, lastLogin, status);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return user;
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        boolean exist = false;
//
//        try {
//            Connection conn = MySQLConnUtils.getConnection();
//
//            PreparedStatement statement = conn.prepareStatement(USER_EXISTS_BY_EMAIL);
//
//            statement.setString(1, email);
//
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                int count = rs.getInt("count");
//
//                if (count > 0) {
//                    exist = true;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return exist;
//    }
//
//    @Override
//    public boolean existsByPhone(String phone) {
//        boolean exist = false;
//
//        try {
//            Connection conn = MySQLConnUtils.getConnection();
//            PreparedStatement statement = conn.prepareStatement(USER_EXISTS_BY_PHONE);
//
//            statement.setString(1, phone);
//
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                int count = rs.getInt("count");
//
//                if (count > 0) {
//                    exist = true;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return exist;
//    }
//
//    @Override
//    public boolean existById(Integer id) {
//        return selectById(id) != null;
//    }
//
//    @Override
//    public String getOriginalEmail(Integer id) {
//        String originalEmail = null;
//
//        try {
//            Connection conn = MySQLConnUtils.getConnection();
//            PreparedStatement statement = conn.prepareStatement(USER_ORIGIN_EMAIL);
//
//            statement.setInt(1, id);
//
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                originalEmail = rs.getString("email");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return originalEmail;
//    }
//
//    @Override
//    public String getOriginalMobile(Integer id) {
//        String originalMobile = null;
//
//        try {
//            Connection conn = MySQLConnUtils.getConnection();
//            PreparedStatement statement = conn.prepareStatement(USER_ORIGIN_MOBILE);
//
//            statement.setInt(1, id);
//
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                originalMobile = rs.getString("mobile");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return originalMobile;
//    }
//
//    @Override
//    public List<User> searchUser(String keySearch) {
//        List<User> userList = new ArrayList<>();
//
//        try {
//            Connection connection = MySQLConnUtils.getConnection();
//
//
//            CallableStatement statement = connection.prepareCall(SEARCH_PRODUCT);
//            statement.setString(1, '%' + keySearch + '%');
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                Integer id = rs.getInt("id");
//                String fullName = rs.getString("full_name");
//                String mobile = rs.getString("mobile");
//                String email = rs.getString("email");
//                String address = rs.getString("address");
//                Role role = Role.parseRole(rs.getString("role"));
//                UserStatus status = UserStatus.parseUserStatus(rs.getString("status"));
//
//                userList.add(new User(id, fullName, mobile, email, address, role, status));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return userList;
//    }
//
//    @Override
//    public List<User> selectUsersById(Integer id) {
//        List<User> users = new ArrayList<>();
//
//        try (
//                Connection conn = getConnection();
//                CallableStatement statement = conn.prepareCall(SP_SELECT_PRODUCT_BY_ID);
//        ) {
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                String fullName = rs.getString("full_name");
//                String mobile = rs.getString("mobile");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                String address = rs.getString("address");
//                Role role = Role.parseRole(rs.getString("role"));
//                Date createdAt = rs.getDate("created_at");
//                Date updatedAt = rs.getDate("updated_at");
//                Date lastLogin = rs.getDate("last_login");
//                UserStatus status = UserStatus.parseUserStatus(rs.getString("status"));
//
//                users.add(new User(id, fullName, mobile, email, password, address, role, createdAt, updatedAt, lastLogin, status)) ;
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return users;
//    }
//
//
//}
