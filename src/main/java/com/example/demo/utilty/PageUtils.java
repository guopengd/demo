package com.example.demo.utilty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 */
public class PageUtils implements Serializable {

    private static final long serialVersionUID = 1L;

    // 列表数据
    private List<?> rows;
    // 分页数据
    private Integer total;

    /**
     * 分页
     *
     * @param rows  列表数据
     * @param total 数据总数
     */
    public PageUtils(List<?> rows, Integer total) {
        this.rows = rows;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}

