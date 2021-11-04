package com.my.dynamic.Web.service;


import com.my.dynamic.entity.subway.CalculateData;
import com.my.dynamic.entity.subway.CurveSec;
import com.my.dynamic.entity.subway.DynamicData;
import com.my.dynamic.entity.subway.Vertical;
import com.my.dynamic.exception.CommonException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SubwayService<T> {

    /**
     * 添加算法的输入参数
     * @param map
     * @param username
     * @throws Exception
     */
    void savePara(Map<String, Object> map,String username,String message,String projectId) throws Exception;

//    /**
//     * 从数据库中获取当前算法设置的数据
//     * @param username
//     * @return
//     */
//    T getPara(String username,String id);

    /**
     * 从数据库中获取当前算法设置的数据
     * @param username
     * @return
     */
    String getPara(String username,String id) throws Exception;

    /**
     * 添加车体参数
     * @param map
     * @param username
     * @param message
     * @throws CommonException
     */
    void addVehiclePara(Map<String, Object> map, String username,String message,String projectId) throws CommonException;

    /**
     * 是否创建文件
     * @param workNumber
     * @return
     */
    boolean create(String workNumber);

    /**
     * 添加控制参数
     * @param map
     * @param username
     * @param message
     */
    void addControlPara(Map<String, Object> map, String username, String message,String projectId) throws CommonException;

    /**
     * 添加曲线cross与vertical
     * @param  curveSec
     * @param  username
     * @param  message
     * @throws CommonException
     */
    void addCrossPara(List<CurveSec>  curveSec, String username, String message, String projectId) throws CommonException;


    /**
     * 添加竖曲线参数
     * @param verticals
     * @param username
     * @param message
     * @param projectId
     */
    void addVertialPara(ArrayList<Vertical> verticals, String username, String message, String projectId) throws CommonException;

    /**
     * 添加减振器参数
     * @param map
     * @param username
     * @param message
     * @param projectId
     */
    void addAbsorberPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException;

    /**
     * 添加悬挂参数
     * @param map
     * @param username
     * @param message
     * @param projectId
     */
    void addSuspensionPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException;

    /**
     * 添加编组的车型，动车组1拖车0
     * @param map
     * @param username
     * @param message
     * @param projectId
     */
    void addTypeVehiclePara(Map<String, Object> map, String username, String message, String projectId) throws CommonException;

    /**
     * 保存数据文件值数据库
     * @param username
     * @param projectId
     */
    void saveResult(String username, String projectId) throws Exception;

    /**
     * 从数据库中获取结果文件
     * @param username
     * @param projectId
     * @param counts
     */
    List<CalculateData> getResult(String username, String projectId, int counts);

    /**
     * 仿真计算
     */
    void calculate() throws IOException;

    /**
     * get InputPara
     * @param username
     * @param projectId
     * @param message
     * @return
     */
    DynamicData getInputPara(String username, String projectId, String message);


    /**
     * 增加轮轨黏着参数
     * @param map
     * @param username
     * @param message
     * @param projectId
     */
    void addAdhesionPara(Map<String, Object> map, String username, String message, String projectId) throws CommonException;

    /**
     * 获得所有的参数
     *
     * @param username
     * @param projectId
     * @return
     */
    Map<String, Object> getAllPara(String username, String projectId);

}
