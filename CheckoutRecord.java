import java.io.Serializable;

public class CheckoutRecord implements Serializable {
    private String patronName;
    private String dueDate;

    public CheckoutRecord(String patronName, String dueDate) {
        this.patronName = patronName;
        this.dueDate = dueDate;
    }

    public String getPatronName() {
        return patronName;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Checked out by: " + patronName + "\nDue Date: " + dueDate;
    }
}