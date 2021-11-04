package com.my.dynamic.Web.controller;


import com.my.dynamic.Web.service.RoleService;
import com.my.dynamic.entity.result.PageResult;
import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.entity.shiro.Role;
import com.my.dynamic.entity.shiro.RoleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("角色")
@CrossOrigin
@RequestMapping(value = "/api/sys")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;

    /**
     * map只传递了菜单与按钮，没有传递api
     * @param
     * @return
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "给角色分配权限，id:角色的id,permIds:权限id")
    @RequestMapping(value = "/role/assignPerm", method = RequestMethod.PUT)
    public Result save(String id,@RequestBody List<String> permIds){

        //1.获取被分配角色的id
        String roleId =id;
        //2.调用service完成权限分配
        roleService.assignPerms(roleId,permIds);

        return new Result(ResultCode.SUCCESS);

    }

    //添加角色
    @ApiOperation(value = "添加角色")
    @RequiresPermissions("user-manager")
    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public Result add(@RequestBody Role role){
        role.setCompanyId(companyId);
        roleService.save(role);
        return new Result(ResultCode.SUCCESS);
    }

    //更新角色
    @ApiOperation(value = "更新角色")
    @RequiresPermissions("user-manager")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id,@RequestBody Role role){
        roleService.update(role);
        return new Result(ResultCode.SUCCESS);

    }

    /**
     * 删除角色
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除角色")
    @RequiresPermissions("user-manager")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        roleService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID获取角色信息
     */
    @ApiOperation(value = "根据ID获取角色信息")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Role role = roleService.findById(id);
        RoleResult roleResult = new RoleResult(role);
        return new Result(ResultCode.SUCCESS,roleResult);
    }


    /**
     * 分页查询角色
     */
    @ApiOperation(value = "分页查询角色")
    @RequiresPermissions("user-manager")
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page,int pageSize) throws Exception {
        Page<Role> searchPage = roleService.findByPage(companyId, page, pageSize);
        PageResult<Role> pr = new PageResult(searchPage.getTotalElements(),searchPage.getContent());
        return new Result(ResultCode.SUCCESS,pr);
    }


    /**
     * 查询全部角色
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询全部角色")
    @RequiresPermissions("user-manager")
    @RequestMapping(value="/role/list" ,method=RequestMethod.GET)
    public Result findAll() throws Exception {
        List<Role> roleList = roleService.findAll(companyId);
        return new Result(ResultCode.SUCCESS,roleList);
    }

}
