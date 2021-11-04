package com.my.dynamic.Web.controller;




import com.my.dynamic.Web.service.SubwayService;
import com.my.dynamic.entity.shiro.ProfileResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;
    protected String companyName;
    protected String username;
    protected String workNumber;
    //用户的主键
    protected String id;

    @Autowired
    private SubwayService subwayService;


    @ModelAttribute
    public void setResAndReq(HttpServletRequest request, HttpServletResponse response){

        this.request=request;
        this.response=response;

        //从session中获取安全数据
        Subject subject = SecurityUtils.getSubject();
        //subject中获取安全数据
        PrincipalCollection principals = subject.getPrincipals();

        if (principals!=null&&!principals.isEmpty()){

            //获取安全数据
            ProfileResult result = (ProfileResult)principals.getPrimaryPrincipal();
            this.companyId=result.getCompanyId();
            this.companyName=result.getCompany();
            this.username=result.getUserName();
            this.workNumber=result.getWorkNumber();
            this.id=result.getId();
        }

        //设置车辆前置参数输入
        if(workNumber!=null){
            boolean isExistFolder = subwayService.create(workNumber);
            if (isExistFolder){
                //如果文件存在需要返回文件夹中已存的数据
            }
        }

    }





}
