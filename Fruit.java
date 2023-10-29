import java.util.Formatter;
import java.util.Scanner;

/**
 * fruit extends Food Item class
 */
public class Fruit extends FoodItem{

    /**
     * string stores orchard name
     */
    private String orchardName;


    /**
     * default constructor
     */
    public Fruit(){

    }



    /**
     * This method returns toString with orchard name
     * @return string
     */
    @Override
    public String toString() {
        return super.toString()+" orchard supplier: "+orchardName;
    }

    /**
     * This method helps to write the item in the file
     * @param writer Formatter
     */
    @Override
    public void outputItem(Formatter writer){
        try {
            super.outputItem(writer);
            writer.format("%s\n", orchardName);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This method will take a orchard name
     * @param keyboard takes a scanner input
     * @param fromFile boolean for input coming from file or not
     * @return boolean
     */
    @Override
    public boolean addItem(Scanner keyboard, boolean fromFile) {
        if (!fromFile) {
            super.addItem(keyboard, false);
            System.out.print("Enter the name of the orchard supplier: ");
            this.orchardName = keyboard.nextLine();
        }else {
            super.addItem(keyboard, true);
            this.orchardName = keyboard.nextLine();
        }
        return true;
    }
}
