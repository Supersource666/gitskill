package com.my.dynamic.Web.dao;

import com.my.dynamic.entity.subway.DynamicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DynamicDataDao extends JpaRepository<DynamicData,String> , JpaSpecificationExecutor<DynamicData> {

    DynamicData findByIdAndUsername(String id,String username);

    DynamicData findByUsernameAndMessageAndProjectId(String username,String message,String project);

    void deleteByProjectIdAndUsername(String projectId,String username);

    List<DynamicData> findByUsernameAndProjectId(String username,String project);

}
