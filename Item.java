public abstract class Item implements java.io.Serializable {
    private String name;
    private String description;
    private String id;

    public Item(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
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
