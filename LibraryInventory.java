import java.util.ArrayList;
import java.util.List;

public class LibraryInventory {

    private static final int COMPARTMENTS = 15;
    private List<Item[]> storage;

    private boolean isValid(int shelf, int compartment) {
        if (shelf < 0 || shelf >= storage.size()) {
            return false;
        }
        if (compartment < 0 || compartment >= COMPARTMENTS) {
            return false;
        }
        return true;
    }

    public LibraryInventory(int numShelves) {
        storage = new ArrayList<>(numShelves);
        for (int i = 0; i < numShelves; i++) {
            this.storage.add(new Item[COMPARTMENTS]);
        }
    }

    public void addItem(Item item, int shelf, int compartment) {
        if (!isValid(shelf, compartment)) {
            System.err.println("Cannot add item. The specified location [" + shelf + "]["
                    + compartment + "] is out of bounds.");
            return;
        }
        if (storage.get(shelf)[compartment] != null) {
            System.err.println("Cannot add item. The specified location [" + shelf + "]["
                    + compartment + "] is already occupied by another item.");
            return;
        }

        storage.get(shelf)[compartment] = item;
        System.out.println(item.getName() + "(ID: " + item.getId() + ") has been successfully added at ["
                + shelf + "][" + compartment + "].");
    }

    public Item getItem(int shelf, int compartment) {
        return storage.get(shelf)[compartment];
    }

    /* I need this function simply to check if there is a valid name in the database when doing my edgecases. */
    public List<String> getItemNames(){
        List<String> names = new ArrayList<>();
        for(Item[] shelf : storage){
            for(Item item : shelf){
                if(item != null){
                    names.add(item.getName());
                }
            }
        }
        return names;
    }

    public int[] findItem(String name){
        for(int shelf = 0; shelf < storage.size(); shelf++){
            for(int compartment = 0; compartment < COMPARTMENTS; compartment++){
                Item item = storage.get(shelf)[compartment];
                return new int[]{shelf, compartment};
            }
        }
        return null;
    }

    public void checkoutItem(int shelf, int compartment) {
    }

    public void checkinItem(int shelf, int compartment) {
    }

    public void swapItem(int shelfA, int compartmentA, int shelfB, int compartmentB) {
    }

    // Print Items in Storage
    // Prints out the items in storage
    // Includes shelf and compartment position (Both start at 1)
    // Only prints out the item if it isn't checked out
    // Styling:
    // === ITEMS IN STORAGE ===
    // Shelf [num] Compartment [num]
    // (Item details here)
    public void printItemsInStorage() {
        System.out.println("\n=== ITEMS IN STORAGE ===");         // Print out header
        for (int shelf = 0; shelf < storage.size(); shelf++) {          // Loop over the shelf
            for (int compartment = 0; compartment < storage.get(shelf).length; compartment++) {     // Loop over the compartment
                if (getItem(shelf, compartment) != null) {          // If item isn't null
                    if (!getItem(shelf, compartment).isCheckedOut()) {       // Check if current item is checked out already
                        System.out.println("Shelf [" + (shelf + 1) + "] Compartment [" + (compartment + 1) + "]");        // If so, print out shelf and compartment details
                        System.out.println(getItem(shelf, compartment).toString());         // print out the item's details
                    }
                }
            }
        }
    }

    // Print Items in Storage
    // Prints out the items in storage
    // Includes shelf and compartment position (Both start at 1)
    // Only prints out the item if it is checked out
    // Styling:
    // === ITEMS CHECKED OUT ===
    // Shelf [num] Compartment [num]
    // (Item details here)
    public void printCheckedoutItems() {
        System.out.println("\n=== ITEMS CHECKED OUT ===");
        for (int shelf = 0; shelf < storage.size(); shelf++) {          // Loop over the shelf
            for (int compartment = 0; compartment < storage.get(shelf).length; compartment++) {     // Loop over the compartment
                if (getItem(shelf, compartment) != null) {          // If item isn't null
                    if (getItem(shelf, compartment).isCheckedOut()) {       // Check if current item isn't checked out already
                        System.out.println("Shelf [" + (shelf + 1) + "] Compartment [" + (compartment + 1) + "]");        // If so, print out shelf and compartment details
                        System.out.println(getItem(shelf, compartment).toString());         // print out the item's details
                    }
                }
            }
        }
    }

    public void saveInventory() {
    }

    public static LibraryInventory restoreInventory() {
        return null;
    }
}
