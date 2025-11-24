public class Magazine extends Item{
    private String title;
    private int edition;
    private String mainArticle;

    Magazine(String name, String description, String id, String title, int edition, String mainArticle){
        super(name, description, id);
        this.title = title;
        this.edition = edition;
        this.mainArticle = mainArticle;
    }

    // GETTER METHODS
    public String getTitle() {
        return title;
    }
    public int getEdition() {
        return edition;
    }
    public String getMainArticle() {
        return mainArticle;
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
    // Edition: [Edition]
    // Main Article: [Main Article]
    public String toString() {
        return super.toString() + 
            "Title: " + title +
            "\nEdition: " + edition +
            "\nMain Article: " + mainArticle +
            "\n";
    }
}
