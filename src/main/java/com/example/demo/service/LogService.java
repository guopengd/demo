package com.example.demo.service;

import com.example.demo.entity.LogEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统日志接口类
 */
public interface LogService {

    // 查询日志
    LogEntity queryObject(Long id);

    // 查询日志信息列表
    List<LogEntity> queryList(Map<String, Object> map);

    // 批量保存日志
    int saveBatch(List<LogEntity> sysLogs);

    // 查询日志信息总数
    int queryTotal(Map<String, Object> map);

    // 保存日志信息
    void save(LogEntity sysLog);

    // 更新日志信息
    void update(LogEntity sysLog);

    // 删除日志信息
    void delete(Long id);

    // 批量删除日志信息
    void deleteBatch(Long[] ids);
}
