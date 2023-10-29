import java.util.Formatter;
import java.util.Scanner;

/**
 * Preserve class Extend food item
 */
public class Preserve extends FoodItem{
    /**
     * stored the dimension of jarSize
     */
    private int jarSize;


    /**
     * the default constructor
     */
    public Preserve(){

    }

    /**
     * This method will return a toString
     * @return string
     */
    @Override
    public String toString() {
        return super.toString()+" size: "+jarSize+"ml";
    }

    /**
     * This method helps to write the item in the file
     * @param writer Formatter
     */
    @Override
    public void outputItem(Formatter writer){
        try {
            super.outputItem(writer);
            writer.format("%d\n",jarSize);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * This method will take an input for jar dimension and it will loop until a valid input
     * @param keyboard takes a scanner input
     * @param fromFile boolean for input coming from file or not
     * @return boolean
     */
    @Override
    public boolean addItem(Scanner keyboard, boolean fromFile) {
        if (!fromFile) {
            super.addItem(keyboard, false);
            while (true) {
                System.out.print("Enter the size of the jar in millilitres: ");
                if (keyboard.hasNextInt()) {
                    int size = keyboard.nextInt();
                    if (size >= 0) {
                        this.jarSize = size;
                        break;
                    } else {
                        System.out.println("Invalid entry");
                    }
                } else {
                    System.out.println("Invalid entry");
                    keyboard.nextLine();
                }
            }
        }else {
            super.addItem(keyboard, true);
            if (keyboard.hasNextInt()) {
                int size = Integer.parseInt(keyboard.nextLine());
                if (size >= 0) {
                    this.jarSize = size;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
