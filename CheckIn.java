public class CheckIn {
    private LibraryInventory inventory;

    public CheckIn(LibraryInventory inventory){
        this.inventory = inventory;
    }

    public void CheckInItem(String name){
        // Find the item
        int[] location = inventory.findItem(name);

        if(location == null) {
            System.out.println("Item not found in library: " + name);
            return;
        }

        // Get the compartment
        Compartment comp = inventory.getCompartment(location[0], location[1]);

        // Check if it has a record
        if(comp.isCheckedOut()){
            // Clear the record to "return" the item
            comp.clearRecord();
            System.out.println("You have successfully checked in " + name + ".");
        }
        else{
            System.out.println("This " + name + " is not currently checked out.");
        }
    }
}