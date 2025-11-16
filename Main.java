public class Main {
    public static void main(String[] args) {

        LibraryInventory library = new LibraryInventory(15);
        Book book1 = new Book("Cool book", "This book has many cool things", "AB123", "How to be Cool", "Ethan", "10/10/22");
        Book book2 = new Book("Not Cool Book", "This book has uncool things", "AC124", "How to be Uncool", "Not Ethan", "10/26/18");


        // Testing done for story 1
        //Pos Test
        library.addItem(book1, 0,5);

        //Negative tests
        library.addItem(book2, 0,5);
        library.addItem(book2, 15, 15);

        //test checkout
        CheckOut bookOut = new CheckOut(library, "IDK", "11/15/2025");
        bookOut.storeInfo("Cool  book");
    }
}
