package com.my.dynamic.Web.controller;


import com.my.dynamic.Web.service.ProjectService;
import com.my.dynamic.entity.project.ProjectString;
import com.my.dynamic.entity.result.Result;
import com.my.dynamic.entity.result.ResultCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
@CrossOrigin
@RequestMapping("/api/pro")
public class FileProjectController extends BaseController {

    @Autowired
    @Qualifier("projectServiceImpl")
    private ProjectService projectService;

    @GetMapping("/get")
    @ApiOperation(value = "获得当前用户的所有文件夹列表")
    public Result getProject() {
        List<ProjectString> data = projectService.getAllProject(id);
        return new Result(ResultCode.SUCCESS,data);
    }

    @PostMapping("/add")
    @ApiOperation(value = "增加新的文件夹")
    public Result addProject(String description) {
        try {
            projectService.addFileProject(description, id);
        } catch (Exception e) {
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("/update/{projectId}/{description}")
    @ApiOperation("修改当前文件夹的名称")
    public Result updateProject(@ApiParam(value = "当前project的id") @PathVariable(value = "projectId") String projectId,
                                @ApiParam(value = "新文件夹描述") @PathVariable(value = "description") String description) {
        try {
            projectService.update(id, Long.parseLong(projectId), description);
        } catch (Exception e) {

            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除所选择的文件夹")
    @Transactional
    public Result deleteProject(@ApiParam(value = "提供需要删除project的id") @PathVariable(value = "id") String projectId) {

        try {
            //id是用户的主键

            projectService.deleteProject(Long.parseLong(projectId),username,id);
        } catch (Exception e) {
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);

    }


}
