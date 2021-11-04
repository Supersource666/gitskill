package com.my.dynamic.Web.service;

import com.my.dynamic.entity.shiro.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     *根据工号查询用户
     * @param workNumber
     * @return
     */
    User findByWorkNumber(String workNumber);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);

    /**
     * 查询企业的部门列表
     * @param map
     * @param page 第几页
     * @param size 每页的大小
     * @return
     */
    Page<User> findAll(Map map, int page, int size);

    /**
     * 分配角色
     * @param userId
     * @param roleIds
     */
    void assignRoles(String userId, List<String> roleIds);

    /**
     * 根据username 查询，判断当前是否存在相同的用户名用户
     * @param username
     * @return
     */
    User findByUserName(String username);

}
