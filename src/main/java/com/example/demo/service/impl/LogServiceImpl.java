package com.example.demo.service.impl;


import com.example.demo.dao.LogDao;
import com.example.demo.entity.LogEntity;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统日志实现类
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao sysLogDao;

    @Override
    public LogEntity queryObject(Long id) {
        return sysLogDao.queryObject(id);
    }

    @Override
    public List<LogEntity> queryList(Map<String, Object> map) {
        return sysLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysLogDao.queryTotal(map);
    }

    @Override
    public void save(LogEntity sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public void update(LogEntity sysLog) {
        sysLogDao.update(sysLog);
    }

    @Override
    public void delete(Long id) {
        sysLogDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysLogDao.deleteBatch(ids);
    }

    @Override
    public int saveBatch(List<LogEntity> sysLogs) {
        sysLogDao.saveBatch(sysLogs);
        return 1;
    }
}

