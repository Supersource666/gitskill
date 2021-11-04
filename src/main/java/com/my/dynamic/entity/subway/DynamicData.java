package com.my.dynamic.entity.subway;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "dynamic_data")
public class DynamicData implements Serializable {

    private static final long serialVersionUID = 6342309898414007105L;

    @Id
    private String id;

    private String username;

    private String data;

    private String message;

    private String projectId;




}
