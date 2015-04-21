package com.Andre;

/**
 * Created by Andre on 4/20/2015.
 */
/** @author Clara MCTC Java Programming Class */

import java.util.LinkedList;
import java.util.Scanner;

public class InventoryView {

    private final int QUIT = 7;   //Modify if you add more menu items.
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
            //TODO: find out why it jumps afterwards automatically to 6
            case 5 : {
                addNewPhone();
            }
            case 6 : {
                retireCellphone();
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

    private void retireCellphone(){
        //Ask for laptop ID
        //Fetch laptop info and display for user to confirm this is the correct laptop
        int id;
        boolean change;
        while (true) {
            System.out.println("Enter cellphone ID to retire");
            try {
                id = Integer.parseInt(s.nextLine());
                change = displayCellphoneById(id);
                //Check if the user enters a valid id and let him retry if not
                if (change) {
                    myController.requestDeletePhoneById(id);
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

        System.out.println("Please enter model of laptop (e.g. Macbook, X-123) : ");
        String model = s.nextLine();

        System.out.println("Please enter name of staff member laptop is assigned to : ");
        String staff = s.nextLine();

        Laptop l = new Laptop(make, model, staff);

        myController.requestAddLaptop(l);

        System.out.println("New laptop added to database");


    }

    private void addNewPhone() {

        //Get data about new cellphone from user

        System.out.println("Please enter make of cellphone (e.g. Apple, Samsung) : ");
        String make = s.nextLine();

        System.out.println("Please enter model of cellphone (e.g. IPhone6, GalaxyNote) : ");
        String model = s.nextLine();

        System.out.println("Please enter name of staff member cellphone is assigned to : ");
        String staff = s.nextLine();

        Cellphone c = new Cellphone(make, model, staff);

        myController.requestAddCellphone(c);

        System.out.println("New cellphone added to database");


    }


    private void displayAllInventory() {
        System.out.println("Please enter an employees name to display his devices");
        String owner = s.nextLine();

        LinkedList allDevices = myController.requestAllInventory(owner);
        if (allDevices.isEmpty()) {
            System.out.println("No laptops or cellphones found in database");
        } else {
            System.out.println("All laptops and cellphones in the database:");
            for (Object o : allDevices) {
                System.out.println(o);   //Call the toString method in Laptop
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

    private boolean displayCellphoneById(int id) {
        Cellphone c = myController.requestCellphoneById(id);
        if (c == null) {
            System.out.println("Cellphone " + id + " not found");
            return false;
        } else {
            System.out.println(c);   //Call the toString method in Cellphone
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
            System.out.println("4. Retire a laptop");
            System.out.println("5. Add a new cellphone");
            System.out.println("6. Retire a cellphone");
            System.out.println(QUIT + ". Quit program");

            System.out.println();
            System.out.println("Please enter your selection");

            String userChoiceStr = s.nextLine();
            try {
                userChoice = Integer.parseInt(userChoiceStr);
                if (userChoice < 1  ||  userChoice > 7) {
                    System.out.println("Please enter a number between 1 and 7");
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