import java.util.Formatter;
import java.util.Scanner;

/**
 * The food Item class
 */
public class FoodItem {
    /**
     * to store the value of item code
     */
    private int itemCode;
    /**
     * to store the value of itemName
     */
    private String itemName;
    /**
     * stores the value of item price
     */
    private float itemPrice;
    /**
     * value of item that is in stock
     */
    private int itemQuantityInStock;
    /**
     * item cost
     */
    private float itemCost;



    /**
     * default constructor
     */
    public FoodItem(){

    }

    /**
     * Getter for item code
     * @return itemCode
     */
    public int getItemCode(){
        return itemCode;
    }

    /**
     * This method helps to write the item in the file
     * @param writer Formatter
     */
    public void outputItem(Formatter writer){
        try {
            writer.format("%d\n%s\n%d\n%.2f\n%.2f\n",itemCode,itemName,itemQuantityInStock,itemPrice,itemCost);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method will return a toString that contain
     * @return string
     */
    public String toString(){
        return "Item " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $" + String.format("%.2f", itemPrice) + " cost: $" + String.format("%.2f", itemCost);
    }

    /**
     * This method will cheque that the updated price will be valid or not
     * @param amount takes an int
     * @return boolean
     */
    public boolean updateItem(int amount){

        if ((amount+itemQuantityInStock)<0){
            return false;
        }else {
            itemQuantityInStock += amount;
            return true;
        }
    }

    /**
     * This method will cheque that the item code is equal to any other int in the parameter
     * @param item takes an int input
     * @return boolean
     */
    public boolean isEqual(int item){
        if (itemCode == item){
            return true;
        }else {
            return false;
        }
    }

    /**
     * This method will add the items except itemCode and it will loop until the valid input
     * @param keyboard takes a scanner input
     * @param fromFile boolean for input coming from file or not
     * @return boolean
     */
    public boolean addItem(Scanner keyboard,boolean fromFile) {
        if (!fromFile) {
            inputCode(keyboard);
            System.out.print("Enter the name for the item: ");
            this.itemName = keyboard.nextLine();

            while (true) {
                System.out.print("Enter the quantity for the item: ");
                if (keyboard.hasNextInt()) {
                    int quantity = keyboard.nextInt();
                    keyboard.nextLine();
                    if (quantity >= 0) {
                        this.itemQuantityInStock = quantity;
                        break;
                    } else {
                        System.out.println("Invalid entry");
                    }
                } else {
                    System.out.println("Invalid entry");
                    keyboard.nextLine();
                }
            }

            while (true) {
                System.out.print("Enter the cost of the item: ");
                if (keyboard.hasNextFloat()) {
                    float cost = keyboard.nextFloat();
                    keyboard.nextLine();
                    if (cost >= 0) {
                        this.itemCost = cost;
                        break;
                    } else {
                        System.out.println("Invalid entry");
                    }
                } else {
                    System.out.println("Invalid entry");
                    keyboard.nextLine();
                }
            }

            while (true) {
                System.out.print("Enter the sales price of the item: ");
                if (keyboard.hasNextFloat()) {
                    float price = keyboard.nextFloat();
                    keyboard.nextLine();
                    if (price >= 0) {
                        this.itemPrice = price;
                        break;
                    } else {
                        System.out.println("Invalid entry");
                    }
                } else {
                    System.out.println("Invalid entry");
                    keyboard.nextLine();
                }
            }
        }
        else {
            if (keyboard.hasNextInt()) {
                this.itemCode = Integer.parseInt(keyboard.nextLine());
            }
            if (keyboard.hasNextLine()){
                this.itemName=keyboard.nextLine();
            }
            if (keyboard.hasNextInt()) {
                int quantity = Integer.parseInt(keyboard.nextLine());
                if (quantity >= 0) {
                    this.itemQuantityInStock = quantity;
                } else {
                    return false;
                }
            }else {
                return false;
            }
            if (keyboard.hasNextFloat()) {
                float cost = Float.parseFloat(keyboard.nextLine());
                if (cost >= 0) {
                    this.itemCost = cost;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            if (keyboard.hasNextFloat()) {
                float price = Float.parseFloat(keyboard.nextLine());
                if (price >= 0) {
                    this.itemPrice = price;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;

    }

    /**
     * This method will take a valid input for itemCode
     * @param keyboard takes scanner input
     * @return boolean
     */
    public boolean inputCode (Scanner keyboard){
        while (true) {
            System.out.print("Enter the code for the item: ");

            if (keyboard.hasNextInt()) {
                this.itemCode = keyboard.nextInt();
                keyboard.nextLine();
                return true;
            } else {
                System.out.println("Invalid code");
                keyboard.nextLine();
            }
        }
    }



}
