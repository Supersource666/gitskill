package com.my.dynamic.entity.project;

import lombok.Data;

import java.sql.Date;

@Data
public class ProjectString {


    private String id;

    /**
     * 文件创建的时间
     */
    private Date date;

    /**
     * 文件夹的名称
     */
    private String name;

    /**
     * 用户id
     */
    private String userId;
}
