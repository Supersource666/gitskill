package com.my.dynamic.entity.subway;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Control {


    //积分步长
    private String ot;
    //初始速度
    private String initialVelocity;
    //轨道初始长度（预平衡）
    private String beginDis;
    //运行时间（s）
    private String totalTime;
    //运行距离
    private String totalDis;


}
