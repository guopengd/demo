package com.example.demo.service;

import com.example.demo.entity.ActivityEntity;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    // 通过id查询社区活动
    ActivityEntity queryObject(Long id);

    // 查询社区活动列表
    List<ActivityEntity> queryList(Map<String, Object> map);

    // 查询社区活动总数
    int queryTotal(Map<String, Object> map);

    // 保存社区活动
    void save(ActivityEntity user);

    // 更新社区活动
    void update(ActivityEntity user);

    // 删除社区活动
    void delete(Long id);
}
