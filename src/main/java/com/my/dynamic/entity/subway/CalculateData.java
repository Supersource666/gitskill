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
@Table(name = "calculate_data")
public class CalculateData implements Serializable {

    private static final long serialVersionUID = 1183654998800898208L;

    @Id
    private String id;

    private String username;

    private String projectId;

    private byte[] fileBytes;

    private String fileName;
}
