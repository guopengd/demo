package com.example.demo.utilty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dao层查询工具
 *
 * @author gpd
 * @date 2019/3/29
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    
    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页条数
     */
    private int rows;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 当前第几页
        this.page = Integer.parseInt(params.get("page").toString());
        // 每页显示记录条数
        this.rows = Integer.parseInt(params.get("rows").toString());
        this.put("offset", (page - 1) * rows);
        this.put("page", page);
        this.put("rows", rows);
        if (params.get("sort") != null && params.get("order") != null) {
            // 防止SQL注入
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