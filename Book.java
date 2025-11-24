public class Book extends Item{
    private String title;
    private String author;
    private String copyrightDate;

    public Book(String name, String description, String id, String title, String author, String copyrightDate) {
        super(name, description, id);
        this.title = title;
        this.author = author;
        this.copyrightDate = copyrightDate;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCopyrightDate() {
        return copyrightDate;
    }

    @Override
    // toString override
    // Overrides toString to output all the information
    // Uses item toString
    //
    // ID: [ID]
    // Name: [Name]
    // Description: [Description]
    // Title: [Title]
    // Author: [Author]
    // Copyright Date: [Copyright Date]
    public String toString() {
        return super.toString() +
            "Title: " + title +
            "\nAuthor: " + author +
            "\nCopyright Date: " + copyrightDate +
            "\n";
    }
}
