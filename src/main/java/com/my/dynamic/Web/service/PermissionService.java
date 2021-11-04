package com.my.dynamic.Web.service;

import com.my.dynamic.entity.shiro.Permission;
import com.my.dynamic.exception.CommonException;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    /**
     * 查询全部
     * @param map
     * @return
     */
    List<Permission> findAll(Map<String, Object> map);


    /**
     * 保存
     * @param map
     */
    void save(Map<String, Object> map) throws Exception;

    /**
     * 修改权限
     * @param map
     */
    void update(Map<String, Object> map) throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Map findById(String id) throws CommonException;

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id) throws CommonException;
}
