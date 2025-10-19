package org.example.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    private int taskId;
    private String taskName;
    private String TaskDescription;
    private LocalDate dueDate;


}
