package com.example.demo.dao;

import com.example.demo.entity.LogEntity;
import org.springframework.stereotype.Service;

@Service
public interface LogDao extends BaseDao<LogEntity> {
    /**
     * 定期删除历史消息
     */
    void deleteSysLog(Integer days);

}
