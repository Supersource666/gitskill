package com.my.dynamic.entity.shiro;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bs_user")
@Data
@ApiModel
@NoArgsConstructor
public class User implements Serializable {


    private static final long serialVersionUID = 5378724576258475259L;
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 启用状态 0：禁用，1：启用
     */
    private Integer enableState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门id
     */
    private String departmentId;

    /**
     * 部门工号
     */
    private String workNumber;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 用户等级
     * level
     * String
     * Admin:管理员所具备的权限
     * coAdmin：企业管理（创建企业时添加）
     * user：普通用户
     */
    private String level;




    /**
     *  @JsonIgnore 解决死循环
     */
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "pe_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();//用户与角色 多对多

}
