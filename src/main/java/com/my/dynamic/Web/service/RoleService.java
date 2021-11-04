package com.my.dynamic.Web.service;


import com.my.dynamic.entity.shiro.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    /**
     * 分配权限
     * @param roleId
     * @param permIds
     */
    void assignPerms(String roleId, List<String> permIds);

    /**
     * 添加用户角色
     * @param role
     */
    void save(Role role);

    /**
     * 更新角色
     * @param role
     */
    void update(Role role);

    /**
     * 根据id删除用户角色
     * @param id
     */
    void delete(String id);

    /**
     * 根据id查询用户角色
     * @param id
     * @return
     */
    Role findById(String id);


    /**
     * 分页查询角色
     * @param companyId
     * @param page
     * @param pageSize
     * @return
     */
    Page<Role> findByPage(String companyId, int page, int pageSize);


    /**
     * 查询所有的角色
     * @param companyId
     * @return
     */
    List<Role> findAll(String companyId);
}
