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
    private Map<String, Object> page;

    /**
     * 分页
     * @param rows 列表数据
     * @param page 分页数据
     */
    public PageUtils(List<?> rows, Map<String, Object> page) {
        this.rows = rows;
        this.page = page;
    }

    public Map<String, Object> getPage() {
        return page;
    }

    public void setPage(Map<String, Object> page) {
        this.page = page;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}

