package com.mvpt.dao;

import com.mvpt.dto.UserDTO;
import com.mvpt.model.Role;
import com.mvpt.model.User;
import com.mvpt.model.UserStatus;
import com.mvpt.utils.MySQLConnUtils;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.mvpt.utils.MySQLConnUtils.getConnection;

public class UserDAO implements IUserDAO {
    //Singleton Design Pattern
    private static UserDAO instance;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null)
            instance = new UserDAO();
        return instance;
    }


    private String USER_EXISTS_BY_PHONE = "" +
            "SELECT COUNT(*) AS count " +
            "FROM users AS u " +
            "WHERE u.mobile = ?;";

    private String USER_EXISTS_BY_EMAIL = "" +
            "SELECT COUNT(*) AS count " +
            "FROM users AS u " +
            "WHERE u.email = ?;";

    private static final String SELECT_ALL_USERS = "" +
            "SELECT " +
                "u.id," +
                "u.full_name," +
                "u.mobile," +
                "u.email," +
                "u.`password`," +
                "u.address," +
                "u.`role`," +
                "u.created_at," +
                "u.updated_at," +
                "u.last_login," +
                "u.status " +
            "FROM users AS u;";

    private static final String SP_INSERT_USER = "{CALL sp_insert_user(?, ?, ?, ?, ?, ?, ?)}";

    private static final String SP_UPDATE_USER = "{CALL sp_update_user(?, ?, ?, ?, ?, ?, ?, ?)}";

    private static final String SP_SELECT_USER_BY_ID = "{CALL sp_select_user_by_id(?)}";

    private static final String USER_ORIGIN_EMAIL = "" +
            "SELECT u.email " +
            "FROM users AS u " +
            "WHERE u.id = ? ";

    private static final String USER_ORIGIN_MOBILE = "" +
            "SELECT u.mobile " +
            "FROM users AS u " +
            "WHERE u.id = ? ";;


    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();

        try (
                Connection conn = MySQLConnUtils.getConnection();
                PreparedStatement statement = conn.prepareStatement(SELECT_ALL_USERS);
             ){

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");
                Role role = Role.parseRole(rs.getString("role"));
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                Date lastLogin = rs.getDate("last_login");
                UserStatus status = UserStatus.parseUserStatus(rs.getString("status"));

                users.add(new User(id, fullName, mobile, email, password, address, role, createdAt, updatedAt, lastLogin, status));
            }
        } catch (SQLException ex) {
            MySQLConnUtils.printSQLException(ex);
        }
        return users;
    }

    @Override
    public User login(String username, String password, Role role) {

        return null;
    }

    @Override
    public void insert(UserDTO newUser) {

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_INSERT_USER);
        ) {
            statement.setString(1, newUser.getFullName());
            statement.setString(2, newUser.getMobile());
            statement.setString(3, newUser.getEmail());
            statement.setString(4, newUser.getPassword());
            statement.setString(5, newUser.getAddress());
            statement.setString(6, newUser.getRole());
            statement.setString(7, newUser.getStatus());
            System.out.println(statement);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO newUser) {
        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_UPDATE_USER)
        ) {
            statement.setLong(1, newUser.getId());
            statement.setString(2, newUser.getFullName());
            statement.setString(3, newUser.getMobile());
            statement.setString(4, newUser.getEmail());
            statement.setString(5, newUser.getPassword());
            statement.setString(6, newUser.getAddress());
            statement.setString(7, newUser.getRole());
            statement.setString(8, newUser.getStatus());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectById(Long id) {
        User user = null;

        try (
                Connection conn = getConnection();
                CallableStatement statement = conn.prepareCall(SP_SELECT_USER_BY_ID);
        ) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");
                Role role = Role.parseRole(rs.getString("role"));
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                Date lastLogin = rs.getDate("last_login");
                UserStatus status = UserStatus.parseUserStatus(rs.getString("status"));

                user = new User(id, fullName, mobile, email, password, address, role, createdAt, updatedAt, lastLogin, status);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        boolean exist = false;

        try {
            Connection conn = MySQLConnUtils.getConnection();

            PreparedStatement statement = conn.prepareStatement(USER_EXISTS_BY_EMAIL);

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    @Override
    public boolean existsByPhone(String phone) {
        boolean exist = false;

        try {
            Connection conn = MySQLConnUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(USER_EXISTS_BY_PHONE);

            statement.setString(1, phone);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    @Override
    public boolean existById(Long id) {
        return selectById(id) != null;
    }

    @Override
    public String getOriginalEmail(Long id) {
        String originalEmail = null;

        try {
            Connection conn = MySQLConnUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(USER_ORIGIN_EMAIL);

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                originalEmail = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return originalEmail;
    }

    @Override
    public String getOriginalMobile(Long id) {
        String originalMobile = null;

        try {
            Connection conn = MySQLConnUtils.getConnection();
            PreparedStatement statement = conn.prepareStatement(USER_ORIGIN_MOBILE);

            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                originalMobile = rs.getString("mobile");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return originalMobile;
    }


}
