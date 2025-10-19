package org.example.service;

import org.example.model.Priority;
import org.example.model.SubTask;
import org.example.model.Task;
import org.example.util.ConsoleUI;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TaskService {
    private final Scanner taskInput = new Scanner(System.in);
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MM yyyy");
    private final Deque<Task> allTasks = new LinkedList<>();
    private final Set<String> allCategories = new HashSet<>();

    public Task getInput() {
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
        System.out.print("Enter task priority(Press Enter to set default(Medium): ");
        String taskPriority = taskInput.nextLine().trim().toUpperCase();
        Priority priority;
        if (taskPriority.isEmpty()) {
            priority = Priority.valueOf("MEDIUM");
        } else {
            priority = Priority.valueOf(taskPriority);
        }
        System.out.println();
        System.out.print("Enter the categories of the task: ");

        String categories = taskInput.nextLine();
        String[] categoriesSplit = categories.split(",");
        Set<String> categorySet = new HashSet<>(List.of(categoriesSplit));
        allCategories.addAll(categorySet);
        System.out.println();
        System.out.println("Do you have any subTasks(y/n): ");
        char yOrN = taskInput.nextLine().charAt(0);
        List<SubTask> subTasks = new ArrayList<>();
        if(yOrN == 'y'){
          subTasks =   inputSubTask();

        }

        Task task = new Task(taskId, taskContent, taskDescription, priority, ld, categorySet,subTasks);
        return task;
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public List<SubTask> inputSubTask(){
        List<SubTask> listOfSubTask = new ArrayList<>();
        int i = 1;
        while(true) {
            System.out.print("Enter SubTask(press enter to close): ");
            String input = taskInput.nextLine();
            if (input.isEmpty()) break;
            SubTask subTask = new SubTask(i,input);
            listOfSubTask.add(subTask);
            i++;

        }
        return listOfSubTask;
    }

    public boolean askToContinue() {
        System.out.println("Do you want to add another task(y/n): ");
        char yOrN = taskInput.nextLine().charAt(0);
        return yOrN == 'y';
    }

    public void printAllTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("No tasks added yet");
            return;
        }
        for (Task task : allTasks) {
            System.out.println(task);
        }
    }

    public void printAllPriorityTasks() {
        if (allTasks.isEmpty()) {
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

    public void printHighPriorityTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("No tasks added yet");
        }
        boolean printed = false;
        for (Task task : allTasks) {
            if (task.getTaskPriority() == Priority.HIGH) {
                System.out.println(task);
                printed = true;
            }
        }
        if (!printed) {
            System.out.println("No high priority task available");
        }


    }

    public void printMediumPriorityTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("No tasks added yet");
        }
        boolean printed = false;
        for (Task task : allTasks) {
            if (task.getTaskPriority() == Priority.MEDIUM) {
                System.out.println(task);
                printed = true;
            }
        }
        if (!printed) {
            System.out.println("No medium priority task available");
        }


    }

    public void printLowPriorityTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("No tasks added yet");
        }
        boolean printed = false;
        for (Task task : allTasks) {
            if (task.getTaskPriority() == Priority.LOW) {
                System.out.println(task);
                printed = true;
            }
        }
        if (!printed) {
            System.out.println("No low priority task available");
        }


    }

    public void printAllCategoryTasks(){
        if(allCategories.isEmpty()){
            System.out.println("There are no categories present at the moment");
        }
        for(String category : allCategories){
            System.out.println("--------------------"+category+"----------------------");
            printSpecificCategory(category);
        }
    }

    public void printSpecificCategory(String category){
        boolean printed = false;
        for(Task task : allTasks){
            if(task.getCategories().contains(category)){
                System.out.println(task);
                printed = true;
            }
        }
        if(!printed){
            System.out.println("No tasks under "+ category+" category");
        }
    }

    public void convertToFile(){
        String filePath = "D:\\Cognizant\\output.txt";
        String content = getAsString();
        try(FileWriter fileWriter = new FileWriter(filePath)){
            fileWriter.write(content);
        }catch(IOException e){
            System.out.println("File problem");
        }

    }

    public String getAsString(){
        if (allTasks.isEmpty()) {
            System.out.println("No tasks added yet");
            return "";
        }
        StringBuilder content = new StringBuilder();
        for (Task task : allTasks) {
            content.append(task.toString());
            content.append(System.lineSeparator());
        }
        return content.toString();
    }


}
