package com.my.dynamic.Web.dao;

import com.my.dynamic.entity.project.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project, String>, JpaSpecificationExecutor<Project> {


    List<Project> getProjectByUserId(String userId);
    void deleteByIdAndUserId(long id,String usedId);

}
