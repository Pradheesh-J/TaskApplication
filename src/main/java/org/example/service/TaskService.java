package org.example.service;

import org.example.model.Priority;
import org.example.model.Task;
import org.example.util.ConsoleUI;


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
        System.out.println();
        System.out.print("Enter Due Date(dd MM yyyy): ");
        String dueDate = taskInput.nextLine().trim();
        LocalDate ld = LocalDate.parse(dueDate, df);
        System.out.println();
        System.out.print("Enter task priority(Press Enter to skip): ");
        String taskPriority = taskInput.nextLine().trim().toUpperCase();
        Priority priority;
        if(taskPriority.isEmpty()){
            priority = Priority.valueOf("MEDIUM");
        }else {
            priority = Priority.valueOf(taskPriority);
        }

        Task task = new Task(taskId, taskContent,taskDescription,priority,ld);
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
           System.out.println(task);
        }
    }
    public void printAllPriorityTasks(){
        if(allTasks.isEmpty()){
            System.out.println("No tasks added yet");
            return;
        }
        ConsoleUI.printLine();
        System.out.println("--------------------High Priority----------------------");
        printHighPriorityTasks();

        System.out.println("--------------------Medium Priority---------------------");
        printMediumPriorityTasks();

        System.out.println("--------------------Low Priority----------------------");
        printLowPriorityTasks();


    }

    public void printHighPriorityTasks(){
        if(allTasks.isEmpty()){
        System.out.println("No tasks added yet");
        }
        boolean printed = false;
        for(Task task : allTasks){
            if(task.getTaskPriority()==Priority.HIGH){
                System.out.println(task);
                printed = true;
            }
        }
        if(!printed){
            System.out.println("No high priority task available");
        }


    }

    public void printMediumPriorityTasks(){
        if(allTasks.isEmpty()){
            System.out.println("No tasks added yet");
        }
        boolean printed = false;
        for(Task task : allTasks){
            if(task.getTaskPriority()==Priority.MEDIUM){
                System.out.println(task);
                printed = true;
            }
        }
        if(!printed){
            System.out.println("No medium priority task available");
        }


    }

    public void printLowPriorityTasks(){
        if(allTasks.isEmpty()){
            System.out.println("No tasks added yet");
        }
        boolean printed = false;
        for(Task task : allTasks){
            if(task.getTaskPriority()==Priority.LOW){
                System.out.println(task);
                printed = true;
            }
        }
        if(!printed){
            System.out.println("No low priority task available");
        }


    }
}
