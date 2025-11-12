import java.util.List;
import java.util.ArrayList;
public class LibraryInventory {
        private static final int COMPARTMENTS = 15;
        private List<Item[]> storage;

        private boolean isValid(int shelf, int compartment){
            if(shelf < 0 || shelf >= storage.size()){
                return false;
            }
            if(compartment < 0 || compartment >= COMPARTMENTS){
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
        if(!isValid(shelf, compartment)){
            System.err.println("Cannot add item. The specified location [" + shelf + "]["
                    + compartment + "] is out of bounds.");
            return;
        }
        if(storage.get(shelf)[compartment] != null) {
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

        public void checkoutItem(int shelf, int compartment){}
        public void checkinItem(int shelf, int compartment){}
        public void swapItem(int shelfA, int compartmentA, int shelfB, int compartmentB){}

        public void printItemsInStorage(){}
        public void printCheckedoutItems(){}

        public void saveInventory() {}
        public static LibraryInventory restoreInventory() {
            return null;
        }
}
