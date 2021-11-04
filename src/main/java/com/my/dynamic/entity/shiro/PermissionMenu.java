package com.my.dynamic.entity.shiro;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 菜单权限
 */
@Setter
@Getter
@Entity
@Table(name = "pe_permission_menu")
public class PermissionMenu implements Serializable {

    private static final long serialVersionUID = -6221036235976682094L;
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 展示图标
     */
    private String menuIcon;

    /**
     * 排序号
     */
    private String menuOrder;

    private String name;


}
