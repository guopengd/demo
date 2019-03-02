package com.example.demo.service.impl;

import com.example.demo.dao.ElderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.ElderEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.ElderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ElderService")
public class ElderServiceImpl implements ElderService {

    @Autowired
    ElderDao elderDao;
    @Autowired
    UserDao userDao;

    @Override
    public ElderEntity queryObject(Long id) {
        return elderDao.queryObject(id);
    }

    @Override
    public List<ElderEntity> queryList(Map<String, Object> map) {
        List<ElderEntity> list = elderDao.queryList(map);
        for (ElderEntity elder : list) {
            if (elder.getUserId() != null) {
                UserEntity user = userDao.queryObject(elder.getUserId());
                elder.setUserName(user == null ? null : user.getUserName());
            }
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return elderDao.queryTotal(map);
    }

    @Override
    public void save(ElderEntity elder) {
        elderDao.save(elder);
    }

    @Override
    public void update(ElderEntity elder) {
        elderDao.update(elder);
    }

    @Override
    public void delete(Long id) {
        elderDao.delete(id);
    }
}
