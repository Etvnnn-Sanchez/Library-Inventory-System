import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CheckOut{
    private LibraryInventory inventory;
    private String patron;
    private String dueDate;

    public CheckOut(LibraryInventory inventory, String patron, String dueDate){
        this.inventory = inventory;
        this.patron = patron;
        this.dueDate = dueDate;
    }

    public boolean validInformation(String checkOutName){
        List<String> names = inventory.getItemNames();
        boolean validInfo = false;

        if(!names.contains(checkOutName)){
            System.out.println("This " + checkOutName + " does not exist.");
        }
        else{
            validInfo = true;
        }

        return validInfo;
    }

    private String getPatronName(){
        return patron;
    }

    private String getDueDate(){
        return dueDate;
    }

    public int[] getLocation(String name){
        return inventory.findItem(name);
    }
    
    public void storeInfo(String name){
        int[] location = getLocation(name);
        try(FileWriter writer = new FileWriter("checkouts.txt", true)){
            writer.write("Patron: " + patron + "\n");
            writer.write("Book: " + name + "\n");
            writer.write("Due Date: " + dueDate + "\n");
            writer.write("Location: [" + location[0] + ", " + location[1] + "]\n");
            writer.write("----------------------\n");
        }
        catch(IOException e){
            System.out.println("Invalid information, unable to store data");
        }
    }
}