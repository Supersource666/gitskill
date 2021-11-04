package com.my.dynamic.entity.project;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "file_project")
public class Project implements Serializable {


    private static final long serialVersionUID = 2898246134753059202L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
