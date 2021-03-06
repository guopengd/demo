package com.example.demo.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 父表id和子表ids对应实体类
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
public class IdAndIdsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父表id
     */
    private Long id;
    /**
     * 子表id集合
     */
    private List<Long> ids = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "RoleName{" +
                "id=" + id +
                ", ids=" + ids +
                '}';
    }
}