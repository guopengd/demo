package com.example.demo.entity;

import java.io.Serializable;

public class MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    // 父菜单Id
    private Long parentId;
    // 菜单跳转地址
    private String path;
    // 菜单名称
    private String name;
    // 菜单标题
    private String title;
    // 菜单图标
    private String icon;
    // 菜单排序
    private String order;
    // 菜单url
    private String url;
    // 菜单所需权限
    private String perms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", order='" + order + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                '}';
    }
}
