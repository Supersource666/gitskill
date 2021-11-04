package com.my.dynamic.entity.shiro;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 功能权限
 */
@Getter
@Setter
@Entity
@Table(name = "pe_permission_point")
public class PermissionPoint implements Serializable {

    private static final long serialVersionUID = 4617952752981680125L;
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 权限代码
     */
    private String pointClass;

    /**
     * 权限图标
     */
    private String pointIcon;

    /**
     * 权限状态
     */
    private String pointStatus;

    /**
     * 名称
     */
    private String name;

}
