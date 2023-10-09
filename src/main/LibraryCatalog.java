package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javax.xml.catalog.Catalog;

import data_structures.ArrayList;
import data_structures.DoublyLinkedList;
import data_structures.SinglyLinkedList;
import interfaces.FilterFunction;
import interfaces.List;

public class LibraryCatalog {
	
		
	public LibraryCatalog() throws IOException {
		
	}

	List<Book> bookCatalog = getBookCatalog(); // get the initial data from csv bookCatalog
	List<User> userList = getUsers();  // get the initial data from csv User

	private List<Book> getBooksFromFiles() throws IOException {
        List<Book> books = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader("data/catalog.csv"))) {
            br.readLine(); // skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);

                int bookId = Integer.parseInt(values[0].trim());
                String title = values[1].trim();
                String author = values[2].trim();
                String genre = values[3].trim();
                LocalDate lastCheckOut = LocalDate.parse(values[4].trim());
                boolean isCheckedOut = Boolean.parseBoolean(values[5].trim());

                books.add(new Book(bookId, title, author, genre, lastCheckOut, isCheckedOut));
			}
		} catch (IOException e) {
				e.printStackTrace();
				throw e; 
		}



		return books;
	}
	
	private List<User> getUsersFromFiles() throws IOException {
		List<User> users = new ArrayList<>();
		String csvFile = "data/user.csv"; // Relative Path
		String line;
		String csvSplitBy = ",";

		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine(); //skip the first line 

			while ((line = br.readLine()) != null) {
				String[] values = line.split(csvSplitBy);

				int userId = Integer.parseInt(values[0].trim());
				String userName = values[1].trim();
				List<Book> userBooks = new ArrayList<>();

				//add books 

				// check inside the curly braces and verify that is not empty
				if(values.length >2 && !values[2].trim().isEmpty()) {
					String[] bookIds = values[2].trim().replace("{", "").replace("}", "").split("\\s+"); // simplify this
					for (String bookId: bookIds){
						userBooks.add(new Book(Integer.parseInt(bookId)));
					}
				} 

				users.add(new User(userId, userName, userBooks));

			}
			
		} catch (IOException e) {
				e.printStackTrace();
				throw e; 
		}



		return users;
	}


	public List<Book> getBookCatalog() {
		try {
			return getBooksFromFiles();
		} catch (IOException e){
			e.printStackTrace();
			return new ArrayList<>();
		}
		
	}

	public List<User> getUsers() {
		try {
			return getUsersFromFiles();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void addBook(String title, String author, String genre) {
		int id;
		int catalogSize = getBookCatalog().size();
		LocalDate lastCheckOut = LocalDate.parse("2023-09-15");

		// In case that we violate the initial 50 book, and avoid repeating the ID
		if(catalogSize < 50){
			id = catalogSize + 50;
			return ;
		}

		id = catalogSize + 1;

		bookCatalog.add(new Book(id, title, author, genre, lastCheckOut, false));

		return ;
	}

	public void removeBook(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id){
				bookCatalog.remove(book);
			}
		}
		return ;
	}	
	
	public boolean checkOutBook(int id) {

		for(Book book: bookCatalog){
			if(book.getId() == id){
				if(!book.isCheckedOut()){
					book.setCheckedOut(true);
					book.setLastCheckOut(LocalDate.parse("2023-09-15"));
					return true;
				}
				if(book.isCheckedOut())
					return false;
			}
		} return false;

	}

	public boolean returnBook(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id){

				if(book.isCheckedOut()){
					book.setCheckedOut(false);
					return true;
				}
		
				if(!book.isCheckedOut())
					return false;
			}
		}
		return false;
	}
	
	public boolean getBookAvailability(int id) {
		for(Book book: bookCatalog){
			if(book.getId() == id && !(book.isCheckedOut()))
				return true;
		}
		return false;
	}

	public int bookCount(String title) {
		int counter = 0;
		for(Book book: bookCatalog){
			if(book.getTitle().equals(title))
				counter++;
		}
		return counter;
	}

	
	public void generateReport() throws IOException {
		
		String output = "\t\t\t\tREPORT\n\n";
		output += "\t\tSUMMARY OF BOOKS\n";
		output += "GENRE\t\t\t\t\t\tAMOUNT\n";
		/*
		 * In this section you will print the amount of books per category.
		 * 
		 * Place in each parenthesis the specified count. 
		 * 
		 * Note this is NOT a fixed number, you have to calculate it because depending on the 
		 * input data we use the numbers will differ.
		 * 
		 * How you do the count is up to you. You can make a method, use the searchForBooks()
		 * function or just do the count right here.
		 */
//		output += "Adventure\t\t\t\t\t" + (/*Place here the amount of adventure books*/) + "\n";
//		output += "Fiction\t\t\t\t\t\t" + (/*Place here the amount of fiction books*/) + "\n";
//		output += "Classics\t\t\t\t\t" + (/*Place here the amount of classics books*/) + "\n";
//		output += "Mystery\t\t\t\t\t\t" + (/*Place here the amount of mystery books*/) + "\n";
//		output += "Science Fiction\t\t\t\t\t" + (/*Place here the amount of science fiction books*/) + "\n";
//		output += "====================================================\n";
//		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" + (/*Place here the total number of books*/) + "\n\n";
		
		/*
		 * This part prints the books that are currently checked out
		 */
		output += "\t\t\tBOOKS CURRENTLY CHECKED OUT\n\n";
		/*
		 * Here you will print each individual book that is checked out.
		 * 
		 * Remember that the book has a toString() method. 
		 * Notice if it was implemented correctly it should print the books in the 
		 * expected format.
		 * 
		 * PLACE CODE HERE
		 */		
//		
//		output += "====================================================\n";
//		output += "\t\t\tTOTAL AMOUNT OF BOOKS\t" (/*Place here the total number of books that are checked out*/) + "\n\n";
		
		
		/*
		 * Here we will print the users the owe money.
		 */
		output += "\n\n\t\tUSERS THAT OWE BOOK FEES\n\n";
		/*
		 * Here you will print all the users that owe money.
		 * The amount will be calculating taking into account 
		 * all the books that have late fees.
		 * 
		 * For example if user Jane Doe has 3 books and 2 of them have late fees.
		 * Say book 1 has $10 in fees and book 2 has $78 in fees.
		 * 
		 * You would print: Jane Doe\t\t\t\t\t$88.00
		 * 
		 * Notice that we place 5 tabs between the name and fee and 
		 * the fee should have 2 decimal places.
		 * 
		 * PLACE CODE HERE!
		 */
		
		output += "Testing Return Books" + "\n";
		
		for(Book book: bookCatalog){
			if(book.getId() == 50)
				output += book.calculateFees() + "\n";
		}


			
		output += "====================================================\n";
//		output += "\t\t\t\tTOTAL DUE\t$" + (/*Place here the total amount of money owed to the library.*/) + "\n\n\n";
		output += "\n\n";
		System.out.println(output);// You can use this for testing to see if the report is as expected.
		
		/*
		 * Here we will write to the file.
		 * 
		 * The variable output has all the content we need to write to the report file.
		 * 
		 * PLACE CODE HERE!!
		 */
		
	}
	
	/*
	 * BONUS Methods
	 * 
	 * You are not required to implement these, but they can be useful for
	 * other parts of the project.
	 */
	public List<Book> searchForBook(FilterFunction<Book> func) {
		return null;
	}
	
	public List<User> searchForUsers(FilterFunction<User> func) {
		return null;
	}
	
}
