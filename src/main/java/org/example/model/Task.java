package org.example.model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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
    private Set<String> categories;

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String categoryList = categories != null && !categories.isEmpty()
                ? String.join(", ", categories)
                : "None";

        return "Task ID: " + taskId +
                " | Task Name: " + taskName +
                " | Description: " + taskDescription +
                " | Priority: " + taskPriority +
                " | Categories: " + categoryList +
                " | Due Date: " + (dueDate != null ? dueDate.format(df) : "N/A");
    }


}
