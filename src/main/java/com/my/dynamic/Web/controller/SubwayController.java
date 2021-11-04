package com.my.dynamic.Web.controller;


import com.my.dynamic.Web.service.SubwayService;
import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.entity.subway.CalculateData;
import com.my.dynamic.entity.subway.CurveSec;
import com.my.dynamic.entity.subway.DynamicData;
import com.my.dynamic.entity.subway.Vertical;
import com.my.dynamic.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/api/subway")
@Api("地铁车辆模块")
@RestController
public class SubwayController extends BaseController {

    @Autowired
    private SubwayService subwayService;



//    @ApiOperation(value = "添加Vehicle参数", notes = "  vehicleCount,bogieCount,wheelsetCount,rw,mc,mt,mw,iwx\n" +
//            "        iwy,iwz,itx,ity,itz,icx,icy,icz,mm,jm,mgb,jgb,jrot,carLength\n" +
//            "        lt,lc,dw,ds,htw,htbn,hbt,hcb,hcbst,hbtst")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/vehicle")
//    public Result setVehiclePara(@RequestBody Map<String, Object> map, String message,String projectId) throws Exception {
//
//
//        subwayService.addVehiclePara(map, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//
//    }
//
//
//    @ApiOperation(value = "添加Control参数", notes = "ot,initialVelocity,beginDis,totalTime,totalDis")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/control")
//    public Result setControlPara(@RequestBody Map<String, Object> map, String message,String projectId) throws Exception {
//
//        subwayService.addControlPara(map, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//    }
//
//
//    @ApiOperation(value = "添加Curve参数", notes = "添加cross段参数等")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/cross")
//    public Result setCurvePara(@RequestBody CurveSec[] curveSecs, String message,String projectId) throws Exception {
//
//        subwayService.addCrossPara(curveSecs, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//
//    }
//
//    @ApiOperation(value = "添加竖曲线参数", notes = "添加vertial段参数等")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/vertial")
//    public Result setCurvePara(@RequestBody Vertial[] vertials, String message,String projectId) throws Exception {
//
//        subwayService.addVertialPara(vertials, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//
//    }
//
//
//    @ApiOperation(value = "添加Absorber参数", notes = "damperPz1,detaVPz1,damperPz2,detaVPz2" +
//            "damperSz1,detaVSz1,damperSz2,detaVSz2,damperSy1,detaVSy1,damperSy2,detaVSy2")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/absorber")
//    public Result setAbsorberPara(@RequestBody Map<String, Object> map, String message,String projectId) throws Exception {
//
//        subwayService.addAbsorberPara(map, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//    }
//
//
//    @ApiOperation(value = "添加Suspension参数", notes = "kpx,cpx,kpy,cpy,kpz,cpz," +
//            "ksx,csx,ksy,csy,ksz,csz,kpnx,cpnx,detaSy1,ksyS1,detaSy2,ksyS2")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/suspension")
//    public Result setSuspensionPara(@RequestBody Map<String, Object> map, String message,String projectId) throws Exception {
//
//        subwayService.addSuspensionPara(map, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//    }
//
//    @ApiOperation(value = "添加typeVehicle参数", notes = "添加type，tractionSecNum，tractionTimeSec，tractionTypeSec参数等")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "message", value = "参数的板块", dataType = "String", paramType = "query"),
//    })
//    @ResponseBody
//    @PostMapping("/typeVehicle")
//    public Result setTypeVehiclePara(@RequestBody Map<String, Object> map , String message,String projectId) throws Exception {
//
//        subwayService.addTypeVehiclePara(map, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//    }



    @ApiOperation(value = "计算")
    @ResponseBody
    @PostMapping("/calculate")
    public Result calculate(String projectId) throws Exception {

        //todo:调用算法计算结果
        subwayService.calculate();
        //保存计算结果
        subwayService.saveResult(username, projectId);
        return new Result(ResultCode.SUCCESS);
    }

//    @ApiOperation(value = "设置轮轨黏着参数",notes = "numFri,tableFri,ka,ks,ap,bp,adhSecNum,adhSecArrays,adhTableArrays")
//    @ResponseBody
//    @PostMapping("/adhesionPara")
//    public Result setAdhesionPara(@RequestBody Map<String, Object> map, String message, String projectId) throws CommonException {
//        //添加轮轨黏着参数
//        subwayService.addAdhesionPara(map, username, message,projectId);
//        return new Result(ResultCode.SUCCESS);
//    }

    @ApiOperation(value = "传输仿真计算的数据结果")
    @ResponseBody
    @PostMapping("/transform")
    public Result transform(String projectId,int counts) throws Exception {


        List<CalculateData> data = subwayService.getResult(username, projectId,counts);
        return new Result(ResultCode.SUCCESS, data);

    }



    @ApiOperation(value = "get inputPara")
    @ResponseBody
    @GetMapping("/getPara")
    public Result getPara(String projectId,String message) throws Exception {

        DynamicData data = subwayService.getInputPara(username, projectId,message);
        return new Result(ResultCode.SUCCESS, data.getData());
    }


    @ApiOperation(value = "上传所有的数据")
    @ResponseBody
    @PostMapping("/upload")
    public Result upLoad(@RequestBody Map<String,Object> map, String projectId) throws CommonException {
        //1.添加 vehicle
        subwayService.addVehiclePara((Map<String, Object>)map.get("vehicle"), username, "vehicle",projectId);
        //2.添加 control
        subwayService.addControlPara((Map<String, Object>)map.get("control"), username, "control",projectId);
        //3.添加 cross
        subwayService.addCrossPara((ArrayList<CurveSec>)map.get("cross"), username, "cross",projectId);
        //4.添加 vertical
        subwayService.addVertialPara((ArrayList<Vertical>)map.get("vertical"), username, "vertical",projectId);
        //5.添加 absorber
        subwayService.addAbsorberPara((Map<String, Object>) map.get("absorber"), username, "absorber",projectId);
        //6.添加 suspension
        subwayService.addSuspensionPara((Map<String, Object>) map.get("suspension"), username, "suspension",projectId);
        //7.添加 typeVehicle
        subwayService.addTypeVehiclePara((Map<String, Object>) map.get("typeVehicle"), username, "typeVehicle",projectId);
        //8.添加 adhesionPara
        subwayService.addAdhesionPara((Map<String, Object>) map.get("adhesionPara"), username, "adhesionPara",projectId);

        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "得到所有的参数")
    @ResponseBody
    @GetMapping("/getAllPara")
    public Result getAllPara(String projectId) throws Exception{

        Map<String, Object> data= subwayService.getAllPara(username,projectId);
        Result result = new Result(ResultCode.SUCCESS, data);

        return result;
//        return new Result(ResultCode.SUCCESS,data);

    }


}
