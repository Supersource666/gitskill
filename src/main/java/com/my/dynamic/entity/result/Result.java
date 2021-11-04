package com.my.dynamic.entity.result;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 *  数据响应对象
 *      {
 *          success:是否成功
 *          code：返回码
 *          message：返回信息
 *          //返回数据
 *          data：{
 *
 *          }
 *      }
 */
@Data
@NoArgsConstructor
public class Result<T> {

    private boolean success; // 是否成功
    private Integer code; // 返回码
    private String message; //返回信息
    private T data; //返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code,T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data=data;
    }

    public Result(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }


}
