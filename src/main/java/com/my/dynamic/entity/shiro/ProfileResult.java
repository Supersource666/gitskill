package com.my.dynamic.entity.shiro;


import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.*;


@Setter
@Getter
public class ProfileResult implements Serializable, AuthCachePrincipal {

    //用户主键
    private String id;

    //公司名称
    private String company;
    //公司id
    private String companyId;
    //用户名称
    private String userName;
    //用户工号
    private String workNumber;
    //用户角色
    private Map<String,Object> roles=new HashMap<>();

    public ProfileResult(User user) {

        this.companyId=user.getCompanyId();
        this.company=user.getCompanyName();
        this.userName=user.getUsername();
        this.workNumber=user.getWorkNumber();
        this.id=user.getId();


        Set<Role> roles = user.getRoles();
        Set<Permission> menus=new HashSet<>();  //菜单 1
        Set<String> points=new HashSet<>(); //按钮 2
        Set<String> apis=new HashSet<>();   //api 3

        for (Role role : roles) {
            Set<Permission> perms= role.getPermissions();
            for (Permission perm : perms) {
                String code=perm.getCode();
                if (perm.getType()==1){
                    menus.add(perm);
                }else if (perm.getType()==2){
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
        }

        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);


    }

    public ProfileResult(User user, List<Permission> list) {
        this.companyId=user.getCompanyId();
        this.company=user.getCompanyName();
        this.userName=user.getUsername();
        this.workNumber=user.getWorkNumber();
        this.id=user.getId();


        Set<Permission> menus=new HashSet<>();  //菜单 1
        Set<String> points=new HashSet<>(); //按钮 2
        Set<String> apis=new HashSet<>();   //api 3

        for (Permission perm : list) {
            String code=perm.getCode();
            if (perm.getType()==1){
                menus.add(perm);
            }else if (perm.getType()==2){
                points.add(code);
            }else {
                apis.add(code);
            }
        }

        this.roles.put("menus",menus);
        this.roles.put("points",points);
        this.roles.put("apis",apis);

    }


    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
