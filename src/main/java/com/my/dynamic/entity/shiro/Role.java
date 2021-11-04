package com.my.dynamic.entity.shiro;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pe_role")
@Data
@NoArgsConstructor
public class Role implements Serializable {


    private static final long serialVersionUID = 594829320797158219L;

    /**
     * id
     */
    @Id
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 企业id
     */
    private String companyId;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users=new HashSet<>(); //角色与用户

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "pe_role_permission",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")}
    )
    private Set<Permission> permissions=new HashSet<>();
}
