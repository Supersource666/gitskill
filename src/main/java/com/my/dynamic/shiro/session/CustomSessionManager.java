package com.my.dynamic.shiro.session;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;



public class CustomSessionManager extends DefaultWebSessionManager {


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        //1.获取请求头Authorization中的数据
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        //如果没有生成新的sessionId
        if (StringUtils.isEmpty(id)) {
            return super.getSessionId(request, response);
        } else {
            //获取请求头中的信息：Bearer sessionId
            id = id.replaceAll("Bearer ", "");
            //返回sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return id;
        }
    }
}
