package com.my.dynamic.Web.controller;



import com.my.dynamic.Web.service.UserService;
import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import com.my.dynamic.entity.shiro.ProfileResult;
import com.my.dynamic.entity.shiro.User;
import com.my.dynamic.entity.shiro.UserResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//1.解决跨域
//@CrossOrigin(allowedHeaders = {"Authorization","authorization"},origins = "http://192.168.1.117:3000")
//2.声明restController
@RestController
//3.设置Api接口
@Api("登录")
//4.设置父路径
@RequestMapping("/api/sys")
public class UserController extends BaseController{


    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {

        //登录账号
        String workNumber = loginMap.get("workNumber");
        //登录密码
        String password = loginMap.get("password");

        try {
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码(密码，盐，加密次数)
            password = new Md5Hash(password, workNumber, 3).toString();//
            UsernamePasswordToken upToken = new UsernamePasswordToken(workNumber, password);
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成认证
            subject.login(upToken);
            //4.获取sessionID
            String sessionId = (String) subject.getSession().getId();
            //构造返回结果，这里需要返回sessionID到前端
            return new Result(ResultCode.SUCCESS, sessionId);
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    /**
     * 1.获取用户id
     * 2.根据用户id查询用户
     * 3.构建返回对象
     * 4.响应
     *
     * @return
     */
    @ApiOperation(value = "用户登录后获取信息")
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public Result profile() {
        /**
         * 从请求头信息中获取token数据 jwt
         * 1.获取请求头信息：名称=Authorization
         * 2.替换Bearer+空格
         * 3.解析token
         * 4.获取clamis
         */
        //1.获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //2.subject获取所有的安全数据
        PrincipalCollection principals = subject.getPrincipals();
        //3.获取安全数据
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
        //返回结果
        return new Result(ResultCode.SUCCESS, result);

    }

    /**
     * 保存
     */
    @ApiOperation(value = "用户保存")
    @RequiresPermissions("user-manager")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result save(@RequestBody User user) {

        //1.设置保存的企业id
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);

        //2.调用userService完成保存
        userService.save(user);

        //返回构造结果
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "根据id删除用户")
    @RequiresPermissions("user-manager")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {

        //1.删除用户
        userService.deleteById(id);
        //2.返回数据结果
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID查询user
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "根据id查询用户")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id){

        //根据id查询用户
        User user=userService.findById(id);
        //返回了角色ids
        UserResult userResult=new UserResult(user);

        return new Result(ResultCode.SUCCESS,userResult);

    }

    /**
     * 修改User
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "/user/update",method = RequestMethod.PUT)
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业的部门列表
     * 指定企业id
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "查询用户列表")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result findAll(int page, int size){

        Map map=new HashMap();
        //1.获取当前的企业id
        map.put("companyId", companyId);
        //2.完成查询
        Page<User>  pageUser=userService.findAll(map, page, size);
        //3.构造返回结果

        return new Result(ResultCode.SUCCESS,pageUser);
    }

    /**
     * 分配角色
     */
    @RequiresPermissions("user-manager")
    @ApiOperation(value = "分配用户角色")
    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result save(String id,@RequestBody List<String> roleIds) {
        //1.获取被分配的用户id
        String userId = id;

        //2.完成角色分配
        userService.assignRoles(userId,roleIds);

        return new Result(ResultCode.SUCCESS);
    }



    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {

        //判断当前用户名称是否重复
        User findUser=userService.findByUserName(user.getUsername());
        if (user!=null){
            return new Result(ResultCode.FAIL,"用户名重复");
        }

        //1.调用userService完成保存
        userService.save(user);

        //返回构造结果
        return new Result(ResultCode.SUCCESS);
    }

}
