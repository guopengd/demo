package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 账户名称
     */
    private String userName;

    /**
     * 账户密码
     */
    private String password;

    /**
     * 账户状态
     */
    private Integer status;

    /**
     * 账户Email
     */
    private String email;

    /**
     * 账户手机号
     */
    private String phone;

    /**
     * 账户创建时间
     */
    private Date createDate;

    /**
     * 角色名称
     */
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public UserEntity() {
    }

    public UserEntity(CreateUserEntity user) {
        setPhone(user.getPhone());
        setEmail(user.getEmail());
        setStatus(user.getStatus());
        setPassword(user.getPassword());
        setUserName(user.getUserName());
        setCreateDate(user.getCreateDate());
        setId(user.getId());
        setRoleId(user.getRoleId());
        setRoleName(user.getRoleName());
    }
}
