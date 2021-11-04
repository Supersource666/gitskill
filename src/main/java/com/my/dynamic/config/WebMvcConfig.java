package com.my.dynamic.config;


import com.google.common.collect.Lists;
import com.my.dynamic.Web.interceptor.CustomInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    /**
     * 将crh文件名称注册到spring容器，用于创建文件
     * @return
     */
    @Bean
    public CustomInterceptor crhInterceptor(){
        return new CustomInterceptor();
    }

    /**
     * 添加文件拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crhInterceptor()).addPathPatterns("/subway/body");

    }


    /**
     * 添加swagger
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }



//    @Bean(name = "corsFilter")
//    public FilterRegistrationBean corsFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(corsFilterBean());
//        registrationBean.setUrlPatterns(Lists.newArrayList("/*"));
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
}
