public class Main {
    public static void main(String[] args) {


        // Load test
        LibraryInventory library = LibraryInventory.restoreInventory();

        // if our library data doesn't exist we run it through this
        if (library == null) {
            System.out.println("No save file found. Creating new inventory...");
            Book book1 = new Book("Cool book", "This book has many cool things", "AB123", "How to be Cool", "Ethan", "10/10/22");
            Book book2 = new Book("Not Cool Book", "This book has uncool things", "AC124", "How to be Uncool", "Not Ethan", "10/26/18");
            Magazine magazine1 = new Magazine("Something Magazine", "A magazine about something.", "BA123", "Today's News", 2, "What's going on?");
            Movie movie1 = new Movie("A Movie", "Where thing's happen", "CA123", "Who Knows", "Steven Spielberg", new String[] {"John Doe", "Jane Doe"});
            
            library = new LibraryInventory(15);

            //Pos Test
            library.addItem(book1, 0, 5);
            library.addItem(magazine1, 2, 1);
            library.addItem(movie1, 3, 1);


            library.addItem(book2, 0, 5);   // Negative test (occupied)
            library.addItem(book2, 15, 15); // Negative test (bounds)
            
        }

        library.printItemsInStorage();
        library.printCheckedoutItems();
        //test checkout
        CheckOut bookOut = new CheckOut(library, "IDK", "11/15/2025");
        bookOut.loanItem("Cool book");
        library.printItemsInStorage();
        library.printCheckedoutItems();

        //test checkin
        CheckIn bookIn = new CheckIn(library, bookOut, "IDK", "Cool  book");
        bookIn.CheckInItem("Cool book");
        library.printItemsInStorage();
        library.printCheckedoutItems();

        //swap check
        library.swapItem(0, 5, 2, 1);
        library.printItemsInStorage();

        //swap check error
        library.swapItem(0, 5, 15, 15);

        //save check
        library.saveInventory();
    }
}
