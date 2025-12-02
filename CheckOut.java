public class CheckOut {
    private LibraryInventory inventory;
    private String patron;
    private String dueDate;

    public CheckOut(LibraryInventory inventory, String patron, String dueDate){
        this.inventory = inventory;
        this.patron = patron;
        this.dueDate = dueDate;
    }

    public void loanItem(String name){
        // Find where the item is stored
        int[] location = inventory.findItem(name);

        if(location == null){
            System.out.println("Item not found: " + name);
            return;
        }

        // Get the specific compartment object
        Compartment comp = inventory.getCompartment(location[0], location[1]);

        // Check internal state (Memory), not a text file
        if(comp.isCheckedOut()){
            System.out.println("This " + name + " is already checked out by " +
                    comp.getCheckoutRecord().getPatronName());
        }
        else {
            // Create the Record and attach it to the Compartment
            CheckoutRecord record = new CheckoutRecord(patron, dueDate);
            comp.setCheckoutRecord(record);

            System.out.println("You have successfully checked out " + name +
                    ". It is due on " + dueDate + ".");
        }
    }
}