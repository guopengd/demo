package com.example.demo.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String password;
    private int state;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", state=" + state + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void change() {
        if (state == 1)
            state = 2;
        else if (state == 2)
            state = 1;
    }

}
