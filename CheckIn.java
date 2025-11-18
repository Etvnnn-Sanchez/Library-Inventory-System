import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CheckIn {
    private CheckOut checkOut;
    private String patron;
    private String name;

    public CheckIn(CheckOut checkOut, String patron, String name){
        this.checkOut = checkOut;
        this.patron = patron;
        this.name = name;
    }

    private void removeList(String name){
        File inputFile = new File("checkouts.txt");
        File tempFile = new File("checkouts_temp.txt");
        try(
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile)
        )
        {
            while(scanner.hasNextLine()){
                String patronLine = scanner.nextLine();
                if(!scanner.hasNextLine()) break;
                String bookLine = scanner.nextLine();
                if(!scanner.hasNextLine()) break;
                String dueLine = scanner.nextLine();
                if(!scanner.hasNextLine()) break;
                String locationLine = scanner.nextLine();
                if(bookLine.equals("Book: " + name)){
                    continue;
                }
                writer.write(patronLine + System.lineSeparator());
                writer.write(bookLine + System.lineSeparator());
                writer.write(dueLine + System.lineSeparator());
                writer.write(locationLine + System.lineSeparator());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(inputFile.delete()){
            tempFile.renameTo(inputFile);
        }
    }

    public void CheckInItem(String name){
        if(checkOut.checkoutExists(name)){
            removeList(name);
            System.out.println("You have successfully checked in " + name + ".");
        }
        else{
            System.out.println("This " + name + " is not checked out.");
        }
    }
}
