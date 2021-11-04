package com.my.dynamic.config;


import com.my.dynamic.shiro.realm.CustomRealm;
import com.my.dynamic.shiro.session.CustomSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1.创建realm
    @Bean
    public CustomRealm getRealm() {
        return new CustomRealm();
    }

    //2.创建安全管理器
    @Bean
    public SecurityManager getSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);

        //将自定义的会话管理器注册到安全管理器
        securityManager.setSessionManager(sessionManager());

        //将自定义redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(cacheManager());

        return securityManager;
    }

    //3.配置shiro的过滤器工厂

    /**
     * 在web程序中，shiro进行权限控制全部是通过一组过滤器集合进行控制
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //1.创建过滤器工厂
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //3.通用配置(跳转登录页面，未授权跳转的页面)
        factoryBean.setLoginUrl("/autherror?code=1");//如果未登录，跳转未登录的页面
        factoryBean.setUnauthorizedUrl("/autherror?code=2");//如果未授权，跳转到未授权的页面
        //4.设置过滤器集合（ 过滤链定义，从上向下顺序执行，一般将/**放在最为下边）使用LinkedHashMap
        Map<String, String> filterMap = new LinkedHashMap<>();
        /**
         * "anon":无参，开放权限，可以理解为匿名用户或游客
         * "authc":无参，需要认证
         * "perms[User]":参数可写多个，表示需要某个或某些权限才能通过，多个参数时写 perms[“user,admin”]，
         * 当有多个参数时必须每个参数都通过才算通过
         */
        //设置登录时过滤器为anon，游客访问
        //测试swagger使用
        filterMap.put("/swagger-ui.html","anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/v2/api-docs/**","anon");
        filterMap.put("/webjars/springfox-swagger-ui/**","anon");
        //登录页面开放
        filterMap.put("/api/sys/login", "anon");
        filterMap.put("/api/sys/register","anon");
        //公共错误页面也需要开放
        filterMap.put("/autherror", "anon");
        //其他页面需要登录后才能访问，设置为authc
        filterMap.put("/**", "authc");
        //权限通过在方法上注解，进行授权
        //注入过滤器集合
        factoryBean.setFilterChainDefinitionMap(filterMap);

        //返回数据结果
        return factoryBean;

    }

    /**
     * @return
     */
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //禁用cookie与url重写（可以不禁用）
        sessionManager.setSessionIdCookieEnabled(false);
    /*
        //警用url重写 url;jsessionid=id(不美观)
        sessionManager.setSessionIdUrlRewritingEnabled(false);
    */
        return sessionManager;

    }

    /**
     * sessionDao
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO=new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;



    /**
     * 设置redis的控制器，操作redis
     * @return
     */
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }

    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    //开启对shiro注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
