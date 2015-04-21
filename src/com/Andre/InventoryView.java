package com.Andre;

/**
 * Created by Andre on 4/20/2015.
 */
/** @author Clara MCTC Java Programming Class */

import java.util.LinkedList;
import java.util.Scanner;

public class InventoryView {

    private final int QUIT = 5;   //Modify if you add more menu items.
    //TODO Can you think of a more robust way of handling menu options which would be easy to modify with a varying number of menu choices?

    InventoryController myController;
    Scanner s;

    InventoryView(InventoryController c) {
        myController = c;
        s = new Scanner(System.in);
    }


    public void launchUI() {
        //This is a text-based UI. Probably a GUI in a real program

        while (true) {

            int userChoice = displayMenuGetUserChoice();
            if (userChoice == QUIT ) {
                break;
            }

            doTask(userChoice);
        }

    }

    private void doTask(int userChoice) {

        switch (userChoice) {

            case 1:  {
                displayAllInventory();
                break;
            }
            case 2: {
                addNewLaptop();
                break;
            }
            case 3 : {
                reassignLaptop();
                break;
            }
            case 4 : {
                retireLaptop();
                break;
            }
        }

    }

    private void reassignLaptop() {

        //Ask for laptop ID
        //Fetch laptop info and display for user to confirm this is the correct laptop
        int id;
        boolean change;
        while (true) {
            System.out.println("Enter laptop ID to reassign");
            try {
                id = Integer.parseInt(s.nextLine());
                change = displayLaptopById(id);
                //Check if the user enters a valid id and let him retry if not
                if (change) {
                    System.out.println("Who is the staff member to be reassigned?");
                    String newStaff = s.nextLine();
                    myController.requestUserChange(id, newStaff);
                    break;
                }
                else {
                    System.out.println("Sorry that user is not in the database.");
                }
                //catch if the user tries to enter characters
            } catch (NumberFormatException nf) {
                System.out.println("Enter a number");
            }
        }

    }

    private void retireLaptop(){
        //Ask for laptop ID
        //Fetch laptop info and display for user to confirm this is the correct laptop
        int id;
        boolean change;
        while (true) {
            System.out.println("Enter laptop ID to reassign");
            try {
                id = Integer.parseInt(s.nextLine());
                change = displayLaptopById(id);
                //Check if the user enters a valid id and let him retry if not
                if (change) {
                    myController.requestDeleteById(id);
                    break;
                }
                else {
                    System.out.println("Sorry that user is not in the database.");
                }

            } catch (NumberFormatException nf) {
                System.out.println("Enter a number");
            }
        }
    }


    private void addNewLaptop() {

        //Get data about new laptop from user

        System.out.println("Please enter make of laptop (e.g. Toshiba, Sony) : ");
        String make = s.nextLine();

        System.out.println("Please enter make of laptop (e.g. Macbook, X-123) : ");
        String model = s.nextLine();

        System.out.println("Please enter name of staff member laptop is assigned to : ");
        String staff = s.nextLine();

        Laptop l = new Laptop(make, model, staff);

        myController.requestAddLaptop(l);

        System.out.println("New laptop added to database");


    }


    private void displayAllInventory() {

        LinkedList<Laptop> allLaptops = myController.requestAllInventory();
        if (allLaptops.isEmpty()) {
            System.out.println("No laptops found in database");
        } else {
            System.out.println("All laptops in the database:");
            for (Laptop l : allLaptops) {
                System.out.println(l);   //Call the toString method in Laptop
            }
        }
    }


    private boolean displayLaptopById(int id) {
        Laptop l = myController.requestLaptopById(id);
        if (l == null) {
            System.out.println("Laptop " + id + " not found");
            return false;
        } else {
            System.out.println(l);   //Call the toString method in Laptop
            return true;
        }
    }

    private int displayMenuGetUserChoice() {

        boolean inputOK = false;
        int userChoice = -1;

        while (!inputOK) {

            System.out.println("1. View all inventory");
            System.out.println("2. Add a new laptop");
            System.out.println("3. Reassign a laptop to another staff member");
            System.out.println("4. To be added - retire a laptop");
            System.out.println(QUIT + ". Quit program");

            System.out.println();
            System.out.println("Please enter your selection");

            String userChoiceStr = s.nextLine();
            try {
                userChoice = Integer.parseInt(userChoiceStr);
                if (userChoice < 1  ||  userChoice > 5) {
                    System.out.println("Please enter a number between 1 and 5");
                    continue;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a number");
                continue;
            }
            inputOK = true;

        }

        return userChoice;

    }
}