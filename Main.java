/**
 * Declaration: This class is the main method that prints the menu
 * Student Name: Ved Patel
 * Student Number: 041-095-249
 * Section #: 312
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * @author/Professor: George Kriger.
 */
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This is the main class
 * It contains the main method to start the program.
 */
public class Main {

    /**
     * This is the class constructor
     */
    public Main(){

    }

    /**
     * main method that will loop until exit
     * @param args arguments
     */
    public static void main(String[] args) {
        Main main = new Main(); //calling necessary methods
        Inventory inventory = new Inventory();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            try{ //this will loop until valid input
                main.displayMenu();
                int choice=keyboard.nextInt();
                keyboard.nextLine();

                switch (choice){
                    case 1:
                        inventory.addItem(keyboard, false);
                        break;

                    case 2:
                        System.out.println("Inventory: ");
                        inventory.toString();
                        System.out.println();
                        break;

                    case 3:
                        inventory.updateQuantity(keyboard, true);
                        break;

                    case 4:
                        inventory.updateQuantity(keyboard, false);
                        break;
                    case 5:
                        inventory.searchForItem(keyboard);
                        break;
                    case 6:

                        inventory.SaveToFile(keyboard);
                        break;
                    case 7:
                        inventory.readFromFile(keyboard);
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        exit=true;
                        break;

                    default:
                        System.out.println("...Invalid input, please try again...");
                        break;
                }

            }catch (InputMismatchException e){
                System.out.println("...Invalid input, please try again...");
                keyboard.nextLine();
            }
        }
    }

    /**
     * displays the main menu
     */
    public void displayMenu(){
        System.out.println("Please select one of the following: ");
        System.out.println("1: Add Item to Inventory");
        System.out.println("2: Display Current Inventory");
        System.out.println("3: Buy Item(s)");
        System.out.println("4: Sell Item(s)");
        System.out.println("5: Search for Item");
        System.out.println("6: Save Inventory to File");
        System.out.println("7: Read Inventory from File");
        System.out.println("8: To Exit");
        System.out.print("> ");
    }
}