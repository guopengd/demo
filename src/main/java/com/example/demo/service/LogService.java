package com.example.demo.service;

import com.example.demo.entity.LogEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统日志接口类
 */
public interface LogService {

    /**
     * 查询日志
     *
     * @param id
     * @return
     */
    LogEntity queryObject(Long id);

    /**
     * 查询日志信息列表
     *
     * @param map
     * @return
     */
    List<LogEntity> queryList(Map<String, Object> map);

    /**
     * 批量保存日志
     *
     * @param sysLogs
     * @return
     */
    int saveBatch(List<LogEntity> sysLogs);

    /**
     * 查询日志信息总数
     *
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存日志信息
     *
     * @param sysLog
     */
    void save(LogEntity sysLog);

    /**
     * 更新日志信息
     *
     * @param sysLog
     */
    void update(LogEntity sysLog);

    /**
     * 删除日志信息
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除日志信息
     *
     * @param ids
     */
    void deleteBatch(Long[] ids);

    /**
     * 定期删除消息
     *
     * @param days 保留的月份数
     */
    void deleteSysLog(Integer days);
}
