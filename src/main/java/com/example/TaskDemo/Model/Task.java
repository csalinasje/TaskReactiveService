package com.example.TaskDemo.Model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Getter
@Setter
@EntityScan
@Table(name = "task")
public class Task implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String title;
    @Column
    private boolean completed;
    @Column
    private long userId;

}
