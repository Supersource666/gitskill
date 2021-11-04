package com.my.dynamic.entity.subway;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class SuspensionPara {






    //一系悬挂纵向刚度 / 阻尼
    private String kpx;
    private String cpx;
    //一系悬挂横向刚度 / 阻尼
    private String kpy;
    private String cpy;
    //一系悬挂垂向刚度 / 阻尼
    private String kpz;
    private String cpz;


    //二系悬挂纵向刚度 / 阻尼
    private String ksx;
    private String csx;
    //二系悬挂横向刚度 / 阻尼
    private String ksy;
    private String csy;
    //二系悬挂垂向刚度 / 阻尼
    private String ksz;
    private String csz;

    //拉杆纵向刚度 / 阻尼
    private String kpnx;
    private String cpnx;

    //二系横向止挡
    private String detaSy1;
    private String ksyS1;
    private String detaSy2;
    private String ksyS2;


}
