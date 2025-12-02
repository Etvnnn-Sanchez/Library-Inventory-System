import java.io.Serializable;

public class Compartment implements Serializable {
    private Item item;
    private CheckoutRecord checkoutRecord; // This holds the patron/due date info

    public Compartment() {
        this.item = null;
        this.checkoutRecord = null;
    }

    //

    public boolean isEmpty() {
        return item == null;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    // Used by CheckOut.java to see if it's available
    //    and by CheckIn.java to ensure it can be returned.
    public boolean isCheckedOut() {
        return checkoutRecord != null;
    }

    // Used by CheckOut.java to save the patron/date
    public void setCheckoutRecord(CheckoutRecord record) {
        this.checkoutRecord = record;
    }

    // Used by CheckOut.java to see WHO has it 
    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    //  Used by CheckIn.java to "return" the item
    public void clearRecord() {
        this.checkoutRecord = null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty";
        String str = item.toString();
        if (isCheckedOut()) {
            str += "\n" + checkoutRecord.toString();
        }
        return str;
    }
}