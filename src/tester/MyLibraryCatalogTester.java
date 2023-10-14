package tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import main.Book;
import main.LibraryCatalog;
import main.User;

public class MyLibraryCatalogTester {

    private LibraryCatalog libraryCatalog;

    @Before
    public void setup() throws IOException {
        libraryCatalog = new LibraryCatalog();
    }

    @Test
    public void testAddAndRemoveBook() {
        // Test adding a book
        libraryCatalog.addBook("New Book", "Author X", "Mystery");
        assertTrue(libraryCatalog.getBookAvailability(51)); // Check if the added book is available

        // Test removing a book
        libraryCatalog.removeBook(51);
        assertFalse(libraryCatalog.getBookAvailability(51)); // Check if the removed book is no longer available
    }

    @Test
    public void testCheckOutAndReturnBook() {
        // Test checking out a book
        assertTrue(libraryCatalog.checkOutBook(1)); // Check out an available book
        assertFalse(libraryCatalog.checkOutBook(1)); // Try to check out the same book again (should fail)

        // Test returning a book
        assertTrue(libraryCatalog.returnBook(1)); // Return a checked-out book
        assertFalse(libraryCatalog.returnBook(2)); // Try to return a book that wasn't checked out (should fail)
    }

    @Test
    public void testBookAvailability() {
        assertTrue(libraryCatalog.getBookAvailability(1)); // Check an available book
        assertFalse(libraryCatalog.getBookAvailability(2)); // Check a checked-out book
        assertFalse(libraryCatalog.getBookAvailability(100)); // Check a non-existent book
    }

    @Test
    public void testBookCount() {
        assertEquals(2, libraryCatalog.bookCount("The Little Prince")); // Check book count for a specific title
        assertEquals(0, libraryCatalog.bookCount("Non-Existent Book Title")); // Check book count for a non-existent title
    }

    // @Test
    // public void testBookFees() {
    //     Book book = libraryCatalog.findBookById(libraryCatalog.getBookCatalog(), 36);
    //     assertEquals(389.50f, book.calculateFees(), 0.01); // Check book fees calculation
    // }

    @Test
    public void testUserCheckedOutBooks() {
        User user = libraryCatalog.getUsers().get(0); // Get the first user
        assertTrue(user.getCheckedOutList().isEmpty()); // Ensure the user doesn't have checked-out books initially

        // Check out a book for the user
        libraryCatalog.checkOutBook(1);
        assertEquals(1, user.getCheckedOutList().size()); // Check if the user has one checked-out book
    }
}

