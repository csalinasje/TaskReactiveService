package com.example.TaskDemo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usertask")
public class UserTask {
    @Id
    private int idUser;
    private String name;
    private String phone;
}
