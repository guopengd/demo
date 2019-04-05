package com.example.demo.dao;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
public interface BaseDao<T> {

    /**
     * 根据实体保存
     *
     * @param t 泛型实体
     */
    void save(T t);

    /**
     * 根据Map集合字段进行数据保存
     *
     * @param map 数据集合
     */
    void save(Map<String, Object> map);

    /**
     * 批量保存
     *
     * @param list 实体泛型集合
     */
    void saveBatch(List<T> list);

    /**
     * 根据实体修改数据
     *
     * @param t 实体泛型
     * @return
     */
    int update(T t);

    /**
     * 根据map集合字段修改数据
     *
     * @param map 字段集合
     * @return
     */
    int update(Map<String, Object> map);

    /**
     * 根据id删除数据
     *
     * @param id id
     * @return
     */
    int delete(Object id);

    /**
     * 根据集合字段判断删除数据
     *
     * @param map 字段集合
     * @return
     */
    int delete(Map<String, Object> map);

    /**
     * 根据id集合批量删除
     *
     * @param id id集合
     * @return
     */
    int deleteBatch(Object[] id);

    /**
     * 根据id查询
     *
     * @param id id
     * @return
     */
    T queryObject(Object id);

    /**
     * 根据字段集合查询
     *
     * @param map 字段集合
     * @return
     */
    List<T> queryList(Map<String, Object> map);

    /**
     * 根据id批量查询
     *
     * @param id id集合
     * @return
     */
    List<T> queryList(Object id);

    /**
     * 根据map集合字段查询总数
     *
     * @param map 集合字段
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 查询总数
     *
     * @return
     */
    int queryTotal();
}
