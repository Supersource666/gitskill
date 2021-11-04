package com.my.dynamic.Web.dao;

import com.my.dynamic.entity.subway.CalculateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CalculateDataDao extends JpaRepository<CalculateData,String>, JpaSpecificationExecutor<CalculateData> {

    CalculateData findByProjectIdAndUsernameAndFileName(String projectId,String username,String fileName);

    List<CalculateData> getByUsernameAndProjectId(String username, String projectId);

    void deleteByProjectIdAndUsername(String project,String username);
}
