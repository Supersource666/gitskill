package com.my.dynamic.entity.subway;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class AdhesionPara {

    //黏着条件的个数
    private String numFri;
    //摩擦系数
    private String tableFri;

    private String ka;

    private String ks;

    private String ap;

    private String bp;

    //黏着区段的个数
    private String adhSecNum;
    //黏着区段数组
    private String adhSecArrays;
    //黏着区间所选择黏着条件
    private String adhTableArrays;

    //黏着状态
    private String status;







}
