package com.my.dynamic.Web.service.impl;


import com.my.dynamic.Web.dao.CalculateDataDao;
import com.my.dynamic.Web.dao.DynamicDataDao;
import com.my.dynamic.Web.dao.ProjectDao;
import com.my.dynamic.Web.service.ProjectService;
import com.my.dynamic.entity.project.Project;
import com.my.dynamic.entity.project.ProjectString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("projectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    @Qualifier("projectDao")
    private ProjectDao projectDao;

    @Autowired
    @Qualifier("dynamicDataDao")
    private DynamicDataDao dynamicDataDao;

    @Autowired
    @Qualifier("calculateDataDao")
    private CalculateDataDao calculateDataDao;

    @Override
    public List<ProjectString> getAllProject(String id) {

        List<Project> fileProject = projectDao.getProjectByUserId(id);
        List<ProjectString> strProject=new ArrayList<>();
        for (Project project : fileProject) {
            ProjectString ps=new ProjectString();
            ps.setDate(project.getDate());
            ps.setUserId(project.getUserId());
            ps.setName(project.getName());
            ps.setId(project.getId()+"");
            strProject.add(ps);
        }
        return  strProject;
    }

    @Override
    public void addFileProject(String description, String id) throws Exception{
        Date date =new Date(System.currentTimeMillis());
        Project project=new Project();
        project.setDate(date);
        project.setName(description);
        project.setUserId(id);

        projectDao.save(project);

    }

    @Override
    public void update(String userId, long projectId, String description) {

        try {
            Project project=new Project();
            project.setId(projectId);
            project.setName(description);
            project.setUserId(userId);
            project.setDate(new Date(System.currentTimeMillis()));
            projectDao.save(project);
        } catch (Exception e) {
           throw e;
        }

    }

    @Override
    public void deleteProject(long projectId, String username, String id) {
        try {
            //1.删除file_project
            projectDao.deleteByIdAndUserId(projectId,id);
            //2.删除dynamic_data
            dynamicDataDao.deleteByProjectIdAndUsername(""+(int)projectId,username);
            //3.删除calculate_data
            calculateDataDao.deleteByProjectIdAndUsername(""+(int)projectId,username);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
