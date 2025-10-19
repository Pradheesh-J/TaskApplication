package org.example.model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private Priority taskPriority;
    private LocalDate dueDate;

    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Task ID: " + taskId +
                " | Task Name: " + taskName +
                " | Description: " + taskDescription +
                " | Priority: " + taskPriority.toString() +
                " | Due Date: " + (dueDate != null ? dueDate.format(df) : "N/A");
    }

}
