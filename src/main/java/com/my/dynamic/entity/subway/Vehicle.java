package com.my.dynamic.entity.subway;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Vehicle {



    //车辆编组数量
    private String vehicleCount;
    //构架个数
    private String bogieCount;
    //轮对个数
    private String wheelsetCount;


    //车轮半径
    private String rw;
    //车体质量
    private String mc;
    //构架质量
    private String mt;
    //轮对质量
    private String mw;
    //轮对惯量
    private String iwx;
    private String iwy;
    private String iwz;
    //构架惯量
    private String itx;
    private String ity;
    private String itz;
    //车体惯量
    private String icx;
    private String icy;
    private String icz;
    //电机、齿轮箱、电机转子转动惯量
    private String mm;
    private String jm;
    private String mgb;
    private String jgb;
    private String jrot;


    //车长
    private String carLength;
    //轴距
    private String lt;
    //转向架中心距
    private String lc;
    //一系悬挂横向跨距
    private String dw;
    //二系悬挂横向跨距
    private String ds;
    //一系悬挂点 - 构架质心高度
    private String htw;
    //轴箱拉杆 - 构架质心高度
    private String htbn;
    //构架质心 - 二系悬挂构架点高度
    private String hbt;
    //二系悬挂构架点 - 车体质心高度
    private String hcb;
    //车体质心-二系横向止挡高度
    private String hcbst;
    //构架质心-二系横向止挡高度
    private String hbtst;





    //轮轨型面选择
    private String tread;
    //轮轨接触方法求解选择
    private String contactMethod;


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
    //一系垂向减振器刚度 / 阻尼
    private String kpz1;
    private String damperPz1;
    private String detaVPz1;
    private String kpz2;
    private String damperPz2;
    private String detaVPz2;
    //二系垂向减振器刚度 / 阻尼
    private String ksz1;
    private String damperSz1;
    private String detaVSz1;
    private String ksz2;
    private String damperSz2;
    private String detaVSz2;
    //二系横向减振器刚度 / 阻尼
    private String ksy1;
    private String damperSy1;
    private String detaVSy1;
    private String ksy2;
    private String damperSy2;
    private String detaVSy2;

}
