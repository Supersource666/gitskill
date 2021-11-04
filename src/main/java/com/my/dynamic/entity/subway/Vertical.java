package com.my.dynamic.entity.subway;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Vertical {

    //竖曲线里程
    private String mileage;
    //竖曲线坡道长度
    private String length;
    //竖曲线缓和曲线半径
    private String radius;
    //竖曲线坡度
    private String gradient;

    public String toStr() {
        System.out.println(1);
        System.out.println(JSON.toJSONString(this));
        System.out.println(2);
        return "1";
    }
}
