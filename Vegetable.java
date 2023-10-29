import java.util.Formatter;
import java.util.Scanner;

/**
 * vegetable class extends Food Item class
 */
public class Vegetable extends FoodItem{
    /**
     * this will store the farmName
     */
    private String farmName;

    /**
     * a default constructor
     */
    public Vegetable(){

    }

    /**
     * This method will make a final string that stores the values
     * @return string
     */
    @Override
    public String toString() {
        return super.toString() +" farm supplier: "+farmName;
    }

    /**
     * This method helps to write the item in the file
     * @param writer Formatter
     */
    @Override
    public void outputItem(Formatter writer){
        try {
            super.outputItem(writer);
            writer.format("%s\n", farmName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method will take a supplier name
     * @param keyboard takes a scanner input
     * @param fromFile boolean for input coming from file or not
     * @return boolean
     */
    @Override
    public boolean addItem(Scanner keyboard, boolean fromFile) {
        if (!fromFile) {
            super.addItem(keyboard, false);
            System.out.print("Enter the name of the farm supplier: ");
            this.farmName = keyboard.nextLine();
        }else {
            super.addItem(keyboard, true);
            this.farmName = keyboard.nextLine();
        }
        return true;
    }
}
