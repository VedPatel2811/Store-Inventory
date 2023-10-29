import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.String.valueOf;

/**
 * Inventory class
 */
public class Inventory {


    /**
     * Creating an array
     */
    private ArrayList<FoodItem> inventory;
    /**
     * variable to cheque the number of item in the array
     */

    private int numItems;
    /**
     * this is the index that will help to store the values in the array
     */
    private int index=0;



    /**
     * this is the inventory constructor that initialize the values
     */
    public Inventory(){
        inventory = new ArrayList<>();
        this.numItems=0;
    }
    /**
     * Initialize a variable that help to cheque Item code already exist or not
     */
    boolean itemCodeExist = false;

    /**
     * This method will return a toString
     * @return string
     */
    public String toString(){
        sortArray();
        StringBuilder sb = new StringBuilder();
        for (int i= 0; i<numItems; i++) { //to store values according to index
            sb.append(inventory.toString()).append("\n");
        }

        for (int i=0; i< numItems; i++){
            System.out.println(inventory.get(i));
        }
        return sb.toString();
    }

    /**
     * Created a void method that help to sort the array
     */
    public void sortArray(){
        for (int i=0; i<numItems; i++){
            for (int j=i+1; j<numItems; j++){
                if (inventory.get(j).getItemCode()<inventory.get(i).getItemCode()){
                    FoodItem temp = inventory.get(i);
                    inventory.set(i,inventory.get(j));
                    inventory.set(j, temp);
                }
            }
        }
    }

    /**
     * Created a void method that help to search an item from the inventory
     * @param keyboard that takes input from scanner
     */
    public void searchForItem(Scanner keyboard){
        System.out.print("Enter the code for the item: ");
        int search = keyboard.nextInt();
        keyboard.nextLine();
        int index = -1;
        boolean found = false;
        for (int i=0; i<numItems; i++){
            if (search == inventory.get(i).getItemCode()){
                found =true;
                index=i;
                break;
            }
        }
        if (found){
            System.out.println(inventory.get(index));
        }else {
            System.out.println("Code not found in inventory...");
        }
    }

    /**
     * void method that help to save the current inventory to the file
     * @param keyboard takes an input from scanner
     */
    public void SaveToFile(Scanner keyboard){
        System.out.print("Enter the filename to save to: ");
        String fileName = keyboard.nextLine();
        try(Formatter write = new Formatter(new BufferedWriter(new FileWriter(fileName)))){
            for (FoodItem item: inventory){
                if (item instanceof Fruit){
                    write.format("f\n");
                    item.outputItem(write);
                } else if (item instanceof Vegetable) {
                    write.format("v\n");
                    item.outputItem(write);
                } else if (item instanceof Preserve) {
                    write.format("p\n");
                    item.outputItem(write);
                }
            }
        }catch (IOException e){
            System.out.println("Error...");
        }
    }

    /**
     * void method that help to add item to inventory from the file
     * @param keyboard to take input from scanner
     */
    public void readFromFile(Scanner keyboard){
        System.out.print("Enter the filename to read from: ");
        String readFile = keyboard.nextLine();
        try(Scanner read = new Scanner(new File(readFile))){
            addItem(read,true);
            while (read.hasNext()) {
                addItem(read, true);
            }
            if (itemCodeExist){
                System.out.println("Item code already exists");
                System.out.println("Error Encountered while reading the file, aborting...");
                itemCodeExist=false;
            }
        }catch (FileNotFoundException e){
            System.out.println("File Not Found, ignoring...");
        }
    }

    /**
     * This method will cheque for the itemCode already exist or not
     * @param item item to cheque in array
     * @return if find return the index else -1
     */
    public int alreadyExists(FoodItem item){
        for (int i = 0; i<numItems; i ++){
            if (inventory.get(i).getItemCode()==item.getItemCode()){ //to cheque the item in the inventory
                return i;
            }
        }
        return -1;
    }

    /**
     * This method will add the values to its respected classes
     * @param keyboard scanner input
     * @param fromFile the input is from a file or not
     * @return boolean
     */
    public boolean addItem(Scanner keyboard, boolean fromFile){
        if(!fromFile) {
            FoodItem item = null;
            boolean exit = false; //looping variable
            while (!exit) {
                System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p)? ");
                String choice = keyboard.nextLine();

                if (choice.equalsIgnoreCase("f")) {
                    item = new Fruit();
                    item.addItem(keyboard, false);


                } else if (choice.equalsIgnoreCase("v")) {
                    item = new Vegetable();
                    item.addItem(keyboard, false);


                } else if (choice.equalsIgnoreCase("p")) {
                    item = new Preserve();
                    item.addItem(keyboard, false);
                } else {
                    System.out.println("Invalid entry");
                    continue;
                }
                if (alreadyExists(item) != -1) { //if item code already exist this will print the statement
                    System.out.println("Item code already exist");
                    continue;
                }
                inventory.add(index, item);
                index++;
                numItems++;
                return true;

            }
            if (!item.addItem(keyboard, false)) {
                return false;
            }
        }else {

            FoodItem item = null;
            String itemType = keyboard.nextLine();
            if (Objects.equals(itemType, "f")) {
                item = new Fruit();
                item.addItem(keyboard, true);
            } else if (Objects.equals(itemType, "v")) {
                item = new Vegetable();
                item.addItem(keyboard, true);
            } else if (Objects.equals(itemType, "p")) {
                item = new Preserve();
                item.addItem(keyboard, true);
            } else {
                System.out.println("Invalid item type..");
            }
            if (alreadyExists(item) != -1) { //if item code already exist this will print the statement
                itemCodeExist=true;
                return false;
            }else {
                inventory.add(index, item);
                index++;
                numItems++;
                return true;
            }
        }

        return true;
    }

    /**
     * This method update the values of quantity
     * @param keyboard takes an input
     * @param buyOrSell true for buy false for sell
     * @return boolean
     */
    public boolean updateQuantity(Scanner keyboard, boolean buyOrSell){
        if (numItems == 0 && buyOrSell) {
            System.out.println("Error...could not buy item");
            return false;
        } else if (numItems==0) {
            System.out.println("Error...could not sell item");
            return false;
        }else {

            System.out.print("Enter the code for the item: ");
            int itemCode = keyboard.nextInt();

            int index = -1;
            for (int i = 0; i < numItems; i++) {
                if (inventory.get(i).isEqual(itemCode)) {
                    index = i;
                    break;
                }
            }
            if (buyOrSell) {
                if (index == -1) {
                    System.out.println("Error...could not buy item");
                    return false;
                }
            }else {
                if (index == -1) {
                    System.out.println("Error...could not sell item");
                    return false;
                }
            }

            System.out.print("Enter valid quantity to buy: ");
            int quantity = keyboard.nextInt();

            if (buyOrSell) {
                return inventory.get(index).updateItem(quantity);
            } else {

                if (inventory.get(index).updateItem(-quantity)) {
                    return true;
                } else {
                    System.out.println("Error...could not sell item");
                    return false;
                }
            }
        }
    }
}
