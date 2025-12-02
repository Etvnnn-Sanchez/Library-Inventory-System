import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryInventory implements Serializable {
    private Compartment[][] storage;
    private static final int COMPARTMENTS_PER_SHELF = 15;
    private int numShelves;

    public LibraryInventory(int numShelves) {
        this.numShelves = numShelves;
        // Initialize the 2D array
        storage = new Compartment[numShelves][COMPARTMENTS_PER_SHELF];

        // Populate the 2D array with empty Compartments
        for (int i = 0; i < numShelves; i++) {
            for (int j = 0; j < COMPARTMENTS_PER_SHELF; j++) {
                storage[i][j] = new Compartment();
            }
        }
    }

    private boolean isValid(int shelf, int compartment) {
        if (shelf < 0 || shelf >= numShelves) return false;
        if (compartment < 0 || compartment >= COMPARTMENTS_PER_SHELF) return false;
        return true;
    }

    public void addItem(Item item, int shelf, int compartment) {
        if (!isValid(shelf, compartment)) {
            System.err.println("Error: Location out of bounds.");
            return;
        }

        Compartment comp = storage[shelf][compartment];

        if (!comp.isEmpty()) {
            System.err.println("Error: Compartment is occupied.");
            return;
        }

        comp.setItem(item);
        System.out.println(item.getName() + " added to [" + shelf + "][" + compartment + "].");
    }

    public Item getItem(int shelf, int compartment) {
        if (isValid(shelf, compartment)) {
            return storage[shelf][compartment].getItem();
        }
        return null;
    }
    public void checkoutItem(String itemName, String patron, String dueDate) {
        int[] loc = findItem(itemName);
        if (loc == null) {
            System.out.println("Item not found: " + itemName);
            return;
        }

        Compartment comp = storage[loc[0]][loc[1]];

        if (comp.isCheckedOut()) {
            System.out.println("Error: Item is already checked out.");
        } else {
            comp.setCheckoutRecord(new CheckoutRecord(patron, dueDate));
            System.out.println("Success: " + itemName + " checked out.");
        }
    }

    public void checkinItem(String itemName) {
        int[] loc = findItem(itemName);
        if (loc == null) {
            System.out.println("Item not found.");
            return;
        }

        Compartment comp = storage[loc[0]][loc[1]];

        if (!comp.isCheckedOut()) {
            System.out.println("Item is not currently checked out.");
        } else {
            comp.clearRecord();
            System.out.println("Success: " + itemName + " returned.");
        }
    }

    public int[] findItem(String name) {
        for (int i = 0; i < numShelves; i++) {
            for (int j = 0; j < COMPARTMENTS_PER_SHELF; j++) {
                Compartment comp = storage[i][j];
                if (!comp.isEmpty() && comp.getItem().getName().equals(name)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void swapItem(int shelfA, int compA, int shelfB, int compB) {
        if (!isValid(shelfA, compA) || !isValid(shelfB, compB)) {
            System.err.println("Invalid locations.");
            return;
        }

        Compartment c1 = storage[shelfA][compA];
        Compartment c2 = storage[shelfB][compB];

        if (c1.isEmpty() || c2.isEmpty()) {
            System.err.println("Both compartments must have items to swap.");
            return;
        }

        // Swap contents (Item AND Record)
        Item tempItem = c1.getItem();
        CheckoutRecord tempRecord = c1.getCheckoutRecord();

        c1.setItem(c2.getItem());
        c1.setCheckoutRecord(c2.getCheckoutRecord());

        c2.setItem(tempItem);
        c2.setCheckoutRecord(tempRecord);

        System.out.println("Items swapped.");
    }

    public void printItemsInStorage(){
        System.out.println("\n==========================================");
        System.out.println("            LIBRARY SHELF MAP             ");
        System.out.println("==========================================");
        System.out.println("Key: [ ]=Empty  [B]=Book  [M]=Movie  [Z]=Magazine  [X]=Checked Out");
        System.out.println("      01 02 03 04 05 06 07 08 09 10 11 12 13 14 15");

        for (int i = 0; i < numShelves; i++) {
            System.out.printf("S%02d: ", (i + 1)); // Print Shelf Number (e.g., S01)

            for (int j = 0; j < COMPARTMENTS_PER_SHELF; j++) {
                Compartment comp = storage[i][j];

                if (comp.isEmpty()) {
                    System.out.print("[ ]");
                }
                else if (comp.isCheckedOut()) {
                    System.out.print("[X]"); // Occupied but checked out
                }
                else {
                    // Determine type for the code letter
                    Item item = comp.getItem();
                    if (item instanceof Book) {
                        System.out.print("[B]");
                    } else if (item instanceof Movie) {
                        System.out.print("[M]");
                    } else if (item instanceof Magazine) {
                        System.out.print("[Z]");
                    } else {
                        System.out.print("[?]");
                    }
                }
            }
            System.out.println(); // New line after each shelf
        }

        System.out.println("\n------------------------------------------");
        System.out.println("          ITEM DETAILS (AVAILABLE)        ");
        System.out.println("------------------------------------------");

        boolean foundItem = false;
        for (int i = 0; i < numShelves; i++) {
            for (int j = 0; j < COMPARTMENTS_PER_SHELF; j++) {
                Compartment comp = storage[i][j];

                // We only print details if it is NOT empty and NOT checked out
                if (!comp.isEmpty() && !comp.isCheckedOut()) {
                    System.out.println("Location: Shelf " + (i + 1) + " / Compartment " + (j + 1));
                    System.out.println(comp.getItem().toString());
                    foundItem = true;
                }
            }
        }

        if (!foundItem) {
            System.out.println("(No items currently on shelves)");
        }
        System.out.println("==========================================\n");
    }

    public void printCheckedoutItems() {
        System.out.println("\n=== ITEMS CHECKED OUT ===");
        for (int i = 0; i < numShelves; i++) {
            for (int j = 0; j < COMPARTMENTS_PER_SHELF; j++) {
                Compartment comp = storage[i][j];
                // Only print if there is an item and it IS checked out
                if (!comp.isEmpty() && comp.isCheckedOut()) {
                    System.out.println("Shelf [" + (i + 1) + "] Compartment [" + (j + 1) + "]");
                    System.out.println(comp.getItem().toString());
                    System.out.println(comp.getCheckoutRecord().toString() + "\n");
                }
            }
        }
    }

    public void saveInventory() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("library.dat", false))) {
            output.writeObject(this);
            System.out.println("Inventory saved.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static LibraryInventory restoreInventory() {
        File file = new File("library.dat");
        if (!file.exists()) return null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (LibraryInventory) input.readObject();
        } catch (Exception ex) {
            return null;
        }
    }

    public Compartment getCompartment(int shelf, int compartment) {
        if (isValid(shelf, compartment)) {
            return storage[shelf][compartment];
        }
        return null;
    }
}