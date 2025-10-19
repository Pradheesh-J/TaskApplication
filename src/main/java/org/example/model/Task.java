package org.example.model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private List<SubTask> subTasks;

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String categoryList = categories != null && !categories.isEmpty()
                ? String.join(", ", categories)
                : "None";

        String subTaskList = subTasks != null && !subTasks.isEmpty()
                ? subTasks.stream()
                .map(SubTask::toString)
                .collect(Collectors.joining(" | "))
                : "None";

        return "Task ID: " + taskId +
                " | Task Name: " + taskName +
                " | Description: " + taskDescription +
                " | Priority: " + taskPriority +
                " | Categories: " + categoryList +
                " | Due Date: " + (dueDate != null ? dueDate.format(df) : "N/A") +
                " | SubTasks: " + subTaskList;
    }



}
