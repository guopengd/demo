package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
@Service
public interface UserDao extends BaseDao<UserEntity> {

    /**
     * 根据账号查询
     *
     * @param name 账号
     * @return
     */
    UserEntity queryByName(String name);

    /**
     * 根据id查询所有菜单权限
     *
     * @param id id
     * @return
     */
    List<String> queryAllPerms(Long id);

    /**
     * 根据id查询所有用户权限
     *
     * @param id id
     * @return
     */
    List<String> queryAllRoles(Long id);

}
