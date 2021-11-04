package com.my.dynamic.entity.subway;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class AbsorberPara {

    //一系垂向减振器力与转折点
    private String damperPz1;
    private String detaVPz1;
    private String damperPz2;
    private String detaVPz2;
    //二系垂向减振器刚度 / 阻尼
    private String damperSz1;
    private String detaVSz1;
    private String damperSz2;
    private String detaVSz2;
    //二系横向减振器刚度 / 阻尼
    private String damperSy1;
    private String detaVSy1;
    private String damperSy2;
    private String detaVSy2;


}
