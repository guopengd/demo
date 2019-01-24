package com.example.demo.entity;

import java.io.Serializable;

public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    // 角色名称
    private String roleName;
    //拥有菜单Id
    private Long menuId;

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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", menuId=" + menuId +
                '}';
    }
}
