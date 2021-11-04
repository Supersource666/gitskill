package com.my.dynamic;


import com.my.dynamic.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(Application.class,args);
        String serverPort=context.getEnvironment().getProperty("server.port");
        log.info("start at http://localhost:"+serverPort);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    /**
     * 解决NO session（延迟加载）的问题
     * jpa多对多使用拦截器会造No session
     *
     * @return
     */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }

}
