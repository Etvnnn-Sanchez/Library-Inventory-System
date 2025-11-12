public class Movie extends Item{
    private String title;
    private String director;
    private String[] mainActors;
    Movie(String name, String description, String id, String title, String director, String[] mainActors) {
        super(name, description, id);
        this.title = title;
        this.director = director;
        this.mainActors = mainActors;
    }

    // GETTER METHODS
    public String getTitle() {
        return title;
    }
    public String getDirector() {
        return director;
    }
    public String[] getMainActors() {
        return mainActors;
    }

    @Override
    public String getItemDetails() {
        return "";
    }
}
