package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    // 角色名称
    private String roleName;
    // 角色描述
    private String describe;
    // 创建日期
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", describe='" + describe + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
