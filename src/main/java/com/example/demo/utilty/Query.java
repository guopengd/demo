package com.example.demo.utilty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dao层查询工具
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    // 当前页码
    private int page;
    // 每页条数
    private int rows;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        if (params.get("page") != null && params.get("rows") != null) {
            this.page = Integer.parseInt(params.get("page").toString());// 当前第几页
            this.rows = Integer.parseInt(params.get("rows").toString());// 每页显示记录条数
            this.put("offset", (page - 1) * rows);
            this.put("page", page);
            this.put("rows", rows);
        }
        // 防止SQL注入
        if (params.get("sort") != null && params.get("order") != null) {
            String sort = SqlFilterUtils.sqlInj(params.get("sort").toString());
            String order = SqlFilterUtils.sqlInj(params.get("order").toString());
            this.put("sort", sort);
            this.put("order", order);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}