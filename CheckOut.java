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

    public int[] getLocation(String name){
        return inventory.findItem(name);
    }
    
    private void storeInfo(String name){
        int[] location = getLocation(name);
        try(FileWriter writer = new FileWriter("checkouts.txt", true)){
            writer.write("Patron: " + patron + "\n");
            writer.write("Book: " + name + "\n");
            writer.write("Due Date: " + dueDate + "\n");
            writer.write("Location: [" + location[0] + ", " + location[1] + "]\n");
        }
        catch(IOException e){
            System.out.println("Invalid information, unable to store data");
        }
    }

    public boolean checkoutExists(String name){
        try(Scanner scanner = new Scanner(new java.io.File("checkouts.txt"))){
            String target = "Book: " + name;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.equals(target)) {
                    return true;
                }
            }
        } 
        catch(IOException e){
            System.out.println("Could not read checkouts.txt");
        }
        return false;
    }

    public void loanItem(String name){
        boolean exists = checkoutExists(name);
        if(exists){
            System.out.println("This " + name + " is already checked out.");
        }
        else{
            storeInfo(name);
            int[] location = getLocation(name);
            if (location != null) {
                Item item = inventory.getItem(location[0], location[1]);
                item.setCheckedOut(true);
                item.setCheckedOutBy(patron);
                item.setDueDate(dueDate);
            }
            System.out.println("You have successfully checked out " + name + ". It is due on " + dueDate + ".");
        }
    }
}