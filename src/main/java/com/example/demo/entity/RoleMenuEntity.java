package com.example.demo.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户角色菜单类
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 拥有菜单Id
     */
    private Long menuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", menuId=" + menuId +
                '}';
    }
}
