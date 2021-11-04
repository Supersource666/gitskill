package com.my.dynamic.entity.subway;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TypeVehicle {

    // 动车1拖车0
    private String type;

    // 牵引制动变化区段个数
    private String tractionSecNum;

    // 牵引制动变化区间的时间节点 0 3 5 7
    private String tractionTimeSec;

    // 牵引制动状态（0 惰行，1牵引，2制动）1 0 2
    private String tractionTypeSec;


}
