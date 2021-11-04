package com.my.dynamic.entity.shiro;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserResult implements Serializable {

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

    private List<String> roleIds = new ArrayList<>();

    public UserResult(User user) {
        BeanUtils.copyProperties(user,this);
        for (Role role : user.getRoles()) {
            this.roleIds.add(role.getId());
        }
    }


}
