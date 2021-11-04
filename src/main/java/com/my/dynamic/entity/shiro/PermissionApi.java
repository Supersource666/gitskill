package com.my.dynamic.entity.shiro;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * api接口权限
 */
@Getter
@Setter
@Entity
@Table(name = "pe_permission_api")
public class PermissionApi implements Serializable {

    private static final long serialVersionUID = 2615744885817958915L;
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 权限等级，1为通用接口权限，2为需校验接口的权限
     */
    private String apiLevel;

    /**
     * 请求类型
     */
    private String apiMethod;

    /**
     * 权限等级
     */
    private String apiUrl;




}
