package com.mvpt.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {//Single Responsibility Principle (SOLID)
    private Long id;
    private String fullName;
    private String mobile;
    private String email;
    private String password;
    private String address;
    private Role role;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLogin;
    private UserStatus status;

    List<Order> orders = new ArrayList<>();

    public User(Long id, String fullName, String mobile, String email, String password, String address, Role role, Date createdAt, Date updatedAt, Date lastLogin, UserStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.status = status;
    }

    public User(String fullName, String mobile, String email, String password, String address, Role role, UserStatus status) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public User(String fullName, String mobile, String email, String password, String address, Role role) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public User(Long id, String fullName, String mobile, String email, String password, String address, Role role, UserStatus status) {
        this.id = id;
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty (message = "Full Name must not be empty")
    @Pattern(regexp = "(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})",
            message = "Full name needs to capitalize the first letter. Ex: 'Johny Dang'")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotEmpty (message = "Mobile must not be empty")
    @Pattern(regexp ="^([+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*){10}$",
            message = "Mobile in the form: '0787264104' or '+(84)787264104'")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NotEmpty (message = "Email must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$",
            message = "Enter email in the form: phthinh_2609@gmail.com (Only used before @ special character: ._%+-)")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty (message = "Password must not be empty ")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}",
            message = "Enter the required password: 6 or more characters, have a digit, have a lower-case letter, have an upper-case letter")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty (message = "Email must not be empty")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
