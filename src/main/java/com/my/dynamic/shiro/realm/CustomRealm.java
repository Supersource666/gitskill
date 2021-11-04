package com.my.dynamic.shiro.realm;



import com.my.dynamic.Web.service.PermissionService;
import com.my.dynamic.Web.service.UserService;
import com.my.dynamic.entity.shiro.Permission;
import com.my.dynamic.entity.shiro.ProfileResult;
import com.my.dynamic.entity.shiro.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.shiro.realm.AuthorizingRealm;


public class CustomRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    public void setName(String name) {
        super.setName("CustomRealm");
    }

    /**
     * 授权方法
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //1.获取安全数据
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
        //2.获取权限信息
        Set<String> pointsPerms = (Set<String>) result.getRoles().get("points");
        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(pointsPerms);

        return info;
    }

    /**
     * 认证方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户的工作工作号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String workNumber = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());
        //2.根据用户工作号查询用户
        User user = userService.findByWorkNumber(workNumber);
        //3.判断用户是否存在
        if (user != null && user.getPassword().equals(password)) {
            //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
            ProfileResult result = null;

            /**
             * 这里通过 result = new ProfileResult(user);直接返回也可以
             */

            if ("user".equals(user.getLevel())) {
                result = new ProfileResult(user);
            } else {
                Map<String, Object> map = new HashMap<>();
                if ("coAdmin".equals(user.getLevel())) {
                    map.put("enVisible", "1");
                }
                List<Permission> list = permissionService.findAll(map);
                result = new ProfileResult(user, list);
            }
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result, user.getPassword(), this.getName());
            return info;
        }

        //返回null,会抛出异常，标识用户名和密码不匹配，通过过滤器跳转"/autherror?code=1"
        return null;
    }
}
