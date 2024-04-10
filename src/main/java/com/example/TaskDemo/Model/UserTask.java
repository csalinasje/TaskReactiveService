package com.example.TaskDemo.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@EntityScan
@Table(name = "usertask")
public class UserTask {
    @Id
    @Column
    private String idUser;
    private String name;
    private String phone;
}
