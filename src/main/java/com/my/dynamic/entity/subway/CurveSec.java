package com.my.dynamic.entity.subway;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class CurveSec {

    //曲线里程
    private String mileage;
    //曲线半径
    private String radius;
    //曲线长度
    private String length;
    //过渡曲线长度
    private String transitionLength;
    //超高
    private String superElevation;
    //轨道板样式
    private String type;


}
