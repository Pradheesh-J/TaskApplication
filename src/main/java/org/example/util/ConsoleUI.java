package org.example.util;

import org.example.exception.OutOfRangeException;

import java.util.Scanner;

public class ConsoleUI {
    private static final Scanner in = new Scanner(System.in);
    public static void menu()  {
        System.out.println("Welcome to Task Management App");
        pauseEffect();
        System.out.println("Menu");
        System.out.println("1. Add new task");
        System.out.println("2. View current tasks");
        System.out.println("3. View tasks based on priority");
        System.out.println("4. End Application");
        printLine();
    }
    public static int selectOption()throws NumberFormatException,OutOfRangeException{

        System.out.println("Select option(1|2|3|4): ");
        int menuOption = Integer.parseInt(in.nextLine().trim());
        if(menuOption<1 || menuOption>4) throw new OutOfRangeException("Out of Range Entered: "+menuOption);
        return menuOption;
    }
    public static void pauseEffect(){
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println("App has been stuck and you are done for");
        }
    }
    public static void printLine(){
        System.out.println("------------------------------------------------------");
    }
}
