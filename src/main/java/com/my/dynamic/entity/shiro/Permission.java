package com.my.dynamic.entity.shiro;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pe_permission")
@Data
@NoArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable {


    private static final long serialVersionUID = 1600060379716677843L;
    @Id
    private String id;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限类型 1为菜单 2为按钮 3为Api
     */
    private int type;

    /**
     * 设置父id
     */
    private String pid;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 企业是否可见权限菜单、按钮、api
     * 0：不可见
     * 1：可见
     */
    private String enVisible;

    public Permission(String name,int type,String code,String description){
        this.name=name;
        this.type=type;
        this.code=code;
        this.description=description;
    }

//    @JsonIgnore
//    @ManyToMany(mappedBy = "permissions")
//    private Set<Role> roles=new HashSet<>(); //角色与用户

}
