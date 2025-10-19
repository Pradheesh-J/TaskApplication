package org.example;

import org.example.exception.OutOfRangeException;
import org.example.model.Task;
import org.example.service.TaskService;
import org.example.util.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        ConsoleUI.menu();
        ConsoleUI.pauseEffect();
runningApp:        while(true) {
            int option;
            try {
                option = ConsoleUI.selectOption();
            }catch(NumberFormatException e){
                System.out.println("Enter valid Number(1|2|3|4)");
                continue;
            }catch(OutOfRangeException e){
                System.out.println("Enter within the range");
                continue;
            }
            switch (option) {
                case 1:
                    boolean getInput = true;
                    while (getInput) {
                        Task task = taskService.getInput();
                        taskService.addTask(task);
                        ConsoleUI.printLine();
                        getInput = taskService.askToContinue();
                    }
                    break;
                case 2:
                    taskService.printAllTasks();
                    ConsoleUI.printLine();
                    break;
                case 3:
                    taskService.printAllPriorityTasks();
                    ConsoleUI.printLine();
                    break;
                case 4:
                    System.out.println("Thanks for using the Task Management App");
                    break runningApp;
            }
        }



    }
}