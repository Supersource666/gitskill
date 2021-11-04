package com.my.dynamic.Web.controller;



import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ErrorController {


    //公共错误页面跳转
    @RequestMapping("/autherror")
    public Result autherror(int code){
//        System.out.println(code);
        return code==1?new Result(ResultCode.UNAUTHENTICATED):new Result(ResultCode.UNAUTHORISE);

    }


}
