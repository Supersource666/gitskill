package com.my.dynamic.Web.controller;

import com.my.dynamic.Web.service.PermissionService;
import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.entity.shiro.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "权限")
@RequestMapping(value = "/api/sys")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 保存
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "保存菜单权限",notes = "description:权限描述 name:权限名称，" +
            "code:权限代码，pid:顶层为0，" +
            "enVisible:可见性，" )
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public Result save(@RequestBody Map<String,Object> map) throws Exception {
        map.put("type",1);
        permissionService.save(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "修改权限")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody Map<String,Object> map) throws Exception {
        //构造id
        map.put("id",id);
        permissionService.update(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "查询权限列表",notes = "pid:父id，enVisible表示可见性（1可见，0不可见），" +
            "type权限信息，其中1为菜单，2为按钮，0可获取全部权限")
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public Result findAll(String pid,String enVisible,String type) {
        Map<String, Object> map=new HashMap<>();
        map.put("pid",pid);
        map.put("enVisible",enVisible);
        map.put("type",type);
        List<Permission> list =  permissionService.findAll(map);
        return new Result(ResultCode.SUCCESS,list);
    }

    /**
     * 根据ID查询
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "根据ID查询权限")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) throws Exception {
        Map map = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS,map);
    }



    /**
     * 根据id删除
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "根据id删除权限")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) throws Exception {
        permissionService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
