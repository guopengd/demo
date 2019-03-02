package com.example.demo.service.impl;

import com.example.demo.dao.ActivityDao;
import com.example.demo.entity.ActivityEntity;
import com.example.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;

    @Override
    public ActivityEntity queryObject(Long id) {
        return activityDao.queryObject(id);
    }

    @Override
    public List<ActivityEntity> queryList(Map<String, Object> map) {
        return activityDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return activityDao.queryTotal(map);
    }

    @Override
    public void save(ActivityEntity user) {
        activityDao.save(user);
    }

    @Override
    public void update(ActivityEntity user) {
        activityDao.update(user);
    }

    @Override
    public void delete(Long id) {
        activityDao.delete(id);
    }
}
