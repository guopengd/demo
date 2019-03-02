package com.example.demo.service;

import com.example.demo.entity.ElderEntity;

import java.util.List;
import java.util.Map;

public interface ElderService {

    // 通过id查询老人信息
    ElderEntity queryObject(Long id);

    // 查询老人信息列表
    List<ElderEntity> queryList(Map<String, Object> map);

    // 查询老人信息总数
    int queryTotal(Map<String, Object> map);

    // 保存老人信息
    void save(ElderEntity user);

    // 更新老人信息
    void update(ElderEntity user);

    // 删除老人信息
    void delete(Long id);
}
