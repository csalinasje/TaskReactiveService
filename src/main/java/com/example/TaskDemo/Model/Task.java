package com.example.TaskDemo.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task implements Serializable {
    @Id
    private int id;
    private String title;
    private boolean completed;
    private int userid;

}
