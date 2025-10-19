package org.example.service;

import org.example.model.Task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


public class TaskService {
    private final Scanner taskInput = new Scanner(System.in);
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MM yyyy");
    private Deque<Task> allTasks = new LinkedList<>();

    public Task getInput(){
        int taskId;
        while (true) {
            System.out.print("Enter Task ID: ");
            String input = taskInput.nextLine();

            try {
                taskId = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        System.out.println();
        System.out.print("Enter Task Name: ");
        String taskContent = taskInput.nextLine().trim();
        System.out.println();
        System.out.print("Enter Task Description: ");
        String taskDescription = taskInput.nextLine().trim();
        System.out.print("Enter Due Date(dd MM yyyy): ");
        String dueDate = taskInput.nextLine().trim();

        LocalDate ld = LocalDate.parse(dueDate, df);

        Task task = new Task(taskId, taskContent,taskDescription,ld);
        return task;
    }

    public void addTask(Task task){
        allTasks.add(task);
    }
    public boolean askToContinue(){
        System.out.println("Do you want to add another task(y/n): ");
        char yOrN = taskInput.nextLine().charAt(0);
        return yOrN == 'y';
    }
    public void printAllTasks(){
        if(allTasks.isEmpty()){
            System.out.println("No tasks added yet");
            return;
        }
        for(Task task : allTasks){
            System.out.print("Task ID: "+task.getTaskId());
            System.out.print(" | Task Name: "+task.getTaskName());
            System.out.print(" | Description: "+task.getTaskDescription());
            System.out.print(" | Due Date: "+task.getDueDate().format(df));
            System.out.println();
        }
    }
}
