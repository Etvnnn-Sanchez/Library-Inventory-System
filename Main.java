public class Main {
    public static void main(String[] args) {

        // Load test (Try to restore from file)
        LibraryInventory library = LibraryInventory.restoreInventory();

        // If no save file exists, create new inventory and populate it
        if (library == null) {
            System.out.println("No save file found. Creating new inventory...");

            Book book1 = new Book("Cool book", "This book has many cool things", "AB123", "How to be Cool", "Ethan", "10/10/22");
            Book book2 = new Book("Not Cool Book", "This book has uncool things", "AC124", "How to be Uncool", "Not Ethan", "10/26/18");
            Magazine magazine1 = new Magazine("Something Magazine", "A magazine about something.", "BA123", "Today's News", 2, "What's going on?");
            Movie movie1 = new Movie("A Movie", "Where things happen", "CA123", "Who Knows", "Steven Spielberg", new String[] {"John Doe", "Jane Doe"});
            Book book3 = new Book("The Hobbit", "Fantasy adventure", "BK001", "The Hobbit", "J.R.R. Tolkien", "1937");
            Book book4 = new Book("Clean Code", "Programming guide", "BK002", "Clean Code", "Robert Martin", "2008");
            Book book5 = new Book("Dune", "Sci-Fi epic", "BK003", "Dune", "Frank Herbert", "1965");
            Book book6 = new Book("1984", "Dystopian classic", "BK004", "1984", "George Orwell", "1949");
            Magazine mag2 = new Magazine("Time Magazine", "Weekly news", "MZ001", "Person of the Year", 2024, "Who is it?");
            Magazine mag3 = new Magazine("Nat Geo", "Nature and Science", "MZ002", "Ocean Mysteries", 155, "Deep Blue");
            Magazine mag4 = new Magazine("Vogue", "Fashion monthly", "MZ003", "Summer Trends", 88, "New Styles");
            Movie mov2 = new Movie("Inception", "Dream heist movie", "MV001", "Inception", "Christopher Nolan", new String[] {"Leonardo DiCaprio", "Joseph Gordon-Levitt"});
            Movie mov3 = new Movie("The Matrix", "Simulation sci-fi", "MV002", "The Matrix", "Wachowskis", new String[] {"Keanu Reeves", "Laurence Fishburne"});
            Movie mov4 = new Movie("Lion King", "Animated classic", "MV003", "The Lion King", "Roger Allers", new String[] {"Matthew Broderick", "Jeremy Irons"});

            library = new LibraryInventory(15);

            System.out.println("--- Adding Items ---");
            library.addItem(book1, 0, 5);      // Shelf 1, Compartment 6
            library.addItem(magazine1, 2, 1);  // Shelf 3, Compartment 2
            library.addItem(movie1, 3, 1);     // Shelf 4, Compartment 2
            library.addItem(book3, 0, 0);
            library.addItem(book4, 0, 1);
            library.addItem(book5, 0, 2);
            library.addItem(mag2, 1, 7);
            library.addItem(mag3, 1, 8);
            library.addItem(mag4, 1, 14);
            library.addItem(mov2, 4, 0);
            library.addItem(mov3, 4, 5);
            library.addItem(mov4, 4, 10);
            library.addItem(book6, 9, 7);

            // Negative tests
            library.addItem(book2, 0, 5);   // Should fail (occupied)
            library.addItem(book2, 15, 15); // Should fail (out of bounds)
        }

        //  Print initial state
        library.printItemsInStorage();
        library.printCheckedoutItems();

        // Checkout testing
        System.out.println("\nTesting Checkout");
        CheckOut transactionOut = new CheckOut(library, "John Doe", "11/15/2025");

        // Perform the loan
        transactionOut.loanItem("Cool book");

        library.printItemsInStorage();
        library.printCheckedoutItems();

        System.out.println("\nTesting Checkin");
        // Create a CheckIn transaction object
        CheckIn transactionIn = new CheckIn(library);

        // Perform the return
        transactionIn.CheckInItem("Cool book");

        // Verify state
        library.printItemsInStorage();
        library.printCheckedoutItems();

        // Swap test
        System.out.println("\nTesting Swap");
        // Swap "Cool book" (0, 5) with "Something Magazine" (2, 1)
        library.swapItem(0, 5, 2, 1);
        library.printItemsInStorage();

        // Swap error test (Out of bounds)
        library.swapItem(0, 5, 20, 20);

        // Save test
        library.saveInventory();
    }
}