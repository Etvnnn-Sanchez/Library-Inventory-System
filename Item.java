public abstract class Item {
    private String name;
    private String description;
    private String id;

    private boolean checkedOut;
    private String checkedOutBy;
    private String dueDate;

    public Item(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.checkedOut = false;
        this.checkedOutBy = null;
        this.dueDate = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    // toString override
    // Overrides toString to output base information
    //
    // ID: [ID]
    // Name: [Name]
    // Description: [Description]
    public String toString() {
        return  "ID: " + id +
                "\nName: " + name +
                "\nDescription: " + description +
                "\n";
    }
}