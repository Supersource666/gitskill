package com.my.dynamic.Web.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import com.power.common.util.DirUtil;
import com.power.common.util.FileUtil;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Paths;
import java.util.UUID;



public class CustomInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }
}
