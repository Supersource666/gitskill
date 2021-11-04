package com.my.dynamic.Web.service;

import com.my.dynamic.entity.project.ProjectString;

import java.util.List;

public interface ProjectService {

    /**
     * 获得当前用户的project信息；
     * @param id 主键
     * @return 返回当前用户所有的文件链表
     */
    List<ProjectString> getAllProject(String id);

    /**
     * 为当前用户添加新的project
     * @param description 当前用户的描述
     * @param id
     */
    void addFileProject(String description, String id) throws Exception;

    /**
     * 修改文件夹
     * @param userId
     * @param projectId
     * @param description
     */
    void update(String userId, long projectId, String description);

    /**
     * 删除需要删除的project
     * @param projectId
     * @param username
     * @param id
     */
    void deleteProject(long projectId, String username, String id);

}
